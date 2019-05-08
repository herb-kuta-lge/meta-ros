# Copyright (c) 2019 LG Electronics, Inc.

inherit ros_component

# Place metadata here that is unique to components of type "ros2" (as deteremined by ros_distro__get_component_type())

# https://index.ros.org/doc/ros2/Installation/Linux-Development-Setup/#id3 says LANG must be in the environment. Allow it to be
# overridden in local.conf . (Can't place in conf/layer.conf as it causes do_patch() to fail when building linux-raspberrypi.)
export LANG ??= "en_US.UTF-8"
