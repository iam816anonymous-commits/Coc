# Research References

Research snapshot date: 2026-11-03. Recheck policies and Android behavior before release.

## Supercell — primary policy sources

- Terms of Service: https://supercell.com/en/terms-of-service/
  - Use limitations prohibit cheats, exploits, automation software, emulators, bots, hacks, mods, and unauthorized third-party software designed to modify or interfere with a game or game experience.
- Safe and Fair Play: https://supercell.com/en/safe-and-fair-play/
  - Describes third-party software as unapproved apps that manipulate gameplay to provide unfair advantages; includes mods, bots/gameplay automation, and other programs that modify gameplay or provide unearned progress.
- Clash of Clans Support, Don't Be a Cheat: https://sc-clashofclans.zendesk.com/hc/en-us/articles/54580505310491-Don-t-Be-a-Cheat

## Android — primary technical sources

- Photo Picker: https://developer.android.com/training/data-storage/shared/photo-picker
- Compose graphics and Canvas: https://developer.android.com/develop/ui/compose/graphics/draw/overview
- Layered images on Compose Canvas: https://developer.android.com/develop/ui/compose/quick-guides/content/display-layered-image
- MediaProjection overview: https://developer.android.com/media/grow/media-projection
- WindowManager.LayoutParams: https://developer.android.com/reference/android/view/WindowManager.LayoutParams
- SYSTEM_ALERT_WINDOW permission: https://developer.android.com/reference/android/Manifest.permission#SYSTEM_ALERT_WINDOW
- Accessibility services: https://developer.android.com/guide/topics/ui/accessibility/service
- Android Gradle Plugin 8/9 compatibility: https://developer.android.com/build/releases/about-agp

## Vision and detector deployment

- TensorFlow Lite Android object detection: https://www.tensorflow.org/lite/android/tutorials/object_detection
- LiteRT: https://developers.google.com/edge/litert
- ML Kit custom object detection: https://developers.google.com/ml-kit/vision/object-detection/custom-models/android
- OpenCV license: https://opencv.org/license/
- Ultralytics license: https://www.ultralytics.com/license

## Decisions and caveats

- Android platform capability does not grant permission under a game publisher's terms. The live overlay remains blocked pending explicit written authorization.
- A general COCO object detector does not recognize Clash of Clans-specific buildings. A custom labeled dataset and trained model are required.
- OpenCV 4.5.0+ is Apache 2.0; older OpenCV versions have different licensing.
- Ultralytics states its YOLO code and trained models are AGPL-3.0 by default; proprietary/internal/commercial deployment requires an Enterprise license under its published terms. This project has not adopted Ultralytics.
- The current Android prototype uses the Android system picker and Compose Canvas; it does not use MediaProjection, SYSTEM_ALERT_WINDOW, or AccessibilityService.
