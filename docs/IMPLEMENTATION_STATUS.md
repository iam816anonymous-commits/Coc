# Implementation Status

**Last updated:** 2026-11-03

## Implemented in repository

- Android Gradle project and Kotlin 2.3.0 / Jetpack Compose app module.
- Minimal app manifest, application entry point, and initial UI.
- System image picker for deliberate screenshot import.
- Bounded image decoding (longest dimension sampled to approximately 2048 px) to reduce memory pressure.
- Fit-center image preview.
- Manual annotation markers for funnel, tank, main army, hero, spell zone, and cleanup.
- Normalized image coordinates so annotations remain aligned as the preview changes size.
- Marker undo/clear and marker sequence display.
- Town Hall and army-context text inputs.
- Pure Kotlin coordinate transform and marker model.
- Unit tests for coordinate mapping, letterbox rejection, marker bounds, and invalid labels.
- GitHub Actions workflow for Android unit tests and debug APK assembly.

## Not implemented

- Automatic building or defense recognition.
- Trained Clash of Clans-specific object-detection model.
- Dataset or model weights.
- Base spatial graph / defense range estimation.
- Army inventory parser or troop-capacity validator.
- Attack strategy generation.
- Automatic troop composition recommendation.
- Spell/hero timing analysis.
- Plan persistence/export.
- Live-game capture, live overlay, or game interaction.

## Verification status

Source files and project configuration have been committed to GitHub. A successful Gradle build has **not** yet been confirmed from this environment. The CI workflow has been added, but no completed workflow result has been observed yet. Do not describe this prototype as build-verified or APK-ready until GitHub Actions reports success.

## Next engineering gates

1. Get CI green and fix any compile/test failures.
2. Add screenshot annotation export and persistent project state.
3. Define and collect a permitted, manually labeled dataset.
4. Train/evaluate a custom detector and document held-out metrics.
5. Implement a spatial scene model and manual detection correction UI.
6. Add inventory constraints before any strategy generation.
7. Build explainable, deterministic strategy candidates and evaluate with human review.
8. Revisit live overlay only if Supercell gives explicit written authorization.
