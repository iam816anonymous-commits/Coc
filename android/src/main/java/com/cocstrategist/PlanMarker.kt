package com.cocstrategist

/**
 * A human-authored annotation on an imported screenshot.
 *
 * Coordinates are normalized to the image bounds, not screen pixels. This keeps
 * annotations stable when the preview is resized. These markers are visual notes,
 * not coordinates sent to or used to control a game.
 */
data class PlanMarker(
    val id: Long,
    val label: String,
    val x: Float,
    val y: Float
) {
    init {
        require(x in 0f..1f) { "x must be normalized to [0, 1]" }
        require(y in 0f..1f) { "y must be normalized to [0, 1]" }
        require(label.isNotBlank()) { "label must not be blank" }
    }
}

enum class MarkerRole(val displayName: String) {
    FUNNEL("Funnel"),
    TANK("Tank"),
    MAIN_ARMY("Main army"),
    HERO("Hero"),
    SPELL("Spell zone"),
    CLEANUP("Cleanup")
}

object PlanMarkerFactory {
    fun create(id: Long, role: MarkerRole, x: Float, y: Float): PlanMarker =
        PlanMarker(id = id, label = role.displayName, x = x.coerceIn(0f, 1f), y = y.coerceIn(0f, 1f))
}
