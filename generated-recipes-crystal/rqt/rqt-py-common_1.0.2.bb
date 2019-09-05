# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "rqt_py_common provides common functionality for rqt plugins written in Python.     Despite no plugin is provided, this package is part of the rqt_common_plugins     repository to keep refactoring generic functionality from these common plugins     into this package as easy as possible.      Functionality included in this package should cover generic ROS concepts and     should not introduce any special dependencies beside &quot;ros_base&quot;."
AUTHOR = "Dorian Scholz <scholz@sim.tu-darmstadt.de>"
ROS_AUTHOR = "Dorian Scholz"
HOMEPAGE = "http://ros.org/wiki/rqt_py_common"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=14;endline=14;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "rqt"
ROS_BPN = "rqt_py_common"

ROS_BUILD_DEPENDS = " \
    qtbase \
    rclpy \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
    rosidl-default-generators-native \
"

ROS_EXPORT_DEPENDS = " \
    qtbase \
    rclpy \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    python-qt-binding \
    qt-gui \
    qtbase \
    rclpy \
    rosidl-default-runtime \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    ament-cmake-pytest \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/ros2-gbp/rqt-release/archive/release/crystal/rqt_py_common/1.0.2-0.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "5f4cb9241c947ab95542f162ccabccbb"
SRC_URI[sha256sum] = "9aab24d68ce8ddf28c893ad4e015ee82092f937f456cbf904e8eba4bf7db4fcd"
S = "${WORKDIR}/rqt-release-release-crystal-rqt_py_common-1.0.2-0"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('rqt', d)}"
ROS_BUILD_TYPE = "ament_cmake"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('rqt', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rqt/rqt_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rqt/rqt-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rqt/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rqt/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
