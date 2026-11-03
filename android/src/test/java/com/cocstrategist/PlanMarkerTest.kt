package com.cocstrategist

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class PlanMarkerTest {
    @Test
    fun factoryClampsCoordinatesToImageBounds() {
        val marker = PlanMarkerFactory.create(1L, MarkerRole.FUNNEL, 1.4f, -0.2f)
        assertEquals(1f, marker.x)
        assertEquals(0f, marker.y)
    }

    @Test
    fun markerRejectsBlankLabels() {
        assertThrows(IllegalArgumentException::class.java) {
            PlanMarker(1L, " ", 0.5f, 0.5f)
        }
    }

    @Test
    fun markerRejectsOutOfRangeCoordinates() {
        assertThrows(IllegalArgumentException::class.java) {
            PlanMarker(1L, "Funnel", 1.1f, 0.5f)
        }
    }
}
