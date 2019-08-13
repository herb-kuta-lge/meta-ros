# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "rqt_robot_dashboard provides an infrastructure for building robot dashboard plugins in rqt."
AUTHOR = "Aaron Blasdel <ablasdel@gmail.com>"
ROS_AUTHOR = "Ze'ev Klapow"
HOMEPAGE = "http://wiki.ros.org/rqt_robot_dashboard"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=7;endline=7;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "rqt_robot_dashboard"
ROS_BPN = "rqt_robot_dashboard"

ROS_BUILD_DEPENDS = ""

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    diagnostic-msgs \
    python-qt-binding \
    qt-gui \
    rospy \
    rqt-console \
    rqt-gui \
    rqt-gui-py \
    rqt-nav-view \
    rqt-robot-monitor \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    diagnostic-msgs \
    python-qt-binding \
    qt-gui \
    rospy \
    rqt-console \
    rqt-gui \
    rqt-gui-py \
    rqt-nav-view \
    rqt-robot-monitor \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/ros-gbp/rqt_robot_dashboard-release/archive/release/melodic/rqt_robot_dashboard/0.5.7-0.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "18fa7cd5e3c9c9a393b99466806cac00"
SRC_URI[sha256sum] = "19eb8bffb5697ef7e7d7bc23cf7cda4e3446e6415a40e53987095b85bb9b8568"
S = "${WORKDIR}/rqt_robot_dashboard-release-release-melodic-rqt_robot_dashboard-0.5.7-0"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('rqt-robot-dashboard', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('rqt-robot-dashboard', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rqt-robot-dashboard/rqt-robot-dashboard_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rqt-robot-dashboard/rqt-robot-dashboard-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rqt-robot-dashboard/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rqt-robot-dashboard/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
