# Copyright (c) 2019 LG Electronics, Inc.

require ros-image-roscore.bb

SUMMARY = "Core ROS image containing core TurtleBot 3 packages"
DESCRIPTION = "${SUMMARY}"

IMAGE_INSTALL_append = " \
    packagegroup-ros-turtlebot3-core \
"
