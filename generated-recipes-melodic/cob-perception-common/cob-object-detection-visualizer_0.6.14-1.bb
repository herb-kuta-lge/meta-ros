# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "The cob_object_detection_visualizer package"
AUTHOR = "Richard Bormann <richard.bormann@ipa.fraunhofer.de>"
ROS_AUTHOR = "Richard Bormann <richard.bormann@ipa.fraunhofer.de>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=8;endline=8;md5=3dce4ba60d7e51ec64f3c3dc18672dd3"

ROS_CN = "cob_perception_common"
ROS_BPN = "cob_object_detection_visualizer"

ROS_BUILD_DEPENDS = " \
    boost \
    cob-object-detection-msgs \
    cv-bridge \
    eigen-conversions \
    image-transport \
    message-filters \
    opencv \
    pcl \
    pcl-ros \
    roscpp \
    sensor-msgs \
    visualization-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    boost \
    cob-object-detection-msgs \
    cv-bridge \
    eigen-conversions \
    image-transport \
    message-filters \
    opencv \
    pcl \
    pcl-ros \
    roscpp \
    sensor-msgs \
    visualization-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    boost \
    cob-object-detection-msgs \
    cv-bridge \
    eigen-conversions \
    image-transport \
    message-filters \
    opencv \
    pcl \
    pcl-ros \
    roscpp \
    sensor-msgs \
    visualization-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/ipa320/cob_perception_common-release/archive/release/melodic/cob_object_detection_visualizer/0.6.14-1.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "24c8cd83aef438380850b1356f015fc2"
SRC_URI[sha256sum] = "6f69b00121d7cb02ff0da6cbe8867092c3a752238dd84fa3664cb75ff6bf6df7"
S = "${WORKDIR}/cob_perception_common-release-release-melodic-cob_object_detection_visualizer-0.6.14-1"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('cob-perception-common', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('cob-perception-common', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/cob-perception-common/cob-perception-common_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/cob-perception-common/cob-perception-common-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/cob-perception-common/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/cob-perception-common/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
