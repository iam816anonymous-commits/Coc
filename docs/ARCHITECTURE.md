# Architecture

## Current approved scope

A standalone screenshot-import tactical planner. The live-game overlay is blocked pending explicit written authorization from Supercell. No automation or game-client integration is in scope.

## High-level flow

```text
Android Screenshot Import
          |
          v
Image Validation + Normalization
          |
          v
Building Detection + Confidence
          |
          v
Spatial Scene Graph
          |
          +------ User Army Inventory
          |       (counts, levels, spells, heroes)
          v
Candidate Strategy Generator
          |
          v
Rule-Based Scoring + Constraints
          |
          v
Explainable Attack Plan
          |
          v
Annotated Image + Ordered Instructions
          |
          v
User Review / Correction / Saved History
```

## Modules

### Android client
- Screenshot import.
- User-entered army inventory.
- Analysis progress and result presentation.
- Manual correction UI.
- Local history and settings.

### Vision engine
- Image validation and coordinate normalization.
- Building detection.
- Confidence and unknown-state handling.
- Spatial graph generation.
- Annotation renderer.

### Strategy engine
- Inventory constraints.
- Candidate attack archetypes.
- Deployment sequence generation.
- Spell/hero timing hypotheses.
- Scoring, explanations, and uncertainty.

### Persistence
- Room database for imported-image metadata, user inventory, scene corrections, plans, and feedback.
- Store image files in app-private storage only with user consent.
- No account credentials or game-client data.

## Data contracts

All coordinates are normalized to [0,1] relative to the validated battlefield crop and include a source-image transform. Each detection includes class, box, confidence, visibility, and correction state. Each recommendation includes the inventory dependency, rationale, confidence, and conditional trigger.

## Non-goals

- Live overlay over Clash of Clans.
- Screen scraping or persistent capture of the game.
- Automated taps, swipes, troop deployment, or accessibility interaction.
- Memory inspection, packet interception, private APIs, game-client modification, or reverse engineering.
- Anti-cheat evasion.
