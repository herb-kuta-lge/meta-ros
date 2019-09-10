# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "The tuw_geometry package"
AUTHOR = "Markus Bader <markus.bader@tuwien.ac.at>"
ROS_AUTHOR = "Markus Bader"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=13;endline=13;md5=2c00b8d2854109dbebef7818b4dae1e2"

ROS_CN = "tuw_geometry"
ROS_BPN = "tuw_geometry"

ROS_BUILD_DEPENDS = " \
    boost \
    cv-bridge \
    libeigen \
    roscpp \
    rospy \
    std-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    cv-bridge \
    roscpp \
    rospy \
    std-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    cv-bridge \
    roscpp \
    rospy \
    std-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    rosunit \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/tuw-robotics/tuw_geometry-release/archive/release/melodic/tuw_geometry/0.0.3-0.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "9035d04f4a7d8a49a224e4d2a399002b"
SRC_URI[sha256sum] = "96df80626ab8ecd101acb1f972c815be0d105e9e5539e73d691ea3b2ef5d3e84"
S = "${WORKDIR}/tuw_geometry-release-release-melodic-tuw_geometry-0.0.3-0"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('tuw-geometry', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('tuw-geometry', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/tuw-geometry/tuw-geometry_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/tuw-geometry/tuw-geometry-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/tuw-geometry/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/tuw-geometry/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
