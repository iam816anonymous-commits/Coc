# Test Plan

## Repository and build
- Verify Gradle wrapper and reproducible Android builds once app module exists.
- Run unit tests, lint, and static analysis in CI.
- Keep research documents and source code in separate directories.

## Image pipeline
- Portrait and landscape screenshots.
- Multiple resolutions and aspect ratios.
- Cropped and incomplete bases.
- Rotation and scaling metadata.
- Compression artifacts and overlays in source images.
- Coordinate transform round-trip tests.

## Vision
- Per-class precision/recall/mAP.
- Bounding-box IoU and normalized center error.
- Confidence calibration.
- Unknown/occluded object handling.
- Held-out bases to prevent leakage.

## Strategy
- Reject plans exceeding army capacity or inventory.
- Reject use of unavailable troops/spells/heroes.
- Validate deployment points remain within the battlefield.
- Verify every step has a rationale and confidence.
- Regression fixtures for known annotated layouts.
- Human/expert review for tactical validity.

## Android
- Image picker cancellation and permission behavior.
- Process death and state restoration.
- Room migration tests.
- Low-memory behavior.
- Android API-level compatibility.

## Policy boundary
- No test harness may connect to or control the Clash of Clans client.
- No accessibility gesture dispatch, game overlay, memory inspection, packet capture, or anti-cheat probing.
