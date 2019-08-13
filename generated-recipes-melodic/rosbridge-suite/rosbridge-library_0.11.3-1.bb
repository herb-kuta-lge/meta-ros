# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "The core rosbridge package, repsonsible for interpreting JSON andperforming     the appropriate ROS action, like subscribe, publish, call service, and     interact with params."
AUTHOR = "Russell Toris <rctoris@wpi.edu>"
ROS_AUTHOR = "Jonathan Mace <jonathan.c.mace@gmail.com>"
HOMEPAGE = "http://ros.org/wiki/rosbridge_library"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=11;endline=11;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "rosbridge_suite"
ROS_BPN = "rosbridge_library"

ROS_BUILD_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_python-imaging} \
    geometry-msgs \
    message-generation \
    python-bson \
    std-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_python-imaging} \
    geometry-msgs \
    message-runtime \
    python-bson \
    roscpp \
    rosgraph \
    rospy \
    rosservice \
    rostopic \
    std-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    actionlib-msgs \
    diagnostic-msgs \
    nav-msgs \
    rospy-tutorials \
    rostest \
    sensor-msgs \
    std-srvs \
    stereo-msgs \
    tf2-msgs \
    trajectory-msgs \
    visualization-msgs \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/RobotWebTools-release/rosbridge_suite-release/archive/release/melodic/rosbridge_library/0.11.3-1.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "75816268a59756a2ec59c2c65555db5f"
SRC_URI[sha256sum] = "d0c705263ea1b9484983a67d77d2a8f9d13f6974d54076d958731f683e468fa8"
S = "${WORKDIR}/rosbridge_suite-release-release-melodic-rosbridge_library-0.11.3-1"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('rosbridge-suite', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('rosbridge-suite', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rosbridge-suite/rosbridge-suite_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rosbridge-suite/rosbridge-suite-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rosbridge-suite/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/rosbridge-suite/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
