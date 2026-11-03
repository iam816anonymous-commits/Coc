# Detector and Dataset Decision

**Research date:** 2026-11-03  
**Status:** Runtime decision made; trained Clash of Clans detector does not exist yet.

## What must be detected

The first detector taxonomy should stay small and useful:
- Town Hall
- Major defenses (separate labels for visually distinguishable defense families)
- Walls / wall segments
- Resource and non-defensive buildings (initially one generic class)
- Obstacles / decorations (optional)
- Unknown / occluded (not a positive detection class; represented as uncertainty)

Do not attempt to recognize every troop, level, trap, skin, or building variant in the first model. A model cannot detect hidden traps from a screenshot. It must return "unknown" rather than inventing a trap.

## Runtime candidates

### OpenCV
Use for image decoding, resizing, geometric transforms, image annotation, and classical image processing. It is not a substitute for a trained building detector. OpenCV 4.5.0+ is Apache 2.0; older versions have different licensing.
Reference: https://opencv.org/license/

### TensorFlow Lite Task Library / LiteRT
Supports custom object-detection models that return class labels, bounding boxes, and confidence. It can run on Android and supports CPU and optional hardware delegates. This requires a model trained for our own building classes; common COCO models do not know Clash of Clans buildings.
References:
- https://www.tensorflow.org/lite/android/tutorials/object_detection
- https://developers.google.com/edge/litert

### Google ML Kit custom object detection
Can run a compatible custom TFLite model bundled locally or hosted remotely. It still requires us to provide a compatible model and does not supply a Clash of Clans building model.
Reference: https://developers.google.com/ml-kit/vision/object-detection/custom-models/android

### Ultralytics YOLO
Technically useful for training and inference, but licensing is a project-level decision. Ultralytics states that AGPL-3.0 use requires the complete corresponding project to comply with AGPL, while proprietary/internal/commercial use requires an Enterprise license. Do not silently add it to this project.
Reference: https://www.ultralytics.com/license

## Decision

For the Android app, target a custom TensorFlow Lite object detector using the TFLite Task Library or ML Kit custom-model API after we have a dataset and a validated model. Use OpenCV 4.5.0+ only for image preprocessing and coordinate geometry. Do not add Ultralytics unless the project owner explicitly accepts AGPL obligations or obtains the appropriate license.

## Dataset workflow

1. Collect screenshots only from sources the contributor is entitled to use. No scraping, game-client extraction, private API access, or asset ripping.
2. Record provenance, date, game version if known, device resolution, image crop, and permission/usage notes.
3. Manually label visible building bounding boxes using a documented taxonomy.
4. Mark ambiguous, overlapped, or cropped structures as difficult/unknown rather than guessing.
5. Export COCO JSON or another documented interchange format.
6. Split by distinct base identity into train/validation/test sets; never split near-duplicate screenshots of one base across train and test.
7. Train candidate detectors and report per-class precision, recall, mAP50-95, normalized center error, and calibration.
8. Test on unseen Town Hall levels, base styles, zooms, and device resolutions.
9. Convert and quantize to TFLite; compare model size, latency, memory, and accuracy on target Android devices.
10. Ship no model until held-out performance meets documented thresholds.

## Initial acceptance thresholds (proposed, not achieved)

- Major defense classes: precision >= 0.95 and recall >= 0.90 on held-out screenshots.
- Town Hall localization: center error <= 1.5% of normalized battlefield diagonal on >= 95% of test images.
- Any low-confidence or incomplete screenshot must trigger manual correction or abstention.
- No strategy plan may be generated from unreviewed detections in the first model release.

These are proposed gates. The project has no labeled dataset or measured detector accuracy yet.
