# OpenEmbedded Build Instructions

Revision: 15 Published: UNPUBLISHED

These instructions are for building ROS 2 **crystal** and **dashing**, and the "core" portion of ROS 1 **melodic** using
OpenEmbedded **thud** (Yocto 2.6.x) on a 64-bit build machine running Ubuntu **bionic** (18.04). There is also support for
building images for the TurtleBot 3 Waffle Pi under ROS 1 **melodic**. The build machine should be a workstation-class PC with a
minimum of 8 GB memory and ~100GB of free space, ideally on a separate disk; however, the more processors and memory the build
machine has, the faster the builds will be.

An overview of OpenEmbedded/Yocto can be found in the
[Yocto Project Overview and Concepts Manual](https://www.yoctoproject.org/docs/2.6.2/overview-manual/overview-manual.html).

Additional information on `meta-ros` can be found in
[**Superflore** OE Recipe Generation Scheme](https://github.com/ros/meta-ros/wiki/Superflore-OE-Recipe-Generation-Scheme).

## Build Environment Setup

1. Install these prerequisites once on the build machine (a workstation-class host PC running 64-bit Ubuntu **bionic**):

    ``` sh
    sudo apt-get install gawk wget git-core diffstat unzip texinfo gcc-multilib \
            build-essential chrpath socat cpio python python3-pip python3-pexpect \
            xz-utils debianutils iputils-ping \
            g++-multilib locales lsb-release python3-distutils time
    sudo locale-gen en_US.utf8
    ```

2. Setup **bitbake** and OE-Core using a separate disk for the build artifacts (~100GB required if the working files are kept).
   **NOTE:** This can only be done in a new directory. To update an existing project, see **Usage Notes** below.

    ``` sh
    mkdir "<NEW-PROJECT-DIR>"
    cd "<NEW-PROJECT-DIR>"

    # The final branches for "build" and OpenEmbedded "thud" are not yet available. In the meantime, use rebaseable drafts of
    # them.
    git clone -b build-draft --single-branch git@github.com:ros/meta-ros build

    # Clone the OpenEmbedded metadata layers and generate conf/bblayers.conf . Select a configuration based on the OE-Core DISTRO
    # (ros1, ros2, or webos), the ROS distro (melodic, crystal or dashing), and the OpenEmbedded release series (thud) that you
    # wish to build:
    #
    # distro=ros1
    # distro=ros2
    # distro=webos
    #
    # ros_distro=melodic
    # ros_distro=crystal
    # ros_distro=dashing
    #
    # oe_release_series=thud

    mkdir conf
    cfg=$distro-$ros_distro-$oe_release_series.mcf
    cp build/files/$cfg conf/.
    build/scripts/mcf -f conf/$cfg

    # Set up the shell environment for this build and create a conf/local.conf . We expect all of the variables below to be unset.
    unset BDIR BITBAKEDIR BUILDDIR OECORELAYERCONF OECORELOCALCONF OECORENOTESCONF OEROOT TEMPLATECONF
    source openembedded-core/oe-init-build-env

    # The current directory is now the build directory; return to the original.
    cd -

    # An OpenEmbedded build produces a number of types of build artifacts, some of which can be shared between builds for
    # different OpenEmbedded DISTRO-s and ROS distros. Create a common artifacts directory on the separate disk under which all
    # the build artifacts will be placed. The edits to conf/local.conf done below will set TMPDIR to be a subdirectory of it.
    mkdir -p "<ABSOLUTE-PATH-TO-DIRECTORY-ON-SEPARATE-DISK>"
    ```

3. Add this to the bottom of `conf/local.conf`:

    ``` sh
    # ROS-ADDITIONS-BEGIN
    # ^^^^^^^^^^^^^^^^^^^ In the future, tools will expect to find this line.

    # Increment the minor version whenever you add or change a setting in this file.
    ROS_LOCAL_CONF_SETTINGS_VERSION = "2.0"

    # If not using mcf, replace ${MCF_DISTRO} with the DISTRO being used.
    DISTRO = "${MCF_DISTRO}"

    # If not using mcf, set ROS_DISTRO in conf/bblayers.conf .

    # The list of supported values of MACHINE is found in the Machines[] array in the .mcf file for the selected configuration.
    MACHINE = "<SUPPORTED-MACHINE>"

    # Can remove if DISTRO is "webos". If not using mcf, replace ${MCF_OPENEMBEDDED_VERSION} with the version of OpenEmbedded
    # being used. See the comments in files/ros*.mcf for its format.
    ROS_DISTRO_VERSION_APPEND = "+${MCF_OPENEMBEDDED_VERSION}"

    # Can remove if DISTRO is not "webos". If not using mcf, replace ${MCF_WEBOS_BUILD_NUMBER} with the build number of webOS OSE
    # being used.
    ROS_WEBOS_DISTRO_VERSION_APPEND = ".${MCF_WEBOS_BUILD_NUMBER}"

    # Because of a bug in OpenEmbedded, <ABSOLUTE-PATH-TO-DIRECTORY-ON-SEPARATE-DISK> can not be a symlink.
    ROS_COMMON_ARTIFACTS = "<ABSOLUTE-PATH-TO-DIRECTORY-ON-SEPARATE-DISK>"

    # Set the directories where downloads, shared-state, and the output from the build are placed to be on the separate disk.
    DL_DIR = "${ROS_COMMON_ARTIFACTS}/downloads"
    SSTATE_DIR = "${ROS_COMMON_ARTIFACTS}/sstate-cache"
    TMPDIR = "${ROS_COMMON_ARTIFACTS}/BUILD-${DISTRO}-${ROS_DISTRO}"
    # Don't add the libc variant suffix to TMPDIR.
    TCLIBCAPPEND := ""

    # As recommended by https://www.yoctoproject.org/docs/2.6.2/mega-manual/mega-manual.html#var-BB_NUMBER_THREADS
    # and https://www.yoctoproject.org/docs/2.6.2/mega-manual/mega-manual.html#var-PARALLEL_MAKE:
    BB_NUMBER_THREADS = "${@min(int(bb.utils.cpu_count()), 20)}"
    PARALLEL_MAKE = "-j ${BB_NUMBER_THREADS}"

    # Reduce the size of the build artifacts by removing the working files under TMPDIR/work. Comment this out to preserve them
    # (see https://www.yoctoproject.org/docs/2.6.2/mega-manual/mega-manual.html#ref-classes-rm-work).
    INHERIT += "rm_work"


    # Any other additions to the file go here.

    # EXTRA_IMAGE_FEATURES is just one of the many settings that can be placed in this file. You can find them all by searching
    # https://www.yoctoproject.org/docs/2.6.2/mega-manual/mega-manual.html#ref-variables-glossary for "local.conf".

    # Uncomment to allow "root" to ssh into the device. Not needed for images with webOS OSE because it implicitly adds this
    # feature.
    # EXTRA_IMAGE_FEATURES += "ssh-server-dropbear"

    # Uncomment to include the package management utilities in the image ("opkg", by default). Not needed for images with
    # webOS OSE because it implicitly adds this feature.
    # EXTRA_IMAGE_FEATURES += "package-management"

    # Uncomment to have all interactive shells implicitly source "setup.sh" (ROS 1) or "ros_setup.sh" (ROS 2).
    # EXTRA_IMAGE_FEATURES += "ros-implicit-workspace"

    # Uncomment to display additional useful variables in the build configuration output.
    # require conf/distro/include/ros-useful-buildcfg-vars.inc

    # vvvvvvvvvvvvvvvvv In the future, tools will expect to find this line.
    # ROS-ADDITIONS-END
    ```

## Building Images

Various **WARNING**-s will be issued by **bitbake** when building these images. They can be ignored. After a successful build of
an image, it can be found under `TMPDIR/deploy/images/MACHINE`.

### ROS 1 and ROS 2 Images

These images can be built when one of `ros?-*.mcf` was chosen for the configuration:

#### 1. `core-image-ros-roscore`

This image contains just the ROS "core".

``` sh
source openembedded-core/oe-init-build-env
cd -
bitbake core-image-ros-roscore
```

To include additional packages in this image, append them to the `IMAGE_INSTALL` variable in `conf/local.conf`:

``` bitbake
IMAGE_INSTALL_append_pn-core-image-ros-roscore = " <PKG1> <PKG2> ..."
```

Note that the leading space after the opening double-quote is mandatory. See
[Customizing Images Using `local.conf`](https://www.yoctoproject.org/docs/2.6.2/mega-manual/mega-manual.html#usingpoky-extend-customimage-localconf)
for more information.

#### 2. `core-image-ros-world`

By default, this image contains all of the "non-optional" ROS packages. In the future, you will be able to include "optional" ones
by setting `DISTRO_FEATURES` in `conf/local.conf`. Currently, this image does not build for ROS 1 **melodic**.

``` sh
source openembedded-core/oe-init-build-env
cd -
bitbake core-image-ros-world
```

#### 3. `core-image-ros-turtlebot3-core` (ROS 1 **melodic** on `raspberrypi3` only)

This image contains the packages required for the core TurtleBot 3 operation listed
[here](http://emanual.robotis.com/docs/en/platform/turtlebot3/raspberry_pi_3_setup/#3-install-dependent-packages-on-turtlebot-pc)
and [here](http://emanual.robotis.com/docs/en/platform/turtlebot3/appendix_raspi_cam/#installation). (Note that
[this](http://emanual.robotis.com/docs/en/platform/turtlebot3/raspberry_pi_3_setup/#4-usb-settings) is not needed, because the
images use `root` for their user account.)

``` sh
source openembedded-core/oe-init-build-env
cd -
bitbake core-image-ros-turtlebot3-core
```

To include additional packages in this image, append them to the `IMAGE_INSTALL` variable in `conf/local.conf`:

``` bitbake
IMAGE_INSTALL_append_pn-core-image-ros-turtlebot3-core = " <PKG1> <PKG2> ..."
```

#### 4. `core-image-ros-turtlebot3-all` (ROS 1 **melodic** on `raspberrypi3` only)

By default, this webOS OSE image contains all of the non-simulation related `turtlebot3-` prefixed packages and their
prerequisities mentioned in the [TurtleBot3 e-Manual](http://emanual.robotis.com/docs/en/platform/turtlebot3/overview/). (Note
that [this](http://emanual.robotis.com/docs/en/platform/turtlebot3/raspberry_pi_3_setup/#4-usb-settings) is not needed, because
the images use `root` for their user account.)

``` sh
source openembedded-core/oe-init-build-env
cd -
bitbake core-image-ros-turtlebot3-all
```

To include additional packages in this image, append them to the `IMAGE_INSTALL` variable in `conf/local.conf`:

``` bitbake
IMAGE_INSTALL_append_pn-core-image-ros-turtlebot3-all = " <PKG1> <PKG2> ..."
```

### webOS Images

These images can be built when one of `webos-*.mcf` was chosen for the configuration:

#### 1. `webos-image-ros-roscore`

This webOS OSE image contains just the ROS "core".

``` sh
source openembedded-core/oe-init-build-env
cd -
bitbake webos-image-ros-roscore
```

To include additional ROS packages in this image, append them to the `IMAGE_INSTALL` variable in `conf/local.conf`:

``` bitbake
IMAGE_INSTALL_append_pn-webos-image-ros-roscore = " <PKG1> <PKG2> ..."
```

Note that the leading space after the opening double-quote is mandatory. See
[Customizing Images Using `local.conf`](https://www.yoctoproject.org/docs/2.6.2/mega-manual/mega-manual.html#usingpoky-extend-customimage-localconf)
for more information.

#### 2. `webos-image-ros-world`

By default, this webOS OSE image contains all of the "non-optional" ROS packages. In the future, you will be able to include
"optional" ones by setting `DISTRO_FEATURES` in `conf/local.conf`. Currently, this image does not build for ROS 1 **melodic**.

``` sh
source openembedded-core/oe-init-build-env
cd -
bitbake webos-image-ros-roscore
```

#### 3. `webos-image-ros-turtlebot3-core` (ROS 1 **melodic** on `raspberrypi3` only)

By default, this webOS OSE image contains the packages required for the core TurtleBot 3 operation listed
[here](http://emanual.robotis.com/docs/en/platform/turtlebot3/raspberry_pi_3_setup/#3-install-dependent-packages-on-turtlebot-pc)
and [here](http://emanual.robotis.com/docs/en/platform/turtlebot3/appendix_raspi_cam/#installation).

Note that [this](http://emanual.robotis.com/docs/en/platform/turtlebot3/raspberry_pi_3_setup/#4-usb-settings) is not needed,
because the images use `root` for their user account.

``` sh
source openembedded-core/oe-init-build-env
cd -
bitbake webos-image-ros-turtlebot3-core
```

To include additional ROS packages in this image, append them to the `IMAGE_INSTALL` variable in `conf/local.conf`:

``` bitbake
IMAGE_INSTALL_append_pn-webos-image-ros-turtlebot3-core = " <PKG1> <PKG2> ..."
```

#### 4. `webos-image-ros-turtlebot3-all` (ROS 1 **melodic** on `raspberrypi3` only)

By default, this webOS OSE image contains all of the non-simulation related `turtlebot3-` prefixed packages and their
prerequisities mentioned in the [TurtleBot3 e-Manual](http://emanual.robotis.com/docs/en/platform/turtlebot3/overview/).

Note that [this](http://emanual.robotis.com/docs/en/platform/turtlebot3/raspberry_pi_3_setup/#4-usb-settings) is not needed,
because the images use `root` for their user account.

``` sh
source openembedded-core/oe-init-build-env
cd -
bitbake webos-image-ros-turtlebot3-all
```

To include additional ROS packages in this image, append them to the `IMAGE_INSTALL` variable in `conf/local.conf`:

``` bitbake
IMAGE_INSTALL_append_pn-webos-image-ros-turtlebot3-all = " <PKG1> <PKG2> ..."
```

## Image Sanity Testing

For now, only the barest minimum sanity testing is done on these images:

### ROS 1 Sanity Test

```text
# ping lge.com
# source /opt/ros/melodic/setup.sh
# roscore &
...
started core service [/rosout]
# rosnode list
/rosout
# rosnode info /rosout
--------------------------------------------------------------------------------
Node [/rosout]
Publications:
* /rosout_agg [rosgraph_msgs/Log]

Subscriptions:
* /rosout [unknown type]

Services:
* /rosout/get_loggers
* /rosout/set_logger_level

contacting node http://127.0.0.1:34643/ ...
Pid: 1363
```

### ROS 2 Sanity Test

Note that without a network connection, there's no output from `ros2 topic echo /chatter`.

``` text
# ping lge.com
# source ros_setup.sh
# ros2 topic list
/parameter_events
/rosout

# ros2 msg list
...

# (sleep 5; ros2 topic pub /chatter std_msgs/String "data: Hello world") &
# ros2 topic echo /chatter
publisher: beginning loop
publishing #1: std_msgs.msg.String(data='Hello world')

data: Hello world

publishing #2: std_msgs.msg.String(data='Hello world')

data: Hello world
```

### webOS OSE Sanity Test

The webOS OSE menu must appear after pressing the **Windows** key and allow the settings for **Network** to be launched.

### TurtleBot 3 Sanity Test

Follow the [TurtleBot 3 Bringup Instructions](http://emanual.robotis.com/docs/en/platform/turtlebot3/bringup/#run-roscore). If the
message:

    [WARN] [<timestamp>]: Failed to get param: timeout expired

appears, restart **turtlebot3_bringup** and try again.

## Usage Notes

- If a `meta-ros/scripts/mcf` file exists in your project, it is using an older `meta-ros` with layer version 2. Please start again
  from scratch in a new project directory following the instructions in the **Build Environment Setup** section. To allow you to
  transition, a branch `[legacy-v2-draft]` was created before the start of the layer version 3 implementation in `[thud-draft]`.
- The `*.mcf` files do not pin the commits of `meta-ros` or `meta-ros-webos`; to update them to their current heads, issue:

  ``` sh
  # Select the configuration you are building:
  #
  # distro=ros1
  # distro=ros2
  # distro=webos
  #
  # ros_distro=melodic
  # ros_distro=crystal
  # ros_distro=dashing
  #
  # oe_release_series=thud
  cfg=$distro-$ros_distro-$oe_release_series.mcf
  build/scripts/mcf -f conf/$cfg
  ```

  The commits of the other repos are pinned. You can see if there have been any changes to the pins (or anything else) by issuing:

  ``` sh
  cd build
  git pull --rebase
  cd -
  diff build/files/$cfg conf/$cfg
  # If there are changes and you have not modified conf/$cfg, copy them over:
  cp build/files/$cfg conf/.
  # Otherwise, merge them, eg, using: meld build/files/$cfg conf/$cfg
  ```

- The images are placed in `TMPDIR/deploy/images/MACHINE`. We've found [**balenaEtcher**](https://www.balena.io/etcher/) to be an
  excellent tool for flashing them to SD cards.
- These images default to being non-production: the `root` account has no password.
- When using the `core-image-ros-*` images on a `raspberrypi3`, the login prompt appears only on the HDMI display, not on the
  serial port. The opposite is true when using `webos-image-ros-*` images.
- When using the `webos-image-ros-*` images, press the **Windows** key to bring up the webOS OSE menu. Navigate to the network
  settings page to discover the IP address assigned by DHCP to the Ethernet connection, and its MAC address. Once you know the
  MAC address of a connection, you can use `ip neigh` from another Linux machine to discover it's IP address.
- If you have not added `ros-implicit-workspace` to `IMAGE_FEATURES`, you must set up a ROS workspace explicitly by issuing:

  ``` sh
  source /opt/ros/ROS_DISTRO/setup.sh       # ROS 1
  source ros_setup.sh                       # ROS 2
  ```

  Note that when running ROS 1:
  - You must set the environment variable `ROS_IP` or `ROS_HOSTNAME` before `source`-ing `setup.sh` if the machine will be
    communicating with remote ROS nodes.
  - You must set the environment variable `ROS_MASTER_URI` before `source`-ing `setup.sh` if **roscore** will be run on another
    ROS node.
- When using the `webos-image-ros-*` images, you can arrange for per-device configuration (such as setting up the ROS workspace
  and connecting to a WiFi network) by attaching an ext4-formatted USB flash drive containing an executable `rc.local` script
  under `USBROOT/webos-device-config/v1`. If present, it will be run from there by the **webos-device-config** service upon first
  boot. The service also copies it to `/var/palm/webos-device-config/rc.local`, where upon subsequent boots, it will be run by
  `/etc/rc.local`. An example `rc.local` script can be found under `meta-ros/files/examples/webos-device-config/v1`.
- WiFi does not work "out-of-the-box" on the `core-image-ros-*` images.
- WiFi does not work when running webOS OSE images on the B+ model of the Raspberry Pi 3. The symptom is that
  **Network Configuration** spins forever waiting for the list of SSIDs to appear.
- If a webOS OSE image is booted without a display connected, start up is delayed (**systemd** waits for 90s for a event that
  never arrives because **luna-surfacemanager** has crashed).

### TurtleBot 3 Waffle Pi with webOS OSE Usage
There is no need to compile packages on the TurtleBot 3. The `webos-image-ros-turtlebot3-all` image contains all of the packages
that exist in ROS 1 **melodic** needed to run the examples in the
[TurtleBot3 e-Manual](http://emanual.robotis.com/docs/en/platform/turtlebot3/overview/)
-- except those related to simulation and the visualization applications (eg **RViz**, **rqt**), which must continue to be run on
a remote PC. (Also note that `turtlebot3-follower` does not run due to a missing dependency.)

For example, all of the SLAM application can be run on the TurtleBot 3 by issuing the usual commands there, adding the option not
to launch **RViz**:

```sh
# roscore

# roslaunch turtlebot3_bringup turtlebot3_robot.launch

# roslaunch turtlebot3_slam turtlebot3_slam.launch slam_methods:=gmapping open_rviz:=false
```

The other two SLAM methods available in ROS 1 **melodic**, `cartographer` and `karto` can also be specified.

#### Tips
- If the TurtleBot 3 will not be communicating with remote ROS nodes, add `ros-implicit-workspace` to `IMAGE_FEATURES` so that you
  won't have to add `source /opt/ros/ROS_DISTRO/setup.sh` to `/home/root/.profile` every time you create a new image.
- If your TurtleBot 3 has a Raspberry Pi 3 model B instead of a B+, we recommend creating a device configuration USB flash drive
  so that when the TurtleBot 3 starts up, its ROS workspace is set up and it's connected to a WiFi network. You can use the
  example in `meta-ros/files/examples/webos-device-config/v1/rc.local` as a starting point. Remember to change its file mode to be
  executable.

## Change Log

### Revision 15
- Force push to `[thud-draft]` to publish part 1 of the implementation of layer version 3 of `meta-ros`. If you have been building
  from the `[thud-draft]` branch, please start again from scratch as soon as you can. To allow you to transition, a branch
  `[legacy-v2-draft]` has been created from the previous `[thud-draft]`. Its files will not be changed.
- Update the XXX instructions for the changes lv3 XXX. `DISTRO` no longer needs to be set in `conf/bblayers.conf`.
- Add a note that `ROS_COMMON_ARTIFACTS` can not be a symlink.
- As `[morty-draft]` was removed long ago, drop the references to it.

### Revision 14
- Clarify various portions of the build instructions so as to be clearer for those unfamiliar with OpenEmbedded.
- Force push to `[thud-draft]` to publish numerous changes, in particular:
  - The generation of `files/ROS_DISTRO/cache.yaml` has been split off from `ros-generate-recipes.sh` into its own script
    `ros-generate-cache.sh` and copies of the **rosdep** platform package mapping files are now retained.
  - The recipes for ROS 1 **melodic** were regenerated from its newer 2019-09-30 release using these scripts.
  - The recipes for ROS 2 **crystal** were regenerated from its newer 2019-09-19 release using these scripts.
  - The recipes for ROS 2 **dashing** were regenerated from its 2019-05-31 release using these scripts. Note that previously, the
    recipes had been generated from a `cache.yaml` that had several changes beyond that for the release.
  - `meta-ros/files/examples/webos-device-config/v1/rc.local` was tweaked to wait longer for WiFi enable to complete.
  - The Raspberry Pi video camera is now always enabled.
  - Additional useful variables can be displayed in the build configuration output by adding
   `require conf/distro/include/ros-useful-buildcfg-vars.inc` to `conf/local.conf`.
  - Drop **morty**-related commits; rearrange and squash many others.

### Revision 13
- Push (unforced) to `[thud-draft]` a greatly improved re-implementation of the per-device configuration scheme, including a
  revamped `meta-ros/files/examples/webos-device-config/v1/rc.local`.
- Explain when ROS 1 `ROS_*` environment variables must be set before `source`-ing `setup.sh`.
- Tweak the TurtleBot 3 SLAM example to make it explicit that all the commands are run on the TurtleBot 3.
- Clarify the TurtleBot 3 tips.

### Revision 12
- Push (unforced) to `[thud-draft]` to publish numerous changes, in particular:
  - Added support for creating images for the TurtleBot 3 Waffle Pi under ROS 1 **melodic**:
    - There are `core-image-ros-turtlebot3-core` and `webos-image-ros-turtlebot3-core` images that contain the packages required
      for the core TurtleBot 3 operation listed
      [here](http://emanual.robotis.com/docs/en/platform/turtlebot3/raspberry_pi_3_setup/#3-install-dependent-packages-on-turtlebot-pc)
      and [here](http://emanual.robotis.com/docs/en/platform/turtlebot3/appendix_raspi_cam/#installation).
    - There are `core-image-ros-turtlebot3-all` and `webos-image-ros-turtlebot3-all` images that contain all of the non-simulation
      related `turtlebot3-` prefixed packages in ROS 1 **melodic** and their prerequisities mentioned in the
      [TurtleBot3 e-Manual](http://emanual.robotis.com/docs/en/platform/turtlebot3/overview/)
  - Per-device configuration using a USB flash drive can be done when using webOS OSE images.
  - The recipes for ROS 1 **melodic** were regenerated from its 2019-09-10 release.
  - The Raspberry Pi video camera is now enabled.
- Convert the items under **Build Environment Setup** to numbered steps.
- Add instructions for building the TurtleBot 3 images, a TurtleBot 3 sanity test, and a section on its usage.

### Revision 11
- Force push to `[thud-draft]` to publish numerous changes, in particular:
    - `*-roscore` images can be built for ROS 1 **melodic**. Note that you must set the environment variable `ROS_IP` or
      `ROS_HOSTNAME` before `source`-ing `setup.sh` if the machine will be communicating with remote ROS nodes.
    - The recipes for ROS 2 **crystal** were regenerated based on its 2019-09-05 release.
    - webOS OSE images now continuously synchronize the machine's time with `time.google.com`. Previously, it was only set once at
      startup.
    - webOS OSE images now have all of the kernel modules installed. Previously, only a portion were.
    - webOS OSE images for `qemux86` now have `linux-yocto` v4.18.
    - `core-image-ros-*` images now contain **connman**, which means that Etherent networking is automatically started. (However,
      WiFi isn't; add an item mentioning this under **Usage Notes**.)
- Add instructions for building ROS 1 **melodic** images.
- Document various values for `EXTRA_IMAGE_FEATURES` that can be set in `conf/local.conf`.

### Revision 10
- Push (unforced) a commit that enables the Ethernet connection when running webOS OSE images on the B+ model of the Raspberry
  Pi 3; however, the Wi-Fi remains broken. Add an item to this effect under **Usage Notes**.
- Add `ping lge.com` to the sanity test to confirm that networking is working.

### Revision 9
- Clarify that the build environment setup instructions are for a new project without any settings in the environment of variables
  used by `openembedded-core/oe-init-build-env`.

### Revision 8
- Update for the migration to OpenEmbedded **thud**.
- Update for the support of ROS 2 **dashing**.
- Add lines that delimit the additions made to `conf/local.conf`. Introduce `ROS_LOCAL_CONF_SETTINGS_VERSION` to track the
  version of the settings.
- Reduce the size of the build artifacts by adding the setting to `conf/local.conf` that causes `rm_work` to be inherited
  globally.
- Drop the `PARALLEL_MAKE_pn-pcl` setting in `conf/local.conf` as the calculation of the value for `PARALLEL_MAKE` has been added
  to the recipe for `pcl`.
- Add an **Image Sanity Testing** section.

### Revision 7
- Drop setting `DISTRO` in `conf/local.conf`. It's now set in `conf/bblayser.conf` to the `Distribution` setting in `*.mcf`.
- Add instructions on how to include additions ROS packages to the `*-ros-core` images.
- Add more usage notes.

### Revision 6
- Problems have been observed when `TMPDIR` is shared between `ROS_DISTRO`-s => include `-${ROS-DISTRO}` in its value.
- Fix typo.

### Revision 5
- Update the plans for ROS 2 **dashing** support.
- Simpify and clarify the build setup instructions. Also make them more robust.
- Add a note on to how update `meta-ros`.
- Fix typos.

### Revision 4
- Revise for the inclusion of `mcf` in `meta-ros`.
- Add support for using webOS OSE as the OpenEmbedded `DISTRO`.

### Revision 3
- Add a step to install the prerequisites.
- Add instructions to build for Raspberry Pi.
- Fix typo.

### Revision 2
- Drop setting `SSTATE_MIRRORS` because `meta-ros` adds global environment variables that invalidates even the shared state for
  native packages.
- Specify a `PARALLEL_MAKE_pn-pcl` setting so that building `pcl` doesn't take nearly 3 hours.
- Fix typos.

### Revision 1
- Initial publication.
