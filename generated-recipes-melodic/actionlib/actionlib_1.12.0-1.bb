# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "The actionlib stack provides a standardized interface for     interfacing with preemptable tasks. Examples of this include moving     the base to a target location, performing a laser scan and returning     the resulting point cloud, detecting the handle of a door, etc."
AUTHOR = "Michael Carroll <michael@openrobotics.org>"
ROS_AUTHOR = "Eitan Marder-Eppstein"
HOMEPAGE = "http://www.ros.org/wiki/actionlib"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=13;endline=13;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "actionlib"
ROS_BPN = "actionlib"

ROS_BUILD_DEPENDS = " \
    actionlib-msgs \
    boost \
    message-generation \
    roscpp \
    rospy \
    rostest \
    std-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    actionlib-msgs \
    boost \
    roscpp \
    rospy \
    rostest \
    std-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_python-wxtools} \
    actionlib-msgs \
    boost \
    message-runtime \
    roscpp \
    roslib \
    rospy \
    rostest \
    rostopic \
    std-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    rosnode \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/ros-gbp/actionlib-release/archive/release/melodic/actionlib/1.12.0-1.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "61f153e33a25b8db5023ffa760e47095"
SRC_URI[sha256sum] = "cf5019b5b23a1aa16c1ad5afc47ce10630a512cdc621c926bc1920803dac9ca5"
S = "${WORKDIR}/actionlib-release-release-melodic-actionlib-1.12.0-1"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('actionlib', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('actionlib', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/actionlib/actionlib_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/actionlib/actionlib-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/actionlib/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/actionlib/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
