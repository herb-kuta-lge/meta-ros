# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "URDF robot description for Warthog"
AUTHOR = "Tony Baltovski <tbaltovski@clearpathrobotics.com>"
ROS_AUTHOR = "Ryan Gariepy <rgariepy@clearpathrobotics.com>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=9;endline=9;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "warthog"
ROS_BPN = "warthog_description"

ROS_BUILD_DEPENDS = " \
    roslaunch \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    robot-state-publisher \
    urdf \
    xacro \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    robot-state-publisher \
    urdf \
    xacro \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/clearpath-gbp/warthog-release/archive/release/melodic/warthog_description/0.1.1-2.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "954c0da6ff78e2ab2e1ec4450e3f8d6a"
SRC_URI[sha256sum] = "dd90723c17e49d8ee56e64008479d08bdbf4e3344ee37cca5612f54ec1ed2033"
S = "${WORKDIR}/warthog-release-release-melodic-warthog_description-0.1.1-2"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('warthog', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('warthog', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/warthog/warthog_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/warthog/warthog-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/warthog/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/warthog/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
