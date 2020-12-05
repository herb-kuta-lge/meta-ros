# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_eloquent
inherit ros_superflore_generated

DESCRIPTION = "TODO"
AUTHOR = "Carlos Orduno <carlos.a.orduno@intel.com>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=8;endline=8;md5=82f0323c08605e5b6f343b05213cf7cc"

ROS_CN = "navigation2"
ROS_BPN = "nav2_system_tests"

ROS_BUILD_DEPENDS = " \
    gazebo-ros-pkgs \
    geometry-msgs \
    launch-ros \
    launch-testing \
    nav-msgs \
    nav2-amcl \
    nav2-bringup \
    nav2-common \
    nav2-lifecycle-manager \
    nav2-msgs \
    nav2-navfn-planner \
    nav2-planner \
    nav2-util \
    rclcpp \
    rclpy \
    std-msgs \
    tf2-geometry-msgs \
    visualization-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    gazebo-ros-pkgs \
    geometry-msgs \
    launch-ros \
    launch-testing \
    lcov \
    nav-msgs \
    nav2-amcl \
    nav2-bringup \
    nav2-lifecycle-manager \
    nav2-msgs \
    nav2-navfn-planner \
    nav2-planner \
    nav2-util \
    navigation2 \
    rclcpp \
    rclpy \
    robot-state-publisher \
    std-msgs \
    tf2-geometry-msgs \
    visualization-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    ament-cmake-gtest \
    ament-cmake-pytest \
    ament-lint-auto \
    ament-lint-common \
    launch \
    launch-ros \
    launch-testing \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/SteveMacenski/navigation2-release/archive/release/eloquent/nav2_system_tests/0.3.5-1.tar.gz
ROS_BRANCH ?= "branch=release/eloquent/nav2_system_tests"
SRC_URI = "git://github.com/SteveMacenski/navigation2-release;${ROS_BRANCH};protocol=https"
SRCREV = "82d1e0c9b079abba2a106422df6185ad897748b6"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "ament_cmake"

inherit ros_${ROS_BUILD_TYPE}
