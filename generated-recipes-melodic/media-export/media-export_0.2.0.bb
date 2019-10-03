# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "Placeholder package enabling generic export of media paths."
AUTHOR = "William Woodall <william@osrfoundation.org>"
ROS_AUTHOR = "Dave Hershberger"
HOMEPAGE = "http://ros.org/wiki/media_export"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=8;endline=8;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "media_export"
ROS_BPN = "media_export"

ROS_BUILD_DEPENDS = ""

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = ""

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/ros-gbp/media_export-release/archive/release/melodic/media_export/0.2.0-0.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "cb8242be44f235d4883958bdb5f18acb"
SRC_URI[sha256sum] = "dba5a590101d4f4969a5f963f8fba90fff5a095e0c19770166787d22bdb799e3"
S = "${WORKDIR}/media_export-release-release-melodic-media_export-0.2.0-0"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('media-export', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('media-export', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/media-export/media-export_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/media-export/media-export-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/media-export/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/media-export/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
