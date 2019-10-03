# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "The quaternion_operation package"
AUTHOR = "Masaya Kataoka <ms.kataoka@gmail.com>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=9;endline=9;md5=12c26a18c7f493fdc7e8a93b16b7c04f"

ROS_CN = "quaternion_operation"
ROS_BPN = "quaternion_operation"

ROS_BUILD_DEPENDS = " \
    geometry-msgs \
    libeigen \
    roscpp \
    rostest \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    geometry-msgs \
    libeigen \
    roscpp \
    rostest \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    geometry-msgs \
    libeigen \
    roscpp \
    rostest \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/OUXT-Polaris/quaternion_operation-release/archive/release/melodic/quaternion_operation/0.0.3-2.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "5031ccc6a97592fe3dc00023ad0c4bc2"
SRC_URI[sha256sum] = "db2cc70403a27df5a67db21c742c28728723a67b4c8f0ab51384e99d6692b32f"
S = "${WORKDIR}/quaternion_operation-release-release-melodic-quaternion_operation-0.0.3-2"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('quaternion-operation', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('quaternion-operation', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/quaternion-operation/quaternion-operation_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/quaternion-operation/quaternion-operation-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/quaternion-operation/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/quaternion-operation/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
