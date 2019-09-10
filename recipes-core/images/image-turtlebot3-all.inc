# Copyright (c) 2019 LG Electronics, Inc.

require image-turtlebot3-core.inc

# http://emanual.robotis.com/docs/en/platform/turtlebot3/slam/#run-slam-nodes
TURTLEBOT3_PACKAGES_ADDITIONAL_SLAM_METHODS ??= " \
    cartographer-ros \
    slam-karto \
"

# XXX "cartographer" doesn't build; including "slam-karto" give this error in do_rootfs():
#   nothing provides openblas needed by sparse-bundle-adjustment-0.4.2-r0.cortexa7t2hf-neon-vfpv4
TURTLEBOT3_PACKAGES_ADDITIONAL_SLAM_METHODS = ""

# From http://emanual.robotis.com/docs/en/platform/turtlebot3/pc_setup/#install-dependent-ros-packages, without rqt-image-view.
# XXX Need platform packages for rosserial-arduino
TURTLEBOT3_PACKAGES_REMOTE_PC = " \
    amcl \
    compressed-image-transport \
    depthimage-to-laserscan \
    gmapping \
    interactive-markers \
    joy \
    laser-proc \
    map-server \
    move-base \
    navigation \
    rgbd-launch \
    rosserial-client \
    rosserial-msgs \
    rosserial-python \
    rosserial-server \
    teleop-twist-joy \
    teleop-twist-keyboard \
    urdf \
    xacro \
"

IMAGE_INSTALL_append = " \
    turtlebot3-applications \
    turtlebot3-applications-msgs \
    turtlebot3-autorace \
    ${TURTLEBOT3_PACKAGES_ADDITIONAL_SLAM_METHODS} \
    ${@bb.utils.contains('IMAGE_FEATURES', 'ros1-remote-roscore', '', d.getVar('TURTLEBOT3_PACKAGES_REMOTE_PC', True), d)} \
"
