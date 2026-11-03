# Roadmap

## Phase 0 — Research and policy gate
- [x] Verify repository and default branch.
- [x] Review Supercell Terms of Service and Safe and Fair Play policy.
- [x] Review Android overlay, MediaProjection, and AccessibilityService documentation.
- [x] Record product boundary and risk.
- [ ] Seek written Supercell clarification on standalone screenshot planner and any proposed live overlay.
- [ ] Finalize requirements and acceptance criteria.

## Phase 1 — Dataset and evaluation specification
- Define screenshot provenance and annotation rules.
- Define building taxonomy and scene schema.
- Create a small permitted, manually labeled dataset.
- Establish train/validation/test splits by base identity.
- Define detection and coordinate metrics.

## Phase 2 — Screenshot import prototype
- Kotlin/Compose app shell.
- System image picker.
- Image validation, crop, rotation, and normalization.
- User-entered army inventory.
- Local Room persistence.

## Phase 3 — Vision baseline
- OpenCV preprocessing baseline.
- Evaluate at least two object-detection model families.
- Building localization and confidence.
- Manual correction screen.
- Annotated image export.

## Phase 4 — Strategy engine
- Encode inventory and army-capacity constraints.
- Implement candidate attack archetypes.
- Implement deterministic scoring and explanations.
- Generate normalized deployment zones and sequence.
- Expert review and held-out evaluation.

## Phase 5 — Integrated planner
- End-to-end screenshot-to-plan flow.
- Local history and comparison.
- Model/rule versioning.
- Performance and privacy tests.

## Phase 6 — Policy review and release decision
- Recheck current Supercell policy.
- Obtain written clarification where required.
- Do not ship live in-game overlay unless explicitly authorized.
- Prepare release notes, privacy disclosure, and device compatibility matrix.

## Release gates

No phase may silently reintroduce live game overlay, live gameplay guidance, game automation, or game-client access. Any change to that scope requires a fresh policy review.
