# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "Interface for using ROS with the <a href="http://gazebosim.org/">Gazebo</a> simulator."
AUTHOR = "Jose Luis Rivero <jrivero@osrfoundation.org>"
ROS_AUTHOR = "John Hsu"
HOMEPAGE = "http://gazebosim.org/tutorials?cat=connect_ros"
SECTION = "devel"
LICENSE = "BSD-2"
LIC_FILES_CHKSUM = "file://package.xml;beginline=12;endline=12;md5=8caad55b0e7a31e039fbcff07dba789e"

ROS_CN = "gazebo_ros_pkgs"
ROS_BPN = "gazebo_ros_pkgs"

ROS_BUILD_DEPENDS = ""

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    gazebo-msgs \
    gazebo-plugins \
    gazebo-ros \
    gazebo-rosdev \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/ros2-gbp/gazebo_ros_pkgs-release/archive/release/dashing/gazebo_ros_pkgs/3.3.1-1.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "ad1e81a5d2e0abbc4d74cdffab77b9a2"
SRC_URI[sha256sum] = "96a94ecf315ee003373c32c2c372d4c660f0cb0b30b786c0ce9ca16b643cd11f"
S = "${WORKDIR}/gazebo_ros_pkgs-release-release-dashing-gazebo_ros_pkgs-3.3.1-1"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('gazebo-ros-pkgs', d)}"
ROS_BUILD_TYPE = "ament_cmake"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('gazebo-ros-pkgs', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/gazebo-ros-pkgs/gazebo-ros-pkgs_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/gazebo-ros-pkgs/gazebo-ros-pkgs-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/gazebo-ros-pkgs/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/gazebo-ros-pkgs/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
