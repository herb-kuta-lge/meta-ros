# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "posedetection_msgs provides messages and services to facilitate passing pose detection results and features."
AUTHOR = "Kei Okada <k-okada@jsk.t.u-tokyo.ac.jp>"
ROS_AUTHOR = "Rosen Diankov"
HOMEPAGE = "http://ros.org/wiki/posedetection_msgs"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=7;endline=7;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "jsk_common_msgs"
ROS_BPN = "posedetection_msgs"

ROS_BUILD_DEPENDS = " \
    cv-bridge \
    geometry-msgs \
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
    cv-bridge \
    geometry-msgs \
    message-filters \
    message-runtime \
    roscpp \
    sensor-msgs \
    std-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    cv-bridge \
    geometry-msgs \
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

SRC_URI = "https://github.com/tork-a/jsk_common_msgs-release/archive/release/melodic/posedetection_msgs/4.3.1-0.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "8892d61446fd59cca9cf474b70cf87fb"
SRC_URI[sha256sum] = "1538ca360bd31dcfa85392ba78374d072d64a3426425c64b08d8db149ea5cd3a"
S = "${WORKDIR}/jsk_common_msgs-release-release-melodic-posedetection_msgs-4.3.1-0"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('jsk-common-msgs', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('jsk-common-msgs', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/jsk-common-msgs/jsk-common-msgs_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/jsk-common-msgs/jsk-common-msgs-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/jsk-common-msgs/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/jsk-common-msgs/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
