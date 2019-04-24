# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "Driver for NovAtel receivers"
AUTHOR = "P. J. Reed <preed@swri.org>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=12;endline=12;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "novatel_gps_driver"
ROS_BPN = "novatel_gps_driver"

ROS_BUILD_DEPENDS = " \
    boost \
    diagnostic-msgs \
    diagnostic-updater \
    gps-common \
    libpcap \
    nav-msgs \
    nodelet \
    novatel-gps-msgs \
    roscpp \
    sensor-msgs \
    std-msgs \
    swri-math-util \
    swri-nodelet \
    swri-roscpp \
    swri-serial-util \
    swri-string-util \
    tf \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    boost \
    diagnostic-msgs \
    diagnostic-updater \
    gps-common \
    libpcap \
    nav-msgs \
    nodelet \
    novatel-gps-msgs \
    roscpp \
    sensor-msgs \
    std-msgs \
    swri-math-util \
    swri-nodelet \
    swri-roscpp \
    swri-serial-util \
    swri-string-util \
    tf \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    boost \
    diagnostic-msgs \
    diagnostic-updater \
    gps-common \
    libpcap \
    nav-msgs \
    nodelet \
    novatel-gps-msgs \
    roscpp \
    sensor-msgs \
    std-msgs \
    swri-math-util \
    swri-nodelet \
    swri-roscpp \
    swri-serial-util \
    swri-string-util \
    tf \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/swri-robotics-gbp/novatel_gps_driver-release/archive/release/melodic/novatel_gps_driver/3.8.0-1.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "e1d61f57fbdbd4c6d95d1ff2152c0bea"
SRC_URI[sha256sum] = "d1fbdbe8605a17c0fc9ee5a3c7c53d45c7422dabc4b4e1ca6c57dd518c8cded1"
S = "${WORKDIR}/novatel_gps_driver-release-release-melodic-novatel_gps_driver-3.8.0-1"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('novatel-gps-driver', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('novatel-gps-driver', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/novatel-gps-driver/novatel-gps-driver_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/novatel-gps-driver/novatel-gps-driver-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/novatel-gps-driver/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/novatel-gps-driver/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
