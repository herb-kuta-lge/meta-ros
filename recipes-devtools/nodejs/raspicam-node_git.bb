# Copyright (c) 2019 LG Electronics, Inc.

SUMMARY = "ROS node for the Raspberry Pi Camera Module"
HOMEPAGE = "https://github.com/UbiquityRobotics/raspicam_node"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3045e9d5326c8f5c311a93f8a9abb873"

PV = "1.2+git${SRCPV}"

SRCREV = "195694afee514370aaf28712e1e09c48bdaf2af7"
SRC_URI = "git://github.com/UbiquityRobotics/raspicam_node;protocol=https;branch=kinetic"
S = "${WORKDIR}/git"

inherit cmake catkin ros_opt_prefix

DEPENDS += "catkin-native compressed-image-transport camera-info-manager diagnostic-updater std-srvs userland"

# To package /opt/ros/melodic/lib/raspicam_node/raspicam_node
ROS_BPN = "raspicam_node"

COMPATIBLE_MACHINE = "^rpi$"
