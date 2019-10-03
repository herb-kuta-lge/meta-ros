# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "This package holds config and launch files for running the  <a href="http://ros.org/wiki/move_base">move_base</a> node on the <a href="http://ros.org/wiki/care-o-bot">Care-O-bot</a> in an odometric frame.     No static map is needed for the move_base node in this configuration."
AUTHOR = "Felix Zeltner <fez@ipa.fraunhofer.de>"
ROS_AUTHOR = "Matthias Gruhler <mig@ipa.fraunhofer.de>"
HOMEPAGE = "http://ros.org/wiki/cob_navigation_local"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=13;endline=13;md5=3dce4ba60d7e51ec64f3c3dc18672dd3"

ROS_CN = "cob_navigation"
ROS_BPN = "cob_navigation_local"

ROS_BUILD_DEPENDS = ""

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    cob-navigation-config \
    dwa-local-planner \
    move-base \
    rviz \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    cob-supported-robots \
    roslaunch \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/ipa320/cob_navigation-release/archive/release/melodic/cob_navigation_local/0.6.8-1.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "9bff61bc18c39a03358a440fe84f1e1b"
SRC_URI[sha256sum] = "cc23203a1c418f921e3ccd70f2dda1135847da9b94862d7cd9dffed9c187b029"
S = "${WORKDIR}/cob_navigation-release-release-melodic-cob_navigation_local-0.6.8-1"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('cob-navigation', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('cob-navigation', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/cob-navigation/cob-navigation_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/cob-navigation/cob-navigation-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/cob-navigation/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/cob-navigation/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
