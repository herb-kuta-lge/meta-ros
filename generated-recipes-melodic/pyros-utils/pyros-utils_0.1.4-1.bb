# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "Miscellaneous tools for pyROS"
AUTHOR = "AlexV <asmodehn@gmail.com>"
ROS_AUTHOR = "AlexV <asmodehn@gmail.com>"
HOMEPAGE = "https://github.com/asmodehn/pyros-utils"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=9;endline=9;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "pyros_utils"
ROS_BPN = "pyros_utils"

ROS_BUILD_DEPENDS = " \
    catkin-pip \
    rosgraph \
    roslaunch \
    roslint \
    rospy \
    rostest \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    rosgraph \
    roslaunch \
    rospy \
    rostest \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    rosgraph \
    roslaunch \
    rospy \
    rostest \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    pyros-test \
    rosnode \
    rosunit \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/pyros-dev/pyros-utils-release/archive/release/melodic/pyros_utils/0.1.4-1.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "c42bc20ecf73eeda88839eaface4ddb5"
SRC_URI[sha256sum] = "a8d91138eef528f426627138f6b29c409bf7638a66c0939d9a0e4ad0c8e94900"
S = "${WORKDIR}/pyros-utils-release-release-melodic-pyros_utils-0.1.4-1"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('pyros-utils', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('pyros-utils', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/pyros-utils/pyros-utils_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/pyros-utils/pyros-utils-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/pyros-utils/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/pyros-utils/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
