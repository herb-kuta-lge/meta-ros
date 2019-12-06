# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_eloquent
inherit ros_superflore_generated

DESCRIPTION = "C++ stdlib-compatible wrapper around tlsf allocator and ROS2 examples"
AUTHOR = "Chris Lalancette <clalancette@openrobotics.org>"
ROS_AUTHOR = "Jackie Kay <jackie@osrfoundation.org>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://package.xml;beginline=8;endline=8;md5=3badeab1074cb0c993003745c15d12f0"

ROS_CN = "realtime_support"
ROS_BPN = "tlsf_cpp"

ROS_BUILD_DEPENDS = " \
    ament-cmake \
    rclcpp \
    rmw \
    std-msgs \
    tlsf \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    ament-cmake \
    rclcpp \
    rmw \
    std-msgs \
    tlsf \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    ament-cmake-gtest \
    ament-lint-auto \
    ament-lint-common \
    rmw-implementation-cmake \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ros2-gbp/realtime_support-release/archive/release/eloquent/tlsf_cpp/0.8.2-1.tar.gz
ROS_BRANCH ?= "branch=release/eloquent/tlsf_cpp"
SRC_URI = "git://github.com/ros2-gbp/realtime_support-release;${ROS_BRANCH};protocol=https"
SRCREV = "12f9c7f6e02f16a1f8508282f3a153d69bdfa630"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "ament_cmake"

inherit ros_${ROS_BUILD_TYPE}
