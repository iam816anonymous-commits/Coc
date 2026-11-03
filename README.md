# CoC — Clash of Clans Tactical Strategist

Android screenshot-planning prototype. It imports a screenshot selected by the user and lets the user place normalized, numbered annotations for funnel, tank, main army, hero, spell-zone, and cleanup notes.

## Current status

**An initial Android app foundation is committed. It is not an AI strategist yet.** The app currently supports screenshot import, image preview, manual placement annotations, and Town Hall / army context notes.

It does not automatically detect buildings, recommend troops, calculate attack strategy, capture the live game, display an in-game overlay, or control Clash of Clans.

See [implementation status](docs/IMPLEMENTATION_STATUS.md) and [roadmap](docs/ROADMAP.md).

## Policy boundary

Supercell's Terms of Service prohibit unauthorized third-party software designed to modify or interfere with its games or game experience. Its Safe and Fair Play policy describes unapproved third-party software that manipulates gameplay to provide unfair advantages and prohibits bots, mods, and other gameplay-modifying tools.

A live overlay giving tactical guidance during active gameplay may fall within those restrictions even when the player makes every tap. The public policy does not clearly exempt manual-only tactical overlays. A secondary account does not remove this risk. Do not assume the proposed live overlay is permitted; obtain explicit written authorization from Supercell before implementing or using it.

This repository therefore implements an independent screenshot annotation foundation only. Even a standalone strategy planner should receive policy review before being used alongside the game or distributed.

## Implemented features

- Kotlin + Jetpack Compose Android app.
- Explicit image import through Android's system picker.
- Bounded image decoding to reduce memory pressure.
- Fit-center screenshot preview.
- Manual numbered annotation markers for six tactical note categories.
- Normalized image coordinates and letterbox-aware tap mapping.
- Undo and clear marker controls.
- Town Hall and army context fields.
- Pure Kotlin coordinate and marker tests.
- GitHub Actions workflow for unit tests and debug APK assembly.

## Build

Open the repository root in Android Studio and sync Gradle. The project uses JDK 17, Android Gradle Plugin 8.13.0, Kotlin 2.3.0, Gradle 8.13, and Android SDK 36.

From a shell with Android SDK configured and Gradle 8.13 installed:

```bash
gradle :android:testDebugUnitTest
gradle :android:assembleDebug
```

The CI workflow is in `.github/workflows/android-ci.yml`. A successful CI result and a downloadable APK have not yet been verified.

## Repository layout

- `research/` — policy, Android platform, detector/model decision, vision strategy, and references.
- `docs/` — architecture, requirements, roadmap, implementation status, and test plan.
- `android/` — Kotlin Android app.
- `android/src/test/` — pure Kotlin unit tests.

## Next technical steps

1. Get CI green and verify a debug APK on a physical Android device.
2. Add persistent plan save/load and annotated image export.
3. Collect permitted, manually labeled screenshots with provenance.
4. Train and benchmark a custom building detector; no generic COCO detector recognizes Clash of Clans buildings.
5. Build a corrected spatial base representation.
6. Add structured army inventory and capacity validation.
7. Implement explainable tactical rules only after the detector and game-mechanics data are validated.

## Research links

- [Supercell policy](research/SUPERCELL_POLICY.md)
- [Android platform](research/ANDROID_PLATFORM.md)
- [Vision and strategy](research/VISION_AND_STRATEGY.md)
- [Detector and dataset decision](research/DETECTOR_MODEL_DECISION.md)
- [Architecture](docs/ARCHITECTURE.md)
- [Implementation status](docs/IMPLEMENTATION_STATUS.md)
- [Requirements](docs/REQUIREMENTS.md)
- [Roadmap](docs/ROADMAP.md)
- [Test plan](docs/TEST_PLAN.md)
- [References](research/REFERENCES.md)
