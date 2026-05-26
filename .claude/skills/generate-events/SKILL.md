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

## Pre-flight (do this BEFORE adding any new event definitions)

1. **Ask the user which ticket they are working on.** Use `AskUserQuestion` to capture the ticket ID (e.g., `AND-XXXXX`). Do not assume the current branch name reflects the right ticket — the user may be starting fresh work.
2. **Pull latest `main`.** If there are uncommitted changes, stash them first, pull, then pop:
   ```bash
   git stash push -m "WIP: <ticket> <short description>"
   git pull origin main
   git stash pop
   ```
   If already on a clean tree, just `git pull origin main` (or check out `main` first if on another branch).
3. **Create a new branch prefixed with the current git user's initials** (never use `task/` or commit directly to `main`). Derive the prefix from `git config user.name` — take the lowercase initials of the first and last name (e.g., `Hai Luong` → `lh`, `Jane Doe` → `jd`):
   ```bash
   USER_PREFIX=$(git config user.name | awk '{print tolower(substr($1,1,1) substr($NF,1,1))}')
   git checkout -b "${USER_PREFIX}/<TICKET>-<short-kebab-description>"
   ```
   Example for user `Hai Luong`: `lh/AND-22751-cloud-drive-document-provider-events`.

Only after these three steps should you add or modify event definitions.

## Steps

1. Ensure the new event definitions have been added. Events are annotated interfaces or classes in `shared/src/commonMain/kotlin/mega/privacy/mobile/analytics/event/` in the appropriate file (e.g., `ButtonPressEvents.kt`, `ScreenViewEvents.kt`, etc.). Simple events use an `interface`, events with runtime parameters use a `class` with constructor params. See `CLAUDE.md` for the full annotation reference.
2. Run `./gradlew build` from the project root to trigger KSP annotation processing.
3. Wait for the build to complete. This generates:
   - Event Kotlin classes from annotated interfaces/classes
   - Updated JSON event ID mappings in `shared/src/commonMain/resources/`
4. After the build completes, run `git status` to show which files were generated or updated.
5. Remind the user: **The updated JSON files in `shared/src/commonMain/resources/` must be committed** to preserve stable event IDs. If they are not committed, event IDs may change and corrupt analytics data.
