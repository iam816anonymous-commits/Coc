# Vision and Tactical-Planning Research

**Research date:** 2026-11-03  
**Status:** Research hypotheses; no model has been benchmarked yet.

## Core problem

Given a static, user-imported base screenshot plus user-entered army inventory, construct a spatial representation of the base and generate a plausible attack plan. This is not a solved problem by simply prompting a vision-language model. Accurate building localization and game-specific tactical rules are required.

## Proposed perception pipeline

1. Validate image dimensions, crop, orientation, and whether the full playable base is visible.
2. Normalize the visible battlefield into a coordinate plane, retaining the transform to source-image pixels.
3. Detect candidate buildings, defenses, walls, traps only where visually observable, obstacles, and Town Hall.
4. Classify each detection and estimate bounding box/center plus confidence.
5. Build a spatial graph: nearby structures, wall compartments, defensive coverage estimates, likely path corridors, and possible entry sectors.
6. Mark unknown/occluded objects as unknown; do not hallucinate hidden traps or levels.
7. Render an annotated copy for human correction before planning.

## Candidate model/tool families to evaluate

- OpenCV: image normalization, contour/shape processing, template matching experiments, geometric utilities.
- YOLO-family object detectors: candidate building localization, subject to dataset licensing and model evaluation.
- RT-DETR or similar detectors: comparison baseline for object detection.
- OCR: read visible labels/levels where present; likely limited value on the battlefield itself.
- Vision-language models: explain a structured scene and answer questions, but must not replace measured object coordinates.

No model is selected until a labeled dataset and benchmark exist. Do not scrape protected game assets or reverse engineer the game client. Use permitted user-supplied screenshots and carefully reviewed annotations.

## Dataset plan

Create a small manually labeled evaluation set from user-provided screenshots, only after policy review. Store:
- image provenance and permission notes,
- image dimensions and crop metadata,
- building class,
- bounding box,
- visible/occluded flag,
- annotator confidence,
- Town Hall / base context when known.

Split by base/screenshot identity, not by cropped patches, to avoid train/test leakage. Keep a held-out test set.

## Metrics

- Per-class precision, recall, and mAP for building detection.
- Bounding-box IoU and center-point error normalized by battlefield dimensions.
- Base-plane transform error.
- Calibration / confidence reliability.
- Human correction time.
- Strategy-plan feasibility judged by expert review, not only language-model preference.

## Strategy planner

Use a hybrid architecture:

1. **Hard constraints:** army capacity, available troop counts, spell slots, hero availability, and user-entered levels.
2. **Scene features:** defensive threat estimates, wall compartments, exterior buildings, and candidate approach sectors.
3. **Candidate generation:** create a small set of attack archetypes supported by the actual army inventory.
4. **Rule-based scoring:** evaluate pathing plausibility, funnel feasibility, exposure to high-threat defenses, objective access, and cleanup coverage.
5. **Explanation:** state why a candidate is recommended and which assumptions could invalidate it.
6. **Human review:** allow correction of detections and reject low-confidence plans.

## Placement output

The planner should produce normalized points and zones on a separate annotated screenshot:
- troop group,
- normalized deployment point or segment,
- sequence index,
- intended role (funnel, tank, damage, support, cleanup),
- rationale,
- confidence,
- dependencies and conditional branches.

Do not imply pixel coordinates are universal. They must be transformed for the exact imported image and must never be used to automate game input.

## Major failure modes

- Incorrect building class or location.
- Cropped/zoomed base and incorrect coordinate transform.
- Hidden traps incorrectly inferred as known.
- Army inventory not matching the player's actual army.
- Recommendations relying on unavailable troop levels, spells, heroes, or equipment.
- Strategy explanations that sound plausible but are not mechanically valid.
- Patch/version changes invalidating assumptions.

## Research gates

Before implementation:
1. Establish policy boundary and obtain written guidance if necessary.
2. Define a manually labeled dataset.
3. Benchmark detection models.
4. Define a structured scene schema.
5. Define strategy rules and a review rubric.
6. Test against held-out examples and require abstention on uncertain cases.
