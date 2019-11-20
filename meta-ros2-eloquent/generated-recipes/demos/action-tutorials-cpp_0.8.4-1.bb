# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_eloquent
inherit ros_superflore_generated

DESCRIPTION = "C++ action tutorial cpp code"
AUTHOR = "Jacob Perron <jacob@openrobotics.org>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=8;endline=8;md5=12c26a18c7f493fdc7e8a93b16b7c04f"

ROS_CN = "demos"
ROS_BPN = "action_tutorials_cpp"

ROS_BUILD_DEPENDS = " \
    action-tutorials-interfaces \
    rclcpp \
    rclcpp-action \
    rclcpp-components \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = " \
    action-tutorials-interfaces \
    rclcpp \
    rclcpp-action \
    rclcpp-components \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    action-tutorials-interfaces \
    rclcpp \
    rclcpp-action \
    rclcpp-components \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    ament-lint-auto \
    ament-lint-common \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ros2-gbp/demos-release/archive/release/eloquent/action_tutorials_cpp/0.8.4-1.tar.gz
ROS_BRANCH ?= "branch=release/eloquent/action_tutorials_cpp"
SRC_URI = "git://github.com/ros2-gbp/demos-release;${ROS_BRANCH};protocol=https"
SRCREV = "3e749ad40e2ae3c0112dab5e71b64f29e544ccbd"
S = "${WORKDIR}/git"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('demos', d)}"
ROS_BUILD_TYPE = "ament_cmake"

inherit ros_${ROS_BUILD_TYPE}
