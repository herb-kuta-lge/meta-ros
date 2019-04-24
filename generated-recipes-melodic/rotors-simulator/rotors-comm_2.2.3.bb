# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "RotorS specific messages and services."
AUTHOR = "Fadri Furrer <fadri.furrer@mavt.ethz.ch>"
ROS_AUTHOR = "Fadri Furrer"
HOMEPAGE = "https://github.com/ethz-asl/rotors_simulator"
SECTION = "devel"
LICENSE = "ASL-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=15;endline=15;md5=5f4e9e9dcee74b02aa26af144fe2f0af"

ROS_CN = "rotors_simulator"
ROS_BPN = "rotors_comm"

ROS_BUILD_DEPENDS = " \
    cmake-modules \
    geometry-msgs \
    message-generation \
    message-runtime \
    octomap-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    cmake-modules \
    geometry-msgs \
    message-generation \
    message-runtime \
    octomap-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    cmake-modules \
    geometry-msgs \
    message-generation \
    message-runtime \
    octomap-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/ethz-asl/rotors_simulator-release/archive/release/melodic/rotors_comm/2.2.3-0.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "47eb5d5258d2dd9e49fbf70eef0b23ef"
SRC_URI[sha256sum] = "6f5fc3033ab80590b10c00fc416c36221be5abd5756b389577e5f3e13dbb346c"
S = "${WORKDIR}/rotors_simulator-release-release-melodic-rotors_comm-2.2.3-0"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('rotors-simulator', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('rotors-simulator', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rotors-simulator/rotors-simulator_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rotors-simulator/rotors-simulator-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rotors-simulator/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rotors-simulator/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
