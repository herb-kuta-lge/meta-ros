# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "Allows multipoint communication between rosserial      nodes connected to an xbee. All nodes communicate back      to a master xbee connected to a computer running ROS.       This software currently only works with Series 1 Xbees.       This pkg includes python code from the python-xbee project:      http://code.google.com/p/python-xbee/"
AUTHOR = "Paul Bouchier <paul.bouchier@gmail.com>"
ROS_AUTHOR = "Adam Stambler"
HOMEPAGE = "http://ros.org/wiki/rosserial_xbee"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=16;endline=16;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "rosserial"
ROS_BPN = "rosserial_xbee"

ROS_BUILD_DEPENDS = ""

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_python-serial} \
    diagnostic-msgs \
    rospy \
    rosserial-msgs \
    rosserial-python \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_python-serial} \
    diagnostic-msgs \
    rospy \
    rosserial-msgs \
    rosserial-python \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/ros-gbp/rosserial-release/archive/release/melodic/rosserial_xbee/0.8.0-0.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "71e5e25305517984a19a07c9813b9afe"
SRC_URI[sha256sum] = "6efbc90d56bf2d6caad0e830fe206c9c7cb3c1edfe08507caf2721589f5d7a5b"
S = "${WORKDIR}/rosserial-release-release-melodic-rosserial_xbee-0.8.0-0"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('rosserial', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('rosserial', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rosserial/rosserial_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rosserial/rosserial-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rosserial/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rosserial/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
