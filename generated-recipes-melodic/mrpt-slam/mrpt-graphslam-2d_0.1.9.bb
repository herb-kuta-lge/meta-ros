# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "Implement graphSLAM using the mrpt-graphslam library, in an online fashion   	by directly reading measurements off ROS Topics."
AUTHOR = "Nikos Koukis <nickkouk@gmail.com>"
ROS_AUTHOR = "Nikos Koukis <nickkouk@gmail.com>"
HOMEPAGE = "http://www.mrpt.org/"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=14;endline=14;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "mrpt_slam"
ROS_BPN = "mrpt_graphslam_2d"

ROS_BUILD_DEPENDS = " \
    geometry-msgs \
    mrpt-bridge \
    mrpt-msgs \
    mrpt1 \
    multimaster-msgs-fkie \
    nav-msgs \
    roscpp \
    rospy \
    sensor-msgs \
    std-msgs \
    tf \
    tf2 \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    geometry-msgs \
    mrpt-bridge \
    mrpt-msgs \
    mrpt1 \
    multimaster-msgs-fkie \
    nav-msgs \
    roscpp \
    rospy \
    sensor-msgs \
    std-msgs \
    tf \
    tf2 \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    geometry-msgs \
    mrpt-bridge \
    mrpt-msgs \
    mrpt1 \
    multimaster-msgs-fkie \
    nav-msgs \
    roscpp \
    rospy \
    sensor-msgs \
    std-msgs \
    tf \
    tf2 \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/mrpt-ros-pkg-release/mrpt_slam-release/archive/release/melodic/mrpt_graphslam_2d/0.1.9-0.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "9eb3e7e04436850a81ca3a3b61bc1eaf"
SRC_URI[sha256sum] = "2581a0b10692921efe75fa9456cba7e3f39755e8ec223ad296f44944e1d721c4"
S = "${WORKDIR}/mrpt_slam-release-release-melodic-mrpt_graphslam_2d-0.1.9-0"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('mrpt-slam', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('mrpt-slam', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/mrpt-slam/mrpt-slam_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/mrpt-slam/mrpt-slam-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/mrpt-slam/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/mrpt-slam/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
