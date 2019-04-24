# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "Provide a node that extracts checkerboard corners from ROS images.     This package is still experimental and unstable.     Expect its APIs to change."
AUTHOR = "Vincent Rabaud <vincent.rabaud@gmail.com>"
ROS_AUTHOR = "Vijay Pradeep"
HOMEPAGE = "http://ros.org/wiki/image_cb_detector"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=12;endline=12;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "calibration"
ROS_BPN = "image_cb_detector"

ROS_BUILD_DEPENDS = " \
    actionlib \
    actionlib-msgs \
    calibration-msgs \
    cv-bridge \
    geometry-msgs \
    image-transport \
    message-filters \
    message-generation \
    roscpp \
    sensor-msgs \
    std-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    actionlib \
    actionlib-msgs \
    calibration-msgs \
    cv-bridge \
    geometry-msgs \
    image-transport \
    message-filters \
    message-runtime \
    roscpp \
    sensor-msgs \
    std-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    actionlib \
    actionlib-msgs \
    calibration-msgs \
    cv-bridge \
    geometry-msgs \
    image-transport \
    message-filters \
    message-runtime \
    roscpp \
    sensor-msgs \
    std-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/ros-gbp/calibration-release/archive/release/melodic/image_cb_detector/0.10.14-0.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "7ac417af6e0cfa37971e99009f089320"
SRC_URI[sha256sum] = "b979f5db4bef45b9072a8392e5abb0f8608a5ebd06ba6dbbb5c51cb48e2ed335"
S = "${WORKDIR}/calibration-release-release-melodic-image_cb_detector-0.10.14-0"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('calibration', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('calibration', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/calibration/calibration_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/calibration/calibration-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/calibration/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/calibration/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
