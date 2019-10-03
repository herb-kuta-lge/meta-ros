# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "Simple Kalman Filter in Python"
AUTHOR = "David V. Lu!! <davidvlu@gmail.com>"
ROS_AUTHOR = "David V. Lu!!"
HOMEPAGE = "http://ros.org/wiki/kalman_filter"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=8;endline=8;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "wu_ros_tools"
ROS_BPN = "kalman_filter"

ROS_BUILD_DEPENDS = " \
    rospy \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    rospy \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    rospy \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/wu-robotics/wu_ros_tools/archive/release/melodic/kalman_filter/0.2.4-0.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "ad040a33af771afc7d750458b49b0498"
SRC_URI[sha256sum] = "dae8cee834dfdb902000812bf4e6141f678bf813a077dd6b6f1580a90a909876"
S = "${WORKDIR}/wu_ros_tools-release-melodic-kalman_filter-0.2.4-0"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('wu-ros-tools', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('wu-ros-tools', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/wu-ros-tools/wu-ros-tools_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/wu-ros-tools/wu-ros-tools-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/wu-ros-tools/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/wu-ros-tools/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
