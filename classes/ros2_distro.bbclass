# Copyright (c) 2019 LG Electronics, Inc.

inherit ros_distro

# Place metadata unique to ROS 2 distros here.

# ROS1_COMPONENTS is set in conf/ros-distro/ros-distro.conf .
def ros_distro__get_component_type(component, d):
    return bb.utils.contains('ROS1_COMPONENTS', component, 'ros1', 'ros2', d)
