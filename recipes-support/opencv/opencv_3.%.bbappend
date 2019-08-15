# Copyright (c) 2019 LG Electronics, Inc.

# Fix up PACKAGECONFIG if Python 2 is being used.
PACKAGECONFIG_prepend = "${@'python2 ' if d.getVar('ROS_PYTHON_VERSION', True) == '2' else ''}"
# _remove happens after _prepend.
PACKAGECONFIG_remove = "${@'python3' if d.getVar('ROS_PYTHON_VERSION', True) == '2' else ''}"

# inherit distutils-base again if there is python2 in PACKAGECONFIG
# because distutils3-base was already inherited by main recipe before
# the PACKAGECONFIG was adjusted, luckily inheritting distutils-base
# after distutils3-base works OK, because it overwrittes all variables
# opencv needs to configure python2 support correctly
inherit ${@bb.utils.contains('PACKAGECONFIG', 'python2', 'distutils-base', '', d)}
