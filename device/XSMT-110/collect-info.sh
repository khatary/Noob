#!/system/bin/sh
# Read-only diagnostics for X.VISION XSMT-110.
# This script does NOT flash, modify, or erase anything.
OUT="/sdcard/XSMT-110-diagnostics.txt"
{
  echo "=== DATE ==="; date
  echo "=== GETPROP ==="; getprop
  echo "=== CPUINFO ==="; cat /proc/cpuinfo
  echo "=== MEMINFO ==="; cat /proc/meminfo
  echo "=== VERSION ==="; cat /proc/version
  echo "=== MOUNTS ==="; mount
  echo "=== BLOCK DEVICES ==="; ls -l /dev/block 2>&1
  echo "=== MTD ==="; cat /proc/mtd 2>&1
  echo "=== DISK FREE ==="; df 2>&1
  echo "=== USB ==="; cat /proc/bus/usb/devices 2>&1
  echo "=== DEVICE TREE MODEL ==="; cat /proc/device-tree/model 2>&1
  echo "=== END ==="
} > "$OUT" 2>&1
echo "Saved diagnostics to $OUT"
