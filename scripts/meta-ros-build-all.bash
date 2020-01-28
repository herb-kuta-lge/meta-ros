# /bin/bash
#
# Usage: bash build/scripts/meta-ros-build-all.bash [--no-images]
#
# XXX Evantually: [--no-images] --all | --last | FILE1.mcf [FILE2.mcf ...]
# FILEn.mcf is typically build/files/xxx.mcf
# When --all: cfgs=$(cd build/files; ls *.mcf)
#
# Copyright (c) 2020LG Electronics, Inc.

usage()
{
    echo "Usage: bash meta-ros-build-all.bash [--no-images]"
}

cfgs="
ros1-melodic-thud.mcf
ros2-dashing-thud.mcf
ros2-eloquent-thud.mcf
webos-melodic-thud.mcf
webos-dashing-thud.mcf
webos-eloquent-thud.mcf
ros1-melodic-warrior.mcf
ros2-dashing-warrior.mcf
ros2-eloquent-warrior.mcf
ros1-melodic-zeus.mcf
ros2-dashing-zeus.mcf
ros2-eloquent-zeus.mcf
ros1-melodic-dunfell.mcf
ros2-dashing-dunfell.mcf
ros2-eloquent-dunfell.mcf
"

nothud_cfgs="
ros2-dashing-warrior.mcf
ros2-eloquent-warrior.mcf
ros1-melodic-warrior.mcf
ros2-dashing-zeus.mcf
ros2-eloquent-zeus.mcf
ros1-melodic-zeus.mcf
ros2-dashing-dunfell.mcf
ros2-eloquent-dunfell.mcf
ros1-melodic-dunfell.mcf
"

x_cfgs="
ros2-dashing-thud.mcf
"

build_images=true
case "$1" in
    "") : OK
        ;;

    --no-images)
        build_images=false
        ;;

    *)  usage
        exit 1
esac
# Otherwise "openembedded-core/oe-init-build-env" below will have $1 set.
set --

if [ ! -L build/conf ]; then
    echo "ABORT: build/conf is not a symlink"
    exit 1
fi

declare -r abort_exit=42
declare -r summary_dir=BUILD-ALL-SUMMARY
declare -r last_cfg=LAST_CFG
# XXX Eventually, get TMPDIR from bitbake somehow.
declare bbtmptree=/mnt/disk3/YOCTO-ARTIFACTS
mkdir -p $summary_dir

if [ -z "$cfgs" ]; then
    if [ -r $summary_dir/$last_cfg ]; then
        cfgs=$(cat $summary_dir/$last_cfg)
    else
        echo "ABORT: cfgs is empty but $summary_dir/$last_cfg not found"
        exit 1
    fi
    declare -r do_config=false
else
    declare -r do_config=true
fi

set -x
for cfg in $cfgs; do
    if [ -r build/files/$cfg ]; then
        : ================================
        : Setup $cfg
        : ================================
        if $do_config; then
            rm -f $summary_dir/$cfg
            cp build/files/$cfg conf/$cfg
            build/scripts/mcf -f conf/$cfg
            echo $cfg > $summary_dir/$last_cfg
        else
            : ================================
            : WARNING: mcf not run
            : ================================
        fi

        # XXX Convert to invoke python3 and print value.
        machines=$(sed -n -e 's/^ *Machines *=//p' conf/$cfg | tr -d "[],'")

        do_cleansstate=false
        case $cfg in
            ros1-melodic-*)
                image_targets="ros-image-core ros-image-turtlebot3-core ros-image-turtlebot3-all"
                noimage_targets="packagegroup-ros-turtlebot3-extended"
                ;;

            webos-melodic-*)
                image_targets="webos-image-ros-core webos-image-ros-turtlebot3-core webos-image-ros-turtlebot3-all"
                noimage_targets="packagegroup-webos-extended packagegroup-ros-turtlebot3-extended"
                do_cleansstate=true
                ;;

            ros*)
                image_targets="ros-image-core ros-image-world"
                noimage_targets="packagegroup-ros-world"
                ;;

            *)  image_targets="webos-image-ros-core webos-image-ros-world"
                noimage_targets="packagegroup-webos-extended packagegroup-ros-world"
                do_cleansstate=true
                ;;
        esac

        if $build_images; then
            targets="$image_targets"
        else
            targets="$noimage_targets"
        fi
        (
            set +x
            trap "exit $abort_exit" SIGINT # SIGTERM ??

            # From OpenEmbedded-Build-Instructions.md:
            unset BDIR BITBAKEDIR BUILDDIR OECORELAYERCONF OECORELOCALCONF OECORENOTESCONF OEROOT TEMPLATECONF
            source openembedded-core/oe-init-build-env
            cd ..
            set -x

            export MACHINE
            for MACHINE in $machines; do
                buildname=${cfg%.mcf}-$MACHINE
                : --------------------------------
                : Build $buildname
                : --------------------------------
                logfile=$summary_dir/${buildname}.log
                stampfilebase=$summary_dir/$buildname
                rm -f $logfile ${stampfilebase}.*
                touch ${stampfilebase}.begin
                $do_cleansstate && $build_images && bitbake -c cleansstate $targets
                bitbake -k $targets
                err=$?
                # touch it so that set -x shows the filename.
                touch ${stampfilebase}.end
                echo $err > ${stampfilebase}.end
                if [ $err -ne 0 ]; then
                    cp $bbtmptree/BUILD-${cfg%.mcf}/log/cooker/$MACHINE/console-latest.log $logfile
                fi
            done
        )
        [ $? -eq $abort_exit ] && exit $abort_exit
    fi

done
