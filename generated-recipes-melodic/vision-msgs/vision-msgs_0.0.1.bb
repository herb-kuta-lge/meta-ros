# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "Messages for interfacing with various computer vision pipelines, such as     object detectors."
AUTHOR = "Adam Allevato <adam.d.allevato@gmail.com>"
ROS_AUTHOR = "Adam Allevato <adam.d.allevato@gmail.com>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=13;endline=13;md5=12c26a18c7f493fdc7e8a93b16b7c04f"

ROS_CN = "vision_msgs"
ROS_BPN = "vision_msgs"

ROS_BUILD_DEPENDS = " \
    geometry-msgs \
    message-generation \
    message-runtime \
    sensor-msgs \
    std-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    geometry-msgs \
    message-generation \
    message-runtime \
    sensor-msgs \
    std-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    geometry-msgs \
    message-generation \
    message-runtime \
    sensor-msgs \
    std-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    rosunit \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/Kukanani/vision_msgs-release/archive/release/melodic/vision_msgs/0.0.1-0.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "a278f89fb62599486635f366fbb3a1ec"
SRC_URI[sha256sum] = "aea7d0a539a76bbd0072f74bb3c7ebc0a7f3b3277963f8d696de42a5c4066e90"
S = "${WORKDIR}/vision_msgs-release-release-melodic-vision_msgs-0.0.1-0"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('vision-msgs', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('vision-msgs', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/vision-msgs/vision-msgs_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/vision-msgs/vision-msgs-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/vision-msgs/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/vision-msgs/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
