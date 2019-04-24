# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "Mechanical, kinematic and visual description   of the Pilz light weight arm PRBT."
AUTHOR = "Alexander Gutenkunst <a.gutenkunst@pilz.de>"
HOMEPAGE = "https://wiki.ros.org/prbt_support"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=12;endline=12;md5=3dce4ba60d7e51ec64f3c3dc18672dd3"

ROS_CN = "pilz_robots"
ROS_BPN = "prbt_support"

ROS_BUILD_DEPENDS = " \
    roscpp \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    canopen-motor-node \
    controller-manager \
    joint-state-controller \
    joint-state-publisher \
    pilz-control \
    prbt-hardware-support \
    robot-state-publisher \
    roscpp \
    rosservice \
    rviz \
    topic-tools \
    xacro \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    cmake-modules \
    libeigen \
    moveit-core \
    moveit-kinematics \
    prbt-hardware-support \
    roslaunch \
    rostest \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/PilzDE/pilz_robots-release/archive/release/melodic/prbt_support/0.5.4-1.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "764d4206bf03c1040bff4ec84741fc4e"
SRC_URI[sha256sum] = "703428b221c5cde71ae4214460b8535f11e777e199b98a57f23e7ca0d49f683d"
S = "${WORKDIR}/pilz_robots-release-release-melodic-prbt_support-0.5.4-1"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('pilz-robots', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('pilz-robots', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/pilz-robots/pilz-robots_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/pilz-robots/pilz-robots-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/pilz-robots/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/pilz-robots/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
