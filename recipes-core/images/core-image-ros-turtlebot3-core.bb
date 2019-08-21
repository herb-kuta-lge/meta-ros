# Copyright (c) 2019 LG Electronics, Inc.

require ${COREBASE}/meta/recipes-core/images/core-image-minimal.bb

SUMMARY = "ROS image containing core TurtleBot 3 packages"
DESCRIPTION = "${SUMMARY}"

require image-turtlebot3-core.inc
