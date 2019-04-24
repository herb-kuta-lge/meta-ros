# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "EusLisp is an integrated programming system for the   research on intelligent robots based on Common Lisp and   Object-Oriented programming"
AUTHOR = "Kei Okada <k-okada@jsk.t.u-tokyo.ac.jp>"
ROS_AUTHOR = "Toshihiro Matsui"
HOMEPAGE = "http://euslisp.github.io/EusLisp/manual.html"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=9;endline=9;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "euslisp"
ROS_BPN = "euslisp"

ROS_BUILD_DEPENDS = " \
    cmake-modules \
    libjpeq-turbo \
    libpng-dev \
    libpq-dev \
    libx11 \
    libxext \
    mesa \
    mk \
    xfonts-100dpi \
    xfonts-75dpi \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    libjpeq-turbo \
    libpng-dev \
    libpq-dev \
    libx11 \
    libxext \
    mesa \
    xfonts-100dpi \
    xfonts-75dpi \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    libjpeq-turbo \
    libpng-dev \
    libpq-dev \
    libx11 \
    libxext \
    mesa \
    xfonts-100dpi \
    xfonts-75dpi \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/tork-a/euslisp-release/archive/release/melodic/euslisp/9.26.0-1.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "a395258cb3d8804861768870a2ec5ede"
SRC_URI[sha256sum] = "267e9150e430c87ed26dd58e103e1db0ef56dfe6580f16bf3352ebb23f5f374a"
S = "${WORKDIR}/euslisp-release-release-melodic-euslisp-9.26.0-1"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('euslisp', d)}"
ROS_BUILD_TYPE = "cmake"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('euslisp', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/euslisp/euslisp_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/euslisp/euslisp-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/euslisp/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/euslisp/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
