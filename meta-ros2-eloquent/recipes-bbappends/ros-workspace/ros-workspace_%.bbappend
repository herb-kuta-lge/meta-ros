# Copyright (c) 2019 LG Electronics, Inc.

# ASSERT(<prefix> == /usr)
# ros-workspace installs its setup scripts under <prefix>, which is an unnatural location when <prefix> is /usr. Move them to be
# under <sysconfdir>/profile.d/ros and create "ros_" prefixed symlinks to them under <bindir>.
#
# Create a ros.sh that sources ros/setup.sh, thereby setting up the ROS workspace for every login. Place it in a separate package
# which will be added to images when IMAGE_FEATURES contains "ros-implicit-workspace"
do_install_append() {
    profile_dir=${sysconfdir}/profile.d/ros
    mkdir -p ${D}$profile_dir
    cd ${D}$profile_dir

    mv ${D}${prefix}/*setup.bash .
    mv ${D}${prefix}/*setup.zsh .
    mv ${D}${prefix}/*setup.sh .
    mv ${D}${prefix}/_local_setup_util.py .

    mkdir -p ${D}${bindir}
    for f in *setup.bash *setup.zsh *setup.sh; do
        ln -s $profile_dir/$f ${D}${bindir}/ros_$f
    done

    echo ". $profile_dir/setup.sh" > ../ros.sh

    cd - > /dev/null
}

PACKAGES =+ "${PN}-implicitworkspace"

FILES_${PN}-implicitworkspace = " \
    ${sysconfdir}/profile.d/ros.sh \
"
