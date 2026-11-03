# Android Platform Research

**Research date:** 2026-11-03  
**Purpose:** Establish Android feasibility while keeping the current implementation outside the live game.

## Screen capture

Android MediaProjection provides user-consented screen-capture capabilities for legitimate screen-sharing and recording use cases. It requires a user-visible permission/consent flow and has lifecycle and foreground-service requirements that vary by Android version.

Official reference: https://developer.android.com/media/grow/media-projection

For the current safe prototype, prefer the Android photo picker / document picker so the user imports a screenshot intentionally. This avoids persistent screen capture and reduces permissions.

## Application overlays

Android supports application overlay windows through `WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY` (API 26+). The app must declare `SYSTEM_ALERT_WINDOW` and the user must explicitly grant special app access. Android may adjust overlay position, size, or visibility.

Official references:
- https://developer.android.com/reference/android/view/WindowManager.LayoutParams
- https://developer.android.com/reference/android/Manifest.permission#SYSTEM_ALERT_WINDOW

Platform feasibility is not policy permission. The fact that Android allows an app overlay does not mean Supercell permits an overlay over Clash of Clans. Therefore, no live-game overlay implementation is authorized by this technical finding.

## Accessibility services

Android AccessibilityService is designed to assist users with disabilities or temporary interaction limitations. It can inspect accessible UI and dispatch gestures when configured. It is not a general-purpose mechanism for controlling third-party games.

Official reference: https://developer.android.com/guide/topics/ui/accessibility/service

This project will not use AccessibilityService to inspect or operate Clash of Clans.

## Initial Android app direction

If an Android client is built, the first version should:
1. Import a screenshot using the system picker.
2. Ask the user to enter army inventory and levels.
3. Send the image and structured inputs to the analysis pipeline.
4. Show a separate annotated result image and ordered plan.
5. Save analysis history locally with Room.

No live capture, floating overlay, game interaction, or accessibility permission is needed for this initial version.

## Technical stack candidates

- Kotlin + Jetpack Compose for Android UI.
- Android Photo Picker / ACTION_OPEN_DOCUMENT for image import.
- Room for local plans and analysis history.
- OpenCV or an evaluated object-detection model for image geometry.
- A deterministic strategy engine for candidate plan generation.
- Optional vision-language model for explanation, not as the sole source of geometry.

## Risks and unknowns

- Android OS versions and OEM image-picker behavior must be tested.
- Screenshot aspect ratios, device scaling, zoom state, and cropped images affect coordinate normalization.
- The model must detect uncertainty and abstain when the screenshot is incomplete or ambiguous.
