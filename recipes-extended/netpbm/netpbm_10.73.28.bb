# Copyright (c) 2019 LG Electronics, Inc.

DESCRIPTION = "Real-time collision detection and multi-physics simulation"
HOMEPAGE = "http://netpbm.sourceforge.net/"
SECTION = "devel"
LICENSE = "PD & GPL-2.0 & LGPL-2.1"
LIC_FILES_CHKSUM = "file://doc/COPYRIGHT.PATENT;md5=12058e0fe45009031ca66cdc09c838a5 \
    file://doc/GPL_LICENSE.txt;md5=079b27cd65c86dbc1b6997ffde902735 \
    file://doc/lgpl_v21.txt;md5=7fbc338309ac38fefcd64b04bb903e34 \
"

SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/super_stable/${PV}/${BP}.tgz"
SRC_URI[md5sum] = "0b59abd83b64353eaa0a68847aa85ecd"
SRC_URI[sha256sum] = "c0d32d4b9a53fde47dd9a36f45653e0ef51f6c390517f10b0c5056d1a8a844bd"

inherit pkgconfig

do_configure() {
    # make sure we use system libs
    sed -i '/SUPPORT_SUBDIRS/s:urt::' ${S}/GNUmakefile || bbfatal "sed failed"

    # take care of the importinc stuff ourselves by only doing it once
    # at the top level and having all subdirs use that one set #149843
    sed -i \
        -e '/^importinc:/s|^|importinc:\nmanual_|' \
        -e '/-Iimportinc/s|-Iimp|-I"$(BUILDDIR)"/imp|g'\
        ${S}/common.mk || bbfatal "sed failed"
    sed -i \
        -e '/%.c/s: importinc$::' \
        ${S}/common.mk ${S}/lib/Makefile ${S}/lib/util/Makefile || bbfatal "sed failed"
    sed -i \
        -e 's:pkg-config:$(PKG_CONFIG):' \
        ${S}/GNUmakefile ${S}/converter/other/Makefile ${S}/other/pamx/Makefile || bbfatal "sed failed"

    # The postscript knob is currently bound up with a fork test.
    if ${@bb.utils.contains('PACKAGECONFIG', 'postscript', 'false', 'true', d)}; then
        sed -i \
            -e 's:$(DONT_HAVE_PROCESS_MGMT):Y:' \
            ${S}/converter/other/Makefile ${S}/generator/Makefile || bbfatal "sed failed"
        sed -i -r \
            -e 's:(pbmtextps|pnmtops|pstopnm).*::' \
            ${S}/test/all-in-place.{ok,test} || bbfatal "sed failed"
        sed -i -e '/^$/d' ${S}/test/all-in-place.ok || bbfatal "sed failed"
        sed -i '2iexit 80' ${S}/test/ps-{alt-,}roundtrip.test || bbfatal "sed failed"
    fi

    # avoid ugly depend.mk warnings
    touch $(find ${S} -name Makefile | sed s:Makefile:depend.mk:g)

    cat ${S}/config.mk.in - >> ${S}/config.mk <<-EOF
    # Misc crap
    BUILD_FIASCO = N
    SYMLINK = ln -sf

    # These vars let src_test work by default
    PKGDIR_DEFAULT = ${B}/netpbm
    RESULTDIR_DEFAULT = ${B}/netpbm-test

    # Toolchain options
    CC = ${CC} -Wall
    LD = ${CC}
    CC_FOR_BUILD = ${BUILD_CC}
    LD_FOR_BUILD = ${BUILD_CC}
    AR = ${AR}
    RANLIB = ${RANLIB}
    PKG_CONFIG = pkg-config

    STRIPFLAG =
    CFLAGS_SHLIB = -fPIC

    LDRELOC = ${LD} -r
    LDSHLIB = \$(LDFLAGS) -shared -Wl,-soname,\$(SONAME)
    LINKER_CAN_DO_EXPLICIT_LIBRARY = N # we can, but dont want to
    LINKERISCOMPILER = Y
    NETPBMLIBSUFFIX = .so
    NETPBMLIBTYPE = unixshared
    STATICLIB_TOO = N

    # The var is called SSE, but the code is actually SSE2.
    WANT_SSE = N

    # Gentoo build options
    TIFFLIB = NONE
    # Let tiff worry about its own dependencies #395753
    TIFFLIB_NEEDS_JPEG = N
    TIFFLIB_NEEDS_Z = N
    JPEGLIB = NONE
    PNGLIB = NONE
    ZLIB = NONE
    LINUXSVGALIB = NONE
    XML2_LIBS = NONE
    JBIGLIB = NONE
    JBIGHDR_DIR =
    JASPERLIB = NONE
    JASPERHDR_DIR =
    URTLIB = NONE
    URTHDR_DIR =
    X11LIB = NONE
    X11HDR_DIR =
EOF
    # cannot chain the die with the heredoc above as bash-3
    # has a parser bug in that setup #282902
    [ $? -eq 0 ] || bbfailed "writing config.mk failed"
}

do_compile() {
    oe-runmake pm_config.h version.h manual_importinc #gentoo bug 149843
    oe-runmake
}
