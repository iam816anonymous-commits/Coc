# Product Requirements

## Product statement

A standalone Android companion that analyzes a user-imported Clash of Clans base screenshot and recommends a constrained, explainable attack plan with annotated troop deployment locations and order.

## Functional requirements

- FR-01: Import a screenshot explicitly selected by the user.
- FR-02: Validate crop, orientation, resolution, and image completeness.
- FR-03: Let the user enter Town Hall, troop inventory/counts/levels, spells, heroes, and relevant equipment.
- FR-04: Detect visible buildings and defenses with confidence values.
- FR-05: Let the user correct detections before strategy generation.
- FR-06: Generate candidate attack plans using only the supplied inventory.
- FR-07: Show troop deployment points/zones and a numbered sequence on a separate annotated image.
- FR-08: Explain the purpose and assumptions for every deployment step.
- FR-09: Show alternative plans and uncertainty.
- FR-10: Save and compare previous plans.

## Non-functional requirements

- NFR-01: Never fabricate certainty for occluded or undetected objects.
- NFR-02: Keep image and plan data local by default.
- NFR-03: Make network/AI processing explicit and opt-in.
- NFR-04: Provide reproducible analysis metadata including model/rule version.
- NFR-05: Test on multiple Android screen sizes and image aspect ratios.
- NFR-06: Keep vision detections and strategy decisions separately testable.

## Explicitly excluded

Live overlay over the game, live in-match guidance, automated input, accessibility-based game control, game-client modification, reverse engineering, and anti-cheat evasion. Reconsider only after written authorization from Supercell.
