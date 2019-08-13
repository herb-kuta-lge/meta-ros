# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "Packages for working with Ridgeback from a ROS desktop."
AUTHOR = "Mike Purvis <mpurvis@clearpathrobotics.com>"
HOMEPAGE = "http://wiki.ros.org/ridgeback_desktop"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=9;endline=9;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "ridgeback_desktop"
ROS_BPN = "ridgeback_desktop"

ROS_BUILD_DEPENDS = ""

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    ridgeback-msgs \
    ridgeback-viz \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    ridgeback-msgs \
    ridgeback-viz \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/clearpath-gbp/ridgeback_desktop-release/archive/release/melodic/ridgeback_desktop/0.1.1-1.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "f1b1d0555e7cd63f1d4863052acfab12"
SRC_URI[sha256sum] = "fa60a97d87f148815a154f6790fbf49f532669678b7ff381326cbf2797ee00f1"
S = "${WORKDIR}/ridgeback_desktop-release-release-melodic-ridgeback_desktop-0.1.1-1"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('ridgeback-desktop', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('ridgeback-desktop', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/ridgeback-desktop/ridgeback-desktop_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/ridgeback-desktop/ridgeback-desktop-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/ridgeback-desktop/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/ridgeback-desktop/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
