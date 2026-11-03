# CoC — Clash of Clans Tactical Strategist

An Android companion project for analyzing user-provided Clash of Clans screenshots and producing explainable attack plans: troop composition, deployment locations, order, spell zones, and alternatives.

## Current status

**Research and specification only. No Android app or game integration has been implemented.**

## Important policy gate

Supercell's current Terms of Service prohibit unauthorized third-party software designed to modify or interfere with its games, including automation software, bots, hacks, and mods. Its Safe and Fair Play policy also prohibits third-party software that manipulates gameplay or provides unfair advantages.

A live in-game overlay that supplies tactical guidance may be considered an unauthorized third-party gameplay aid. A secondary account does not remove this risk. Therefore, this repository will not implement a live overlay over Clash of Clans, automated taps, gameplay automation, client modification, reverse engineering, or anti-detection. The live-overlay concept is **blocked pending explicit written authorization from Supercell**.

The safe research path is an independent companion planner that analyzes screenshots the user deliberately imports outside live gameplay, then displays a separate annotated plan. This is not a claim that Supercell has approved such a tool; policy review remains necessary before distribution or use alongside the game.

## Intended planning output

- Detected buildings with confidence and uncertainty.
- Base geometry and threat coverage.
- Candidate army composition constrained by the user's available troops.
- Deployment zones and ordered steps on a separate annotated image.
- Spell/hero timing hypotheses and alternative plans.
- Explicit confidence, assumptions, and reasons; no guaranteed star predictions.

## Repository layout

- `research/` — policy, Android platform, computer vision, and strategy research.
- `docs/` — requirements, architecture, roadmap, and test strategy.
- `android/` — reserved for a future approved Android companion app.
- `vision-engine/` — reserved for screenshot parsing and spatial reconstruction.
- `strategy-engine/` — reserved for deterministic tactical planning.

## Research documents

- [Supercell policy and product boundary](research/SUPERCELL_POLICY.md)
- [Android platform feasibility](research/ANDROID_PLATFORM.md)
- [Vision and tactical-planning research](research/VISION_AND_STRATEGY.md)
- [Architecture](docs/ARCHITECTURE.md)
- [Requirements](docs/REQUIREMENTS.md)
- [Roadmap](docs/ROADMAP.md)
- [Test plan](docs/TEST_PLAN.md)
- [References](research/REFERENCES.md)

## Development principles

1. Research and policy review before implementation.
2. No interaction with the game client or its private data.
3. User-supplied screenshots only for the initial product.
4. Explainable, testable strategy rules before adding an LLM.
5. Never present uncertain computer-vision detections as facts.
