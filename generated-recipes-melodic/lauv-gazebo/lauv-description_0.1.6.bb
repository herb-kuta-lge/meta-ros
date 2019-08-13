# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "Robot description files for the LAUV."
AUTHOR = "Musa Morena Marcusso Manhaes <musa.marcusso@de.bosch.com>"
ROS_AUTHOR = "Musa Morena Marcusso Manhaes <musa.marcusso@de.bosch.com>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=11;endline=11;md5=82f0323c08605e5b6f343b05213cf7cc"

ROS_CN = "lauv_gazebo"
ROS_BPN = "lauv_description"

ROS_BUILD_DEPENDS = ""

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    gazebo-ros \
    robot-state-publisher \
    uuv-assistants \
    uuv-descriptions \
    uuv-gazebo-ros-plugins \
    uuv-sensor-ros-plugins \
    xacro \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    rostest \
    rosunit \
    xacro \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/uuvsimulator/lauv_gazebo-release/archive/release/melodic/lauv_description/0.1.6-0.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "c7919e7673ac7e51c5fbb7ddc6a7cf6b"
SRC_URI[sha256sum] = "a3cd50cb68d4034daf6aa283fee53329c9e70c3177c0fe1bfdd169a236e4ab5c"
S = "${WORKDIR}/lauv_gazebo-release-release-melodic-lauv_description-0.1.6-0"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('lauv-gazebo', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('lauv-gazebo', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/lauv-gazebo/lauv-gazebo_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/lauv-gazebo/lauv-gazebo-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/lauv-gazebo/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/lauv-gazebo/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
