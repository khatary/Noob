# X.VISION XSMT-110 — Public research and feasibility

Research date: 2026-11-20

## Publicly corroborated product information

Public product listings for X.VISION XSMT-110 describe:
- Android 4.2
- 1 GB RAM
- 8 GB flash storage
- Mali-400MP2 graphics
- Four-core CPU claim
- Wi-Fi and wired LAN
- Three USB ports, HDMI, IR sensor, Full HD output

Sources:
- https://lavazemkhanegii.ir/اندروید-باکس-ایکس-ویژن-مدل-xsmt-110/
- https://tajrishkala.com/product/اندروید‌باکس-ایکس-ویژن-xsmt-110/

These are retail/product descriptions, not board schematics or chipset identification. They do NOT establish:
- exact SoC vendor/model/revision
- PCB model/revision
- bootloader family/version
- kernel version/configuration
- partition layout
- Wi-Fi chipset or firmware
- availability/licensing of vendor source and blobs

## Research finding

Searches for the exact model name did not locate a trustworthy stock firmware package, board photo with readable chip markings, device tree, kernel source, or confirmed SoC identifier. A developer portfolio mentions an X.VISION XSMT-110 SmartBox project, but does not publish technical firmware details: https://drjackycv.web.app/

Do not infer Amlogic, Rockchip, or Allwinner from the Mali GPU or Android version alone.

## Feasibility

An Android 8 port is technically conceivable only if a compatible SoC kernel/vendor base exists and the boot chain can load it. It is not currently demonstrated feasible for this exact board. Android 8 support may be blocked by:
- unavailable or incompatible vendor kernel
- missing proprietary GPU, Wi-Fi, media, HDMI or audio components
- incompatible bootloader / partition layout
- insufficient RAM/storage for a usable Android 8 configuration
- no safe recovery method

## Current completion state

- Repository documentation: present
- Read-only diagnostic script: present, not yet run on device
- Public product-spec cross-check: completed, chipset still unknown
- Exact SoC/PCB identification: blocked on device diagnostics or clear board/chip photos
- Stock firmware extraction/backup: not done
- Kernel/vendor source matching: not done
- Device tree and kernel config: not done
- Android 8 source integration/build: not started
- Boot image / flashable package: not built

## Next required evidence

At least one of:
1. Device Info HW or AIDA64 CPU/System screenshots from the actual box.
2. Output from the read-only collector in collect-info.sh.
3. Clear photos of both sides of the PCB and readable markings on the main SoC and Wi-Fi chip.

Mask serial numbers and MAC addresses before sharing.

## Safety

No generic TV-box image should be flashed. No bootloader, recovery, NAND, eMMC, or factory partition should be erased. A backup and tested recovery route are mandatory before any write operation.
