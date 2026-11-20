# X.VISION XSMT-110 — Current status and next steps

## Status (2026-11-20)

| Item | Status |
|---|---|
| Project folder and initial hardware record | Done |
| Read-only diagnostic collector | Added; not yet run on the box |
| Exact SoC / board revision | Not identified |
| Stock firmware backup | Not done |
| Bootloader / recovery method | Not identified |
| Matching Android 8 kernel and vendor base | Not identified |
| Device tree / kernel configuration | Not created |
| Android 8 build | Not started |
| Test image / flashable ROM | Not built; unsafe to produce yet |

## Collect hardware information without a computer

1. On the TV box, install Device Info HW or AIDA64 if its Android version supports the app.
2. Photograph the CPU/SoC and System/Android pages.
3. If a terminal app is available, copy `collect-info.sh` to the box and run:
   ```sh
   sh /sdcard/collect-info.sh
   ```
   If the script is in a different folder, use its actual path.
4. The output should be saved as `/sdcard/XSMT-110-diagnostics.txt`.
5. Before sharing, hide serial numbers, Wi-Fi MAC addresses, Bluetooth MAC addresses, and any account or network credentials.

## Do not do these things yet

- Do not flash an image made for another MXQ / X96 / Amlogic / Rockchip / Allwinner box.
- Do not erase recovery, bootloader, NAND, eMMC, or factory partitions.
- Do not assume the reported Cortex-A9 / Mali-400 details uniquely identify the SoC.
- Do not try an Android 8 image until the exact board, kernel lineage, partition layout, and a tested recovery route are known.

## Work that can start after diagnostics

1. Match the exact SoC and PCB revision.
2. Search for the stock firmware and matching kernel source/vendor blobs for that exact board.
3. Record partition layout and boot image format from a safe stock backup.
4. Prepare a device tree and vendor integration based on verified evidence.
5. Build and test a non-destructive boot path, if the bootloader supports one.
6. Validate HDMI, Wi-Fi, Ethernet, USB, audio, remote control, storage, and suspend.
7. Only after recovery is proven, consider a flashable test release.

## Important limitation

This repository currently contains documentation and a read-only information collector, not Android source code, a device kernel, vendor blobs, or a ROM. A full Android 8 build also requires a suitable Linux build workstation and substantial storage/RAM; an Android phone alone is not a practical AOSP build machine. See the official AOSP setup requirements: https://source.android.com/docs/setup/start
