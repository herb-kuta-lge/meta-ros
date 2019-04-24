# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "Python library to assist in publishing markers easily"
AUTHOR = "David V. Lu!! <davidvlu@gmail.com>"
ROS_AUTHOR = "David V. Lu!!"
HOMEPAGE = "http://ros.org/wiki/easy_markers"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=8;endline=8;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "wu_ros_tools"
ROS_BPN = "easy_markers"

ROS_BUILD_DEPENDS = " \
    geometry-msgs \
    interactive-markers \
    roslib \
    rospy \
    tf \
    visualization-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    geometry-msgs \
    interactive-markers \
    roslib \
    rospy \
    tf \
    visualization-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    geometry-msgs \
    interactive-markers \
    roslib \
    rospy \
    tf \
    visualization-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/wu-robotics/wu_ros_tools/archive/release/melodic/easy_markers/0.2.4-0.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "033a236d037d0a5a29802f0dceee239e"
SRC_URI[sha256sum] = "64fea822e5e6ee2d58d4bfbd23519485c3197e5a8a8d74ef97ec91abc5e35088"
S = "${WORKDIR}/wu_ros_tools-release-melodic-easy_markers-0.2.4-0"

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
