# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "Playstation 3 SIXAXIS or DUAL SHOCK 3 joystick driver.     Driver for the Sony PlayStation 3 SIXAXIS or DUAL SHOCK 3     joysticks. In its current state, this driver is not compatible     with the use of other Bluetooth HID devices. The driver listens     for a connection on the HID ports, starts the joystick     streaming data, and passes the data to the Linux uinput device     so that it shows up as a normal joystick."
AUTHOR = "Jonathan Bohren <jbo@jhu.edu>"
ROS_AUTHOR = "Blaise Gassend"
HOMEPAGE = "http://www.ros.org/wiki/ps3joy"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=4;endline=4;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "joystick_drivers"
ROS_BPN = "ps3joy"

ROS_BUILD_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_bluez} \
    ${ROS_UNRESOLVED_PLATFORM_PKG_libusb-dev} \
    ${ROS_UNRESOLVED_PLATFORM_PKG_python-bluez} \
    diagnostic-msgs \
    joystick \
    rosgraph \
    rospy \
    sensor-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_bluez} \
    ${ROS_UNRESOLVED_PLATFORM_PKG_libusb-dev} \
    ${ROS_UNRESOLVED_PLATFORM_PKG_python-bluez} \
    diagnostic-msgs \
    joystick \
    rosgraph \
    rospy \
    sensor-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_bluez} \
    ${ROS_UNRESOLVED_PLATFORM_PKG_libusb-dev} \
    ${ROS_UNRESOLVED_PLATFORM_PKG_python-bluez} \
    diagnostic-msgs \
    joystick \
    rosgraph \
    rospy \
    sensor-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/ros-gbp/joystick_drivers-release/archive/release/melodic/ps3joy/1.13.0-1.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "56eba10f7b7083884aae7b02b3301ba5"
SRC_URI[sha256sum] = "766b29d7a4fcc3aa123fdc796f10c6862b9028466bfed7290d51976d547750b2"
S = "${WORKDIR}/joystick_drivers-release-release-melodic-ps3joy-1.13.0-1"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('joystick-drivers', d)}"
ROS_BUILD_TYPE = "catkin"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('joystick-drivers', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/joystick-drivers/joystick-drivers_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/joystick-drivers/joystick-drivers-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/joystick-drivers/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/joystick-drivers/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
