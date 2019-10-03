# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "Calibrate a Robot"
AUTHOR = "Michael Ferguson <mike@vanadiumlabs.com>"
ROS_AUTHOR = "Michael Ferguson"
HOMEPAGE = "http://ros.org/wiki/robot_calibration"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=9;endline=9;md5=c93e37fc0c6f510db5735eb91dcc1550"

ROS_CN = "robot_calibration"
ROS_BPN = "robot_calibration"

ROS_BUILD_DEPENDS = " \
    actionlib \
    camera-calibration-parsers \
    ceres-solver \
    control-msgs \
    cv-bridge \
    geometry-msgs \
    gflags \
    kdl-parser \
    moveit-msgs \
    nav-msgs \
    orocos-kdl \
    pluginlib \
    protobuf \
    robot-calibration-msgs \
    rosbag \
    roscpp \
    sensor-msgs \
    std-msgs \
    suitesparse-cholmod \
    suitesparse-cxsparse \
    tf \
    tf2-geometry-msgs \
    tf2-ros \
    visualization-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    actionlib \
    camera-calibration-parsers \
    ceres-solver \
    control-msgs \
    cv-bridge \
    geometry-msgs \
    kdl-parser \
    moveit-msgs \
    nav-msgs \
    orocos-kdl \
    pluginlib \
    protobuf \
    robot-calibration-msgs \
    rosbag \
    roscpp \
    sensor-msgs \
    std-msgs \
    suitesparse-cholmod \
    suitesparse-cxsparse \
    tf \
    tf2-geometry-msgs \
    tf2-ros \
    visualization-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    actionlib \
    camera-calibration-parsers \
    ceres-solver \
    control-msgs \
    cv-bridge \
    geometry-msgs \
    kdl-parser \
    moveit-msgs \
    nav-msgs \
    orocos-kdl \
    pluginlib \
    protobuf \
    robot-calibration-msgs \
    rosbag \
    roscpp \
    sensor-msgs \
    std-msgs \
    suitesparse-cholmod \
    suitesparse-cxsparse \
    tf \
    tf2-geometry-msgs \
    tf2-ros \
    visualization-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/ros-gbp/robot_calibration-release/archive/release/melodic/robot_calibration/0.6.0-0.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "c9293d1b060d6ccf5cdf8a8c57723177"
SRC_URI[sha256sum] = "bd39604970ae9dd0a27d006fac49afbf50604f89ff7d7218e4967c91d866b175"
S = "${WORKDIR}/robot_calibration-release-release-melodic-robot_calibration-0.6.0-0"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('robot-calibration', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('robot-calibration', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/robot-calibration/robot-calibration_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/robot-calibration/robot-calibration-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/robot-calibration/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/robot-calibration/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
