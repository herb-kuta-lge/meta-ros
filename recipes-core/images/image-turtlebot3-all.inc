# Copyright (c) 2019 LG Electronics, Inc.

require image-turtlebot3-core.inc

# http://emanual.robotis.com/docs/en/platform/turtlebot3/slam/#run-slam-nodes
# XXX Need recipes for various dependencies.
TURTLEBOT3_SLAM_METHODS ??= " \
    cartographer-ros \
    slam-karto \
"
TURTLEBOT3_SLAM_METHODS = ""

IMAGE_INSTALL_append = " \
    turtlebot3-applications \
    turtlebot3-applications-msgs \
    turtlebot3-autorace \
    ${TURTLEBOT3_SLAM_METHODS} \
"

# From http://emanual.robotis.com/docs/en/platform/turtlebot3/autonomous_driving/#tutorials-3-install-additional-dependent-packages:
# XXX Add to autorace recipe(s) RDEPENDS instead of here:
IMAGE_INSTALL_append = " image-transport vision-opencv opencv image-proc"

# From: http://emanual.robotis.com/docs/en/platform/turtlebot3/applications/#automatic-parking:
# Add to turtlebot3-automatic-parking RDEPENDS instead of here:
IMAGE_INSTALL_append = " python-numpy"
