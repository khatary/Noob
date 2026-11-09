# Noob — Private Media Hub

Android app for importing and browsing personal photos, videos, and other files.

## Starter features
- PIN-gated entry screen.
- Multi-file picker for photos, videos, and arbitrary file types.
- Copies imported originals into app-private internal storage.
- Persists the media index across app restarts.
- Separate Upload and Explore tabs.
- GitHub Actions workflow builds a debug APK.

## Build
Run `gradle assembleDebug` in Android Studio/Gradle environment. The APK is generated at `app/build/outputs/apk/debug/app-debug.apk`.

## Storage and playback notes
Imported originals remain in app-private storage until the app is uninstalled or its app data is cleared. Back up important files separately. Preview/playback depends on Android and installed codecs; unsupported formats are retained unchanged but may not play on the device.

The starter PIN is checked in the app for this prototype. Before public release, replace it with a user-configurable salted credential and stronger device authentication.
