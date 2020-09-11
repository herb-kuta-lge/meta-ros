# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_foxy
inherit ros_superflore_generated

DESCRIPTION = "Pure C++ driver library for Kobuki."
AUTHOR = "Daniel Stonier <d.stonier@gmail.com>"
ROS_AUTHOR = "Daniel Stonier"
HOMEPAGE = "https://kobuki.readthedocs.io/en/release-1.0.x/"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=12;endline=12;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "kobuki_core"
ROS_BPN = "kobuki_core"

ROS_BUILD_DEPENDS = " \
    ecl-build \
    ecl-command-line \
    ecl-config \
    ecl-converters \
    ecl-devices \
    ecl-geometry \
    ecl-mobile-robot \
    ecl-sigslots \
    ecl-threads \
    ecl-time \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-ros-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    ecl-command-line \
    ecl-config \
    ecl-converters \
    ecl-devices \
    ecl-geometry \
    ecl-mobile-robot \
    ecl-sigslots \
    ecl-threads \
    ecl-time \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/stonier/kobuki_core-release/archive/release/foxy/kobuki_core/1.2.0-1.tar.gz
ROS_BRANCH ?= "branch=release/foxy/kobuki_core"
SRC_URI = "git://github.com/stonier/kobuki_core-release;${ROS_BRANCH};protocol=https"
SRCREV = "a77460d459c6f021e5a487f30fd9eafde126d18c"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "ament_cmake"

inherit ros_${ROS_BUILD_TYPE}
