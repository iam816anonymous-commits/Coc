# Android Platform Research

**Research date:** 2026-11-03  
**Purpose:** Document Android capabilities and the current app implementation without treating platform capability as publisher permission.

## Screenshot import

The app uses AndroidX Activity's `PickVisualMedia` contract with an image-only request. Android Photo Picker grants access only to media selected by the user and can fall back to document-picker behavior on devices where the native picker is unavailable.

Official reference: https://developer.android.com/training/data-storage/shared/photo-picker

The app decodes the selected image with `BitmapFactory` bounds inspection and a sampling factor to limit memory use. The current longest image dimension is reduced to approximately 2048 pixels before preview. The image is fit-centered in a Compose Canvas; taps in letterbox margins are rejected and accepted taps are mapped to normalized image coordinates.

## Annotation visualization

Jetpack Compose Canvas supports drawing a source image and graphics on top of it. The current prototype uses this to render numbered manual planning markers on a separate imported image.

Official references:
- https://developer.android.com/develop/ui/compose/graphics/draw/overview
- https://developer.android.com/develop/ui/compose/quick-guides/content/display-layered-image

## Application overlays

Android supports application overlay windows through `WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY` (API 26+). The app must declare `SYSTEM_ALERT_WINDOW` and the user must explicitly grant special app access.

Official references:
- https://developer.android.com/reference/android/view/WindowManager.LayoutParams
- https://developer.android.com/reference/android/Manifest.permission#SYSTEM_ALERT_WINDOW

Platform feasibility is not policy permission. This project does not declare or request overlay permission and does not display content over Clash of Clans. A live tactical overlay remains blocked pending explicit written authorization from Supercell.

## Screen capture

Android MediaProjection provides user-consented screen-capture capabilities for legitimate screen-sharing and recording use cases. It requires a user-visible consent flow and has lifecycle and foreground-service requirements that vary by Android version.

Official reference: https://developer.android.com/media/grow/media-projection

The current prototype does not use MediaProjection. It imports a user-selected static image instead.

## Accessibility services

Android AccessibilityService is designed to assist users with disabilities or temporary interaction limitations. It can inspect accessible UI and dispatch gestures when configured. It is not used by this project.

Official reference: https://developer.android.com/guide/topics/ui/accessibility/service

This project does not use AccessibilityService to inspect or operate Clash of Clans.

## Current Android implementation

- Kotlin + Jetpack Compose.
- minSdk 26, compileSdk 36, targetSdk 35.
- Android Photo Picker image-only contract.
- Bounded BitmapFactory decode.
- Fit-center Compose Canvas preview.
- Manual marker types: funnel, tank, main army, hero, spell zone, cleanup.
- Normalized coordinate transform with letterbox rejection.
- No broad media permission, overlay permission, MediaProjection, or AccessibilityService.

## Build configuration

- JDK 17.
- Android Gradle Plugin 8.13.0.
- Kotlin Gradle / Compose plugins 2.3.0.
- Gradle 8.13 in GitHub Actions.
- Compose BOM 2025.08.01.
- Android SDK platform 36 / build tools 36.0.0.

These versions are pinned in the repository. A successful CI build has not yet been observed.
