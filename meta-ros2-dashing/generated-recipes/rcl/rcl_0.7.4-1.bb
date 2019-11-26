# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_dashing
inherit ros_superflore_generated

DESCRIPTION = "The ROS client library common implementation.     This package contains an API which builds on the ROS middleware API and is optionally built upon by the other ROS client libraries."
AUTHOR = "William Woodall <william@osrfoundation.org>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=10;endline=10;md5=12c26a18c7f493fdc7e8a93b16b7c04f"

ROS_CN = "rcl"
ROS_BPN = "rcl"

ROS_BUILD_DEPENDS = " \
    rcl-interfaces \
    rcl-logging-noop \
    rcutils \
    rmw-implementation \
    rosidl-generator-c \
    tinydir-vendor \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-ros-native \
"

ROS_EXPORT_DEPENDS = " \
    rcl-interfaces \
    rcl-logging-noop \
    rcutils \
    rmw \
    rmw-implementation \
    rosidl-generator-c \
    tinydir-vendor \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    ament-cmake \
    rcl-interfaces \
    rcl-logging-noop \
    rcutils \
    rmw-implementation \
    rosidl-default-runtime \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    ament-cmake-gtest \
    ament-cmake-pytest \
    ament-lint-auto \
    ament-lint-common \
    launch \
    launch-testing \
    launch-testing-ament-cmake \
    osrf-testing-tools-cpp \
    rmw \
    rmw-implementation-cmake \
    test-msgs \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ros2-gbp/rcl-release/archive/release/dashing/rcl/0.7.4-1.tar.gz
ROS_BRANCH ?= "branch=release/dashing/rcl"
SRC_URI = "git://github.com/ros2-gbp/rcl-release;${ROS_BRANCH};protocol=https"
SRCREV = "44ebb8c82b68b9ab5dc341f249bd2d001808d0d2"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "ament_cmake"

inherit ros_${ROS_BUILD_TYPE}
