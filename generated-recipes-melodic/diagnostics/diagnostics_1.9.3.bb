# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "diagnostics"
AUTHOR = "Brice Rebsamen <brice.rebsamen@gmail.com>"
ROS_AUTHOR = "Kevin Watts"
HOMEPAGE = "http://www.ros.org/wiki/diagnostics"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=10;endline=10;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "diagnostics"
ROS_BPN = "diagnostics"

ROS_BUILD_DEPENDS = ""

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    diagnostic-aggregator \
    diagnostic-analysis \
    diagnostic-common-diagnostics \
    diagnostic-updater \
    self-test \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    diagnostic-aggregator \
    diagnostic-analysis \
    diagnostic-common-diagnostics \
    diagnostic-updater \
    self-test \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/ros-gbp/diagnostics-release/archive/release/melodic/diagnostics/1.9.3-0.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "242d26ecde65e705604256ccf79e3622"
SRC_URI[sha256sum] = "dec737018e36952a217efb3fda54489da280470d5230f4a5fa282027317cc03f"
S = "${WORKDIR}/diagnostics-release-release-melodic-diagnostics-1.9.3-0"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('diagnostics', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('diagnostics', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/diagnostics/diagnostics_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/diagnostics/diagnostics-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/diagnostics/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/diagnostics/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
