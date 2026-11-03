# Roadmap

## Phase 0 — Research and policy gate
- [x] Verify repository and default branch.
- [x] Review Supercell Terms of Service and Safe and Fair Play policy.
- [x] Review Android image import, overlay, MediaProjection, and AccessibilityService documentation.
- [x] Record product boundary and risk.
- [ ] Seek written Supercell clarification on screenshot planner and any proposed live overlay.

## Phase 1 — Android screenshot-planner foundation
- [x] Create Kotlin / Jetpack Compose Android project.
- [x] Add explicit screenshot import using the system picker.
- [x] Add bounded image decoding and fit-center preview.
- [x] Add manual troop-role annotation markers.
- [x] Add normalized image coordinate mapping.
- [x] Add coordinate and marker unit tests.
- [x] Add Android CI workflow.
- [ ] Confirm CI green and produce a verified debug APK.
- [ ] Add persistent plan save/load and annotated-image export.

## Phase 2 — Dataset and detector
- [x] Define initial building taxonomy and dataset workflow.
- [x] Compare OpenCV, TFLite/LiteRT, ML Kit custom detector, and YOLO licensing.
- [ ] Collect permitted screenshots with provenance.
- [ ] Manually label buildings and defenses.
- [ ] Create train/validation/test split by base identity.
- [ ] Train and benchmark candidate detectors.
- [ ] Integrate a validated custom TFLite model.
- [ ] Add manual correction UI and confidence-based abstention.

## Phase 3 — Spatial base model
- [ ] Reconstruct battlefield coordinate plane.
- [ ] Represent buildings, walls, defense zones, and unknown regions.
- [ ] Estimate threat coverage with explicit assumptions.
- [ ] Test geometry against hand-annotated reference images.

## Phase 4 — Army and strategy engine
- [ ] Build structured troop/spell/hero inventory.
- [ ] Validate army capacity and availability constraints.
- [ ] Implement candidate strategy rules from verified game mechanics.
- [ ] Generate explainable deployment zones and ordered plan.
- [ ] Evaluate against human-reviewed examples; do not claim guaranteed stars.

## Phase 5 — Integrated companion planner
- [ ] Screenshot-to-plan flow.
- [ ] Save/load plans and export annotations.
- [ ] Model/rule versioning and local history.
- [ ] Device performance, privacy, and regression tests.

## Phase 6 — Policy review and release decision
- [ ] Recheck current Supercell policy.
- [ ] Obtain written clarification where required.
- [ ] Do not ship a live in-game overlay or in-match recommendation feature unless explicitly authorized.
- [ ] Prepare privacy disclosure and device compatibility matrix.

## Release gates

- No model may be represented as accurate without held-out metrics.
- No tactical plan may be represented as reliable without inventory validation and human review.
- No phase may silently introduce game-client access, live gameplay guidance, automation, or anti-cheat evasion.
