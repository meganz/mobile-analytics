---
name: generate-events
description: >
  Runs ./gradlew build to trigger KSP annotation processing, generating event classes
  and updating the JSON event ID mapping files in shared/src/commonMain/resources/.
triggers:
  - /generate-events
  - generate events
  - build events
  - generate ksp
---

# Generate Events

Run the Gradle build to trigger KSP code generation for analytics events.

## Steps

1. First, ensure the new event definitions have been added. Events are annotated interfaces or classes in `shared/src/commonMain/kotlin/mega/privacy/mobile/analytics/event/` in the appropriate file (e.g., `ButtonPressEvents.kt`, `ScreenViewEvents.kt`, etc.). Simple events use an `interface`, events with runtime parameters use a `class` with constructor params. See `CLAUDE.md` for the full annotation reference.
2. Run `./gradlew build` from the project root to trigger KSP annotation processing.
3. Wait for the build to complete. This generates:
   - Event Kotlin classes from annotated interfaces/classes
   - Updated JSON event ID mappings in `shared/src/commonMain/resources/`
4. After the build completes, run `git status` to show which files were generated or updated.
5. Remind the user: **The updated JSON files in `shared/src/commonMain/resources/` must be committed** to preserve stable event IDs. If they are not committed, event IDs may change and corrupt analytics data.