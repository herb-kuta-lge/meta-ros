# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "Wrapper around ogre3d, it provides a fixed CMake module and an ExternalProject build of ogre."
AUTHOR = "William Woodall <william@osrfoundation.org>"
HOMEPAGE = "https://www.ogre3d.org/"
SECTION = "devel"
LICENSE = "Apache-2.0 & MIT"
LIC_FILES_CHKSUM = "file://package.xml;beginline=12;endline=12;md5=f12ef8c0445c08084ae92cf2dcb7ee92"

ROS_CN = "rviz"
ROS_BPN = "rviz_ogre_vendor"

ROS_BUILD_DEPENDS = " \
    freetype \
    libx11 \
    libxaw \
    libxrandr \
    mesa \
    pkgconfig \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = " \
    freetype \
    libx11 \
    libxaw \
    libxrandr \
    mesa \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    freetype \
    libx11 \
    libxaw \
    libxrandr \
    mesa \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/ros2-gbp/rviz-release/archive/release/dashing/rviz_ogre_vendor/6.1.1-1.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "7fe8678a27b540db1aecacf71ff87ed9"
SRC_URI[sha256sum] = "69608227e5a94bc27384629fa74a4ba63e9fcea71198f68c41a5412217ee6efb"
S = "${WORKDIR}/rviz-release-release-dashing-rviz_ogre_vendor-6.1.1-1"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('rviz', d)}"
ROS_BUILD_TYPE = "ament_cmake"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('rviz', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rviz/rviz_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rviz/rviz-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rviz/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rviz/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
