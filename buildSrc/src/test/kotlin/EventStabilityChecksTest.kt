package src.main.kotlin

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EventStabilityChecksTest {

    @Test
    fun `test that compareEventManifests reports only added when events are added`() {
        val diff = compare(
            baseline = listOf(event("A")),
            current = listOf(event("A"), event("B", id = 1)),
        )

        assertEquals(listOf(EventKey("ButtonPressEvent", "B")), diff.added)
        assertFalse(diff.hasBreakingChanges)
    }

    @Test
    fun `test that compareEventManifests reports removed when an event is deleted`() {
        val diff = compare(baseline = listOf(event("A"), event("B", id = 1)), current = listOf(event("A")))

        assertEquals(listOf(EventKey("ButtonPressEvent", "B")), diff.removed)
        assertTrue(diff.hasBreakingChanges)
    }

    @Test
    fun `test that compareEventManifests reports removed and added when an event is renamed`() {
        val diff = compare(baseline = listOf(event("Old")), current = listOf(event("New", id = 1)))

        assertEquals(listOf(EventKey("ButtonPressEvent", "Old")), diff.removed)
        assertEquals(listOf(EventKey("ButtonPressEvent", "New")), diff.added)
    }

    @Test
    fun `test that compareEventManifests reports removed and added when an event moves to another type`() {
        val diff = compare(
            baseline = listOf(event("A")),
            current = listOf(event("A", type = "MenuItemEvent")),
        )

        assertEquals(listOf(EventKey("ButtonPressEvent", "A")), diff.removed)
        assertEquals(listOf(EventKey("MenuItemEvent", "A")), diff.added)
    }

    @Test
    fun `test that compareEventManifests reports modified when an annotation argument changes`() {
        val diff = compare(
            baseline = listOf(event("A", arguments = """{"buttonName": "a", "screen": ""}""")),
            current = listOf(event("A", arguments = """{"buttonName": "a", "screen": "Home"}""")),
        )

        assertEquals(
            listOf(ModifiedEvent(EventKey("ButtonPressEvent", "A"), listOf("annotation argument 'screen' changed from '' to 'Home'"))),
            diff.modified,
        )
    }

    @Test
    fun `test that compareEventManifests reports modified when a parameter type changes`() {
        val diff = compare(
            baseline = listOf(event("A", parameters = """[{"name": "count", "type": "kotlin.Int", "enumValues": null}]""")),
            current = listOf(event("A", parameters = """[{"name": "count", "type": "kotlin.Long", "enumValues": null}]""")),
        )

        assertEquals(listOf("parameter 'count' type changed from kotlin.Int to kotlin.Long"), diff.modified.single().reasons)
    }

    @Test
    fun `test that compareEventManifests reports modified when parameters are added removed or reordered`() {
        val diff = compare(
            baseline = listOf(event("A", parameters = """[{"name": "a", "type": "T"}, {"name": "b", "type": "T"}, {"name": "c", "type": "T"}]""")),
            current = listOf(event("A", parameters = """[{"name": "b", "type": "T"}, {"name": "a", "type": "T"}, {"name": "d", "type": "T"}]""")),
        )

        assertEquals(
            listOf(
                "parameter 'c' was removed",
                "parameter 'd' was added with type T",
                "parameter order changed from [a, b] to [b, a]",
            ),
            diff.modified.single().reasons,
        )
    }

    @Test
    fun `test that compareEventManifests reports modified when enum values change`() {
        val diff = compare(
            baseline = listOf(event("A", parameters = """[{"name": "s", "type": "S", "enumValues": ["X"]}]""")),
            current = listOf(event("A", parameters = """[{"name": "s", "type": "S", "enumValues": ["X", "Y"]}]""")),
        )

        assertEquals(listOf("parameter 's' enum values changed from [X] to [X, Y]"), diff.modified.single().reasons)
    }

    @Test
    fun `test that compareEventManifests reports modified when a static value changes`() {
        val diff = compare(
            baseline = listOf(event("A", staticValues = """{"v": "1"}""")),
            current = listOf(event("A", staticValues = """{"v": "2", "w": "3"}""")),
        )

        assertEquals(
            listOf("static value 'v' changed from '1' to '2'", "static value 'w' was added with value '3'"),
            diff.modified.single().reasons,
        )
    }

    @Test
    fun `test that compareEventManifests reports modified when the id changes`() {
        val diff = compare(baseline = listOf(event("A", id = 1)), current = listOf(event("A", id = 2)))

        assertEquals(listOf("id changed from 1 to 2"), diff.modified.single().reasons)
    }

    @Test
    fun `test that compareEventManifests reports nothing when manifests are equal`() {
        val events = listOf(event("A"), event("B", id = 1))

        val diff = compare(baseline = events, current = events)

        assertEquals(EventApiDiff(emptyList(), emptyList(), emptyList()), diff)
    }

    @Test
    fun `test that isEventApiChangeAllowed returns true when a commit message contains the marker`() {
        assertTrue(isEventApiChangeAllowed("AND-1 Rename the event\n\n[allow-event-api-change]\n"))
    }

    @Test
    fun `test that isEventApiChangeAllowed returns false when no commit message contains the marker`() {
        assertFalse(isEventApiChangeAllowed("AND-1 Rename the event\n\n[allow-event-id-removal]\n"))
    }

    @Test
    fun `test that checkEventIds accepts a new declared event in range`() {
        val result = checkEventIds("ButtonPressEvent.json", mapOf("A" to 0), mapOf("A" to 0, "B" to 1), setOf("A", "B"))

        assertEquals(EventIdCheck(emptyList(), emptyList()), result)
    }

    @Test
    fun `test that checkEventIds fails when a generated id is outside the range`() {
        val result = checkEventIds("ButtonPressEvent.json", emptyMap(), mapOf("A" to 1000), setOf("A"))

        assertEquals(listOf("ButtonPressEvent.json: A has ID 1000, outside 0..999"), result.errors)
    }

    @Test
    fun `test that checkEventIds accepts an id outside the range when the file is the legacy file`() {
        val result = checkEventIds("LegacyEvent.json", emptyMap(), mapOf("A" to 99_999), setOf("A"))

        assertTrue(result.errors.isEmpty())
    }

    @Test
    fun `test that checkEventIds fails when a new id has no declaration`() {
        val result = checkEventIds("ButtonPressEvent.json", mapOf("A" to 0), mapOf("A" to 0, "B" to 1), setOf("A"))

        assertEquals(listOf("ButtonPressEvent.json: new event B has no declaration"), result.errors)
    }

    @Test
    fun `test that checkEventIds accepts an existing id when it has no declaration`() {
        val result = checkEventIds("ButtonPressEvent.json", mapOf("A" to 0), mapOf("A" to 0), emptySet())

        assertTrue(result.errors.isEmpty())
    }

    @Test
    fun `test that checkEventIds fails when an id changes or is reused`() {
        val result = checkEventIds(
            "ButtonPressEvent.json",
            mapOf("A" to 0, "B" to 1),
            mapOf("A" to 2, "C" to 1),
            setOf("A", "C"),
        )

        assertEquals(
            listOf(
                "ButtonPressEvent.json: A changed from 0 to 2",
                "ButtonPressEvent.json: new event C reuses ID 1 from B",
            ),
            result.errors,
        )
        assertEquals(listOf("ButtonPressEvent.json: B (1) was removed"), result.removals)
    }

    private fun compare(baseline: List<String>, current: List<String>) = compareEventManifests(
        parseEventManifest(manifest(baseline)),
        parseEventManifest(manifest(current)),
    )

    private fun manifest(events: List<String>) = """{"events": [${events.joinToString()}]}"""

    private fun event(
        name: String,
        type: String = "ButtonPressEvent",
        id: Int = 0,
        arguments: String = """{"buttonName": "b"}""",
        parameters: String = "[]",
        staticValues: String = "{}",
    ) = """
        {
            "name": "$name",
            "type": "$type",
            "id": $id,
            "legacyId": null,
            "annotationArguments": $arguments,
            "parameters": $parameters,
            "staticValues": $staticValues
        }
    """
}
