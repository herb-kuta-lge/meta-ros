# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "Interface for enforcing joint limits."
AUTHOR = "Bence Magyar <bence.magyar.robotics@gmail.com>"
ROS_AUTHOR = "Adolfo Rodriguez Tsouroukdissian"
HOMEPAGE = "https://github.com/ros-controls/ros_control/wiki"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=8;endline=8;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "ros_control"
ROS_BPN = "joint_limits_interface"

ROS_BUILD_DEPENDS = " \
    hardware-interface \
    liburdfdom-dev \
    roscpp \
    urdf \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    hardware-interface \
    liburdfdom-dev \
    roscpp \
    urdf \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    hardware-interface \
    liburdfdom-dev \
    roscpp \
    urdf \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    rostest \
    rosunit \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/ros-gbp/ros_control-release/archive/release/melodic/joint_limits_interface/0.15.1-0.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "f2f33591279f22adb36e6b3fa2e83929"
SRC_URI[sha256sum] = "c79c7fa6eaa2ccb35ed79b7dc6b125db16a98d298085a1fb1a2ed530e9817a64"
S = "${WORKDIR}/ros_control-release-release-melodic-joint_limits_interface-0.15.1-0"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('ros-control', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('ros-control', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/ros-control/ros-control_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/ros-control/ros-control-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/ros-control/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/ros-control/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
