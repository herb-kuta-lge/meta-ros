# Copyright (c) 2019 LG Electronics, Inc.

inherit ros_distro_${ROS_DISTRO}
inherit ${ROS_DISTRO_TYPE}_image


TURTLEBOT3_PACKAGES_LIDAR ??= " \
    hls-lfcd-lds-driver \
"
TURTLEBOT3_PACKAGES_LIDAR_qemux86 = ""

TURTLEBOT3_PACKAGES_CAMERA ??= " \
    camera-info-manager \
    compressed-image-transport \
"

# From just above here: http://emanual.robotis.com/docs/en/platform/turtlebot3/appendix_raspi_cam/#run-raspicam-node
# XXX Don't have recipe for raspicam-node yet.
X_TURTLEBOT3_PACKAGES_CAMERA_append_raspberrypi3 = " \
    raspicam-node \
"

# From http://emanual.robotis.com/docs/en/platform/turtlebot3/raspberry_pi_3_setup/#3-install-dependent-packages-on-turtlebot-pc
# XXX "robot: needed?? Seems not.
IMAGE_INSTALL_append = " \
    ros-base \
    ${@bb.utils.contains('IMAGE_FEATURES', 'ros1-remote-roscore', 'turtlebot3-bringup', 'turtlebot3', d)} \
    turtlebot3-msgs \
    ${TURTLEBOT3_PACKAGES_CAMERA} \
    ${TURTLEBOT3_PACKAGES_LIDAR} \
"

IMAGE_INSTALL_append_ros1-distro = " \
    rosserial-python \
    tf \
"
