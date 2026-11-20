# X.VISION XSMT-110 — Android 8 porting

## Goal
Prepare a device-specific Android 8 (Oreo) port for the X.VISION XSMT-110 TV box.

## Safety status
**Not flashable.** This workspace is a bring-up record, not a ROM image. Do not flash generic Amlogic/Rockchip/Allwinner images.

## Reported hardware (not yet verified)
- Android: 4.2 / 4.2.2
- CPU: Cortex-A9, reportedly quad-core
- GPU: Mali-400 MP2
- RAM: 1 GB DDR3
- Storage: 8 GB
- Wi-Fi: 2.4 GHz
- Ethernet: 10/100

## Current project files
- [Current status and next steps](NEXT-STEPS.md)
- [Read-only diagnostic collector](collect-info.sh)

## Required identification before selecting a kernel or device tree
- Exact SoC model and revision
- Board name / PCB revision
- Bootloader and boot mode
- Partition table and boot image layout
- Kernel version and available kernel source
- Wi-Fi / Ethernet / remote-control / HDMI vendor drivers and firmware

## Next evidence to collect
From the TV box itself, capture:
- CPU / SoC page from Device Info HW or AIDA64
- Android version and build number
- Board / device / hardware strings
- If ADB or a terminal is available, output of `getprop` and `cat /proc/cpuinfo`

## Porting stages
1. Identify exact board and SoC.
2. Locate a compatible Android 8 vendor base and kernel.
3. Extract and document stock boot/recovery/system images.
4. Build device tree, kernel configuration, and vendor integration.
5. Build a test boot image without overwriting stock recovery.
6. Validate HDMI, storage, Wi-Fi, Ethernet, USB, remote, audio, and suspend.
7. Package a recovery path and only then produce a flashable release.

No binary image should be published until the exact board identity and recovery method are confirmed.
