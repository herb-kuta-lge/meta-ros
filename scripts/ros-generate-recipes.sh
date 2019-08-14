# /bin/sh (deliberately no !#)
#
# Usage: cd meta-ros
#        [SUPERFLORE_GEN_OE_RECIPES=/path/to/superflore-gen-oe-recipes] sh scripts/ros-generate-recipes.sh ROS_DISTRO YYYYMMDD __all__
#        (current branch is now superflore/DATETIME)
#
# where YYYYMMDD is the value for ROS_DISTRO_RELEASE_DATE, which is taken from the release announcement or the last field of the
# "release-ROS_DISTRO-YYYYMMDD" tag. Prior to the first release of ROS_DISTRO, specify "" for YYYYMMDD.
#
# XXX Once superflore is fixed to generate only recipes when given --only, have this script recognize optional PKG1 PKG2 ...
# arguments in place of "__all__" that cause "--only PKG1 PKG2 ..." to be passed to superflore.
#
# Copyright (c) 2019 LG Electronics, Inc.

SCRIPT_NAME="ros-generate-recipes"
SCRIPT_VERSION="1.0.0"

usage() {
    echo "Usage: cd meta-ros"
    echo "       [SUPERFLORE_GEN_OE_RECIPES=/path/to/superflore-gen-oe-recipes] sh scripts/$SCRIPT_NAME.sh ROS_DISTRO YYYYMMDD __all__"
    echo "           or"
    echo "       sh scripts/$SCRIPT_NAME.sh --version"
    exit 1
}

if [ $1 = "--version" ]; then
    echo "$SCRIPT_NAME $SCRIPT_VERSION"
    exit
fi

# XXX Eventually, this test will be changed to [ $# -ge 3 ]
[ $# -ne 3 ] && usage

[ -z "$SUPERFLORE_GEN_OE_RECIPES" ] && SUPERFLORE_GEN_OE_RECIPES=$(which superflore-gen-oe-recipes)
if [ -z "$SUPERFLORE_GEN_OE_RECIPES" ]; then
    echo "ABORT: superflore-gen-oe-recipes not found"
    exit
fi

ROS_DISTRO=$1
# ROS_VERSION and ROS_PYTHON_VERSION must be in the environment as they appear in "conditional" attributes.
case $ROS_DISTRO in
    "kinetic"|"melodic")
        export ROS_VERSION="1"
        export ROS_PYTHON_VERSION="2"
        ;;

    "bouncy"|"crystal"|"dashing")
        export ROS_VERSION="2"
        export ROS_PYTHON_VERSION="3"
        ;;

    *)  echo "ABORT: Unrecognized ROS_DISTRO: $ROS_DISTRO"
        exit 1
        ;;
esac

skip_keys_option=""
case $ROS_DISTRO in
    "kinetic")
        skip_keys_option="--skip-keys catkin_virtualenv flatbuffers grpc nanomsg octovis"
        ;;

    "melodic")
        skip_keys_option="--skip-keys catkin_virtualenv flatbuffers iirob_filters grpc nanomsg octovis"
        ;;

    "bouncy")
        skip_keys_option="--skip-keys console_bridge"
        ;;

    *)  : Nothing is skipped for "crystal" and "dashing".
        ;;
esac

if [ $3 = "__all__" ]; then
    only_option=""
else
    usage

    # XXX Eventually:
    shift 2
    only_option="--only $*"
fi

before_commit=$(git rev-list -1 HEAD)

$SUPERFLORE_GEN_OE_RECIPES --dry-run --ros-distro $ROS_DISTRO --output-repository-path . --upstream-branch HEAD \
                            $skip_keys_option $only_option

after_commit=$(git rev-list -1 HEAD)
if [ $after_commit != $before_commit ]; then
    generated="conf/ros-distro/include/$ROS_DISTRO/generated-ros-distro.inc"
    cat <<! >> $generated

# From the release announcement or the last field of the "release-ROS_DISTRO-YYYYMMDD" tag for the release in
# https://github.com/ros2/ros2/releases. Prior to the first release of a ROS_DISTRO, it is set to "".
ROS_DISTRO_RELEASE_DATE = "$2"

# Platform packages without a OE-RECIPE@OE-LAYER mapping in base.yaml or python.yaml. Until they are added, the settings can be
# overridden in ros-distro.inc if necessary.
!
    sed -n 's/- \${ROS_UNRESOLVED_PLATFORM_PKG_\([^}]*\)}.*/ROS_UNRESOLVED_PLATFORM_PKG_\1 = "UNRESOLVED-\1"/p' \
        files/$ROS_DISTRO/rosdep-resolve.yaml >> $generated

    git add $generated
    git commit --amend -q -C HEAD

    unset generated
fi
