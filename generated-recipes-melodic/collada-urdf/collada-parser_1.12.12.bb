# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "This package contains a C++ parser for the Collada robot     description format. The parser reads a Collada XML robot     description, and creates a C++ URDF model. Although it is possible     to directly use this parser when working with Collada robot     descriptions, the preferred user API is found in the urdf package."
AUTHOR = "Chris Lalancette <clalancette@osrfoundation.org>"
ROS_AUTHOR = "Rosen Diankov"
HOMEPAGE = "http://ros.org/wiki/collada_parser"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=19;endline=19;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "collada_urdf"
ROS_BPN = "collada_parser"

ROS_BUILD_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_liburdfdom-headers-dev} \
    class-loader \
    collada-dom \
    rosconsole \
    urdf \
    urdf-parser-plugin \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    urdf \
    urdf-parser-plugin \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    class-loader \
    collada-dom \
    rosconsole \
    urdf-parser-plugin \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/ros-gbp/collada_urdf-release/archive/release/melodic/collada_parser/1.12.12-0.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "a12ee1cf95f34626a52ff5ee4a13cf8c"
SRC_URI[sha256sum] = "8edfcf03b750d196cca3a360cb7fbf5b41033bef4594c4dfc2f4978d2f701310"
S = "${WORKDIR}/collada_urdf-release-release-melodic-collada_parser-1.12.12-0"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('collada-urdf', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('collada-urdf', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/collada-urdf/collada-urdf_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/collada-urdf/collada-urdf-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/collada-urdf/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/collada-urdf/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
