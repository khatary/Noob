# X.VISION XSMT-110 — Public research and feasibility

Research date: 2026-11-20

## Product information found online

Multiple product listings for the exact XSMT-110 model agree on the following:
- Android 4.2
- 1 GB RAM
- 8 GB internal flash
- Four-core CPU (the exact CPU/SoC model is not disclosed)
- Mali-400MP2 GPU
- Wi-Fi and wired LAN
- Full HD / 1080p output
- Three USB ports, HDMI, SPDIF audio output, IR receiver
- MicroSD expansion, DLNA and Miracast are mentioned by retailers
- Bluetooth is listed as absent by some retailers

Sources:
- https://lavazemkhanegii.ir/اندروید-باکس-ایکس-ویژن-مدل-xsmt-110/
- https://tajrishkala.com/product/اندروید‌باکس-ایکس-ویژن-xsmt-110/
- https://www.safirkala.com/product/sfp-105745/اندروید-باکس-ایکس-ویژن-مدل-xsmt-110/
- https://virgool.io/@aminahan/whats-android-box-sqcvsmbps4sx

These are retail descriptions, not board schematics or silicon identification. Some specifications may be copied between stores and are not independent hardware verification.

## Exact chipset / firmware search result

Searches for the exact strings "XSMT-110", "X.VISION XSMT-110 firmware", "XSMT-110 motherboard", and combinations with Mali-400 did not reveal:
- a trustworthy stock firmware image for this exact model
- a readable photograph of its PCB or main SoC marking
- an exact SoC vendor/model/revision
- a kernel source tree or device tree for this exact board
- a verified recovery/flash procedure

A developer portfolio mentions an X.VISION XSMT-110 SmartBox project but does not publish firmware or hardware identification: https://drjackycv.web.app/

Important: Cortex-A9 and Mali-400MP2 are not unique to one SoC. They are insufficient to choose an Amlogic, Rockchip, Allwinner, or other vendor image. No SoC family is confirmed.

## Feasibility

An Android 8 port is technically conceivable only if a compatible SoC kernel/vendor base exists and the boot chain can load it. Feasibility for this exact board remains unproven. Main blockers:
- exact SoC and PCB revision unknown
- no confirmed matching Android 8 kernel/vendor base
- proprietary GPU, Wi-Fi, media, HDMI, audio and remote-control components may be unavailable
- bootloader and partition layout unknown
- no stock firmware backup or tested recovery route

The reported 1 GB RAM and 8 GB flash also make a full Android 8 build/configuration constrained, but do not alone prove impossibility.

## Current completion state

- Repository documentation: present
- Read-only diagnostic collector: present, not run on device
- Public product-spec research: completed
- Exact SoC / PCB identification: blocked
- Stock firmware extraction/backup: not done
- Bootloader / recovery / partition layout: not identified
- Kernel/vendor source matching: not done
- Device tree and kernel config: not created
- Android 8 source integration/build: not started
- Test boot image / flashable package: not built
- Installable ROM: does not exist yet

## Next evidence required

At least one of:
1. Device Info HW or AIDA64 CPU/System screenshots from the actual box.
2. Output from collect-info.sh (if the box can boot and run a terminal).
3. Clear photos of both sides of the PCB and readable markings on the main SoC and Wi-Fi chip.

Mask serial numbers and MAC addresses before sharing.

## Safety

Do not flash generic MXQ, Amlogic, Rockchip, Allwinner, or other TV-box images based only on the CPU/GPU family. Do not erase bootloader, recovery, NAND, eMMC, or factory partitions. A stock backup and a tested recovery method are needed before writing firmware.
