# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "Plugin-based layers for the navigation stack that   implement various social navigation contraints, like proxemic distance."
AUTHOR = "David V. Lu!! <davidvlu@gmail.com>"
ROS_AUTHOR = "David V. Lu!! <davidvlu@gmail.com>"
HOMEPAGE = "http://ros.org/wiki/social_navigation_layers"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=10;endline=10;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "navigation_layers"
ROS_BPN = "social_navigation_layers"

ROS_BUILD_DEPENDS = " \
    angles \
    costmap-2d \
    dynamic-reconfigure \
    geometry-msgs \
    people-msgs \
    pluginlib \
    roscpp \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    angles \
    costmap-2d \
    dynamic-reconfigure \
    geometry-msgs \
    people-msgs \
    pluginlib \
    roscpp \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    angles \
    costmap-2d \
    dynamic-reconfigure \
    geometry-msgs \
    people-msgs \
    pluginlib \
    roscpp \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    roslint \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/wu-robotics/navigation_layers_release/archive/release/melodic/social_navigation_layers/0.5.0-0.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "0eeef37b9734e4f3a6a5cf4c9d2c6238"
SRC_URI[sha256sum] = "106139809be8e491881b20b8510ca74eb2b73109bef94c30f1c58be452e2e4cd"
S = "${WORKDIR}/navigation_layers_release-release-melodic-social_navigation_layers-0.5.0-0"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('navigation-layers', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('navigation-layers', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/navigation-layers/navigation-layers_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/navigation-layers/navigation-layers-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/navigation-layers/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/navigation-layers/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
