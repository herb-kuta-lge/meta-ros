# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "Provides nonlinear state estimation through sensor fusion of an abritrary number of sensors."
AUTHOR = "Tom Moore <ayrton04@gmail.com>"
ROS_AUTHOR = "Tom Moore <ayrton04@gmail.com>"
HOMEPAGE = "http://ros.org/wiki/robot_localization"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=9;endline=9;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "robot_localization"
ROS_BPN = "robot_localization"

ROS_BUILD_DEPENDS = " \
    cmake-modules \
    diagnostic-msgs \
    diagnostic-updater \
    eigen-conversions \
    geographic-msgs \
    geometry-msgs \
    libeigen \
    message-filters \
    message-generation \
    nav-msgs \
    nodelet \
    python-catkin-pkg \
    roscpp \
    roslint \
    sensor-msgs \
    std-msgs \
    std-srvs \
    tf2 \
    tf2-geometry-msgs \
    tf2-ros \
    xmlrpcpp \
    yaml-cpp \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    cmake-modules \
    diagnostic-msgs \
    diagnostic-updater \
    eigen-conversions \
    geographic-msgs \
    geometry-msgs \
    libeigen \
    message-filters \
    nav-msgs \
    nodelet \
    roscpp \
    sensor-msgs \
    std-msgs \
    std-srvs \
    tf2 \
    tf2-geometry-msgs \
    tf2-ros \
    xmlrpcpp \
    yaml-cpp \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    cmake-modules \
    diagnostic-msgs \
    diagnostic-updater \
    eigen-conversions \
    geographic-msgs \
    geometry-msgs \
    libeigen \
    message-filters \
    message-runtime \
    nav-msgs \
    nodelet \
    roscpp \
    sensor-msgs \
    std-msgs \
    std-srvs \
    tf2 \
    tf2-geometry-msgs \
    tf2-ros \
    xmlrpcpp \
    yaml-cpp \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    rosbag \
    rostest \
    rosunit \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/cra-ros-pkg/robot_localization-release/archive/release/melodic/robot_localization/2.6.5-1.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "d483f3804da541b8cd6ac3d794724c7f"
SRC_URI[sha256sum] = "971bcda9b4564009674b21d1a344a14ef6d7458f3156b9110ed36b02d933b189"
S = "${WORKDIR}/robot_localization-release-release-melodic-robot_localization-2.6.5-1"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('robot-localization', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('robot-localization', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/robot-localization/robot-localization_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/robot-localization/robot-localization-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/robot-localization/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/robot-localization/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
