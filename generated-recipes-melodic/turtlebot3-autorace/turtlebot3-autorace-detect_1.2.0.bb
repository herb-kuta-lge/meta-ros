# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "AutoRace ROS packages for feature detection with TurtleBot3 Auto"
AUTHOR = "Pyo <pyo@robotis.com>"
ROS_AUTHOR = "Gilbert <kkjong@robotis.com>"
HOMEPAGE = "http://wiki.ros.org/turtlebot3_autorace_detect"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=8;endline=8;md5=3dce4ba60d7e51ec64f3c3dc18672dd3"

ROS_CN = "turtlebot3_autorace"
ROS_BPN = "turtlebot3_autorace_detect"

ROS_BUILD_DEPENDS = " \
    dynamic-reconfigure \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    dynamic-reconfigure \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    cv-bridge \
    dynamic-reconfigure \
    geometry-msgs \
    move-base-msgs \
    nav-msgs \
    python-enum34 \
    python-numpy \
    python-opencv \
    rospy \
    sensor-msgs \
    std-msgs \
    tf \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/ROBOTIS-GIT-release/turtlebot3_autorace-release/archive/release/melodic/turtlebot3_autorace_detect/1.2.0-0.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "15845943e6cf1317ea1f8349e802dbfc"
SRC_URI[sha256sum] = "51d9465e570d60f2d1fe2500d9c9f285a801800c131f38fba376b0034eff3288"
S = "${WORKDIR}/turtlebot3_autorace-release-release-melodic-turtlebot3_autorace_detect-1.2.0-0"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('turtlebot3-autorace', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('turtlebot3-autorace', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/turtlebot3-autorace/turtlebot3-autorace_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/turtlebot3-autorace/turtlebot3-autorace-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/turtlebot3-autorace/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/turtlebot3-autorace/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
