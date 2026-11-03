package com.cocstrategist

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ImageViewportTest {
    @Test
    fun fitCentersLandscapeImageInsidePortraitCanvas() {
        val viewport = ImageViewport.fit(400f, 800f, 1600, 900)
        assertEquals(0f, viewport.left, 0.001f)
        assertEquals(287.5f, viewport.top, 0.001f)
        assertEquals(400f, viewport.width, 0.001f)
        assertEquals(225f, viewport.height, 0.001f)
    }

    @Test
    fun mapsImageCenterToNormalizedCenter() {
        val viewport = ImageViewport.fit(400f, 800f, 1600, 900)
        val point = viewport.mapToNormalizedPoint(200f, 400f)!!
        assertEquals(0.5f, point.x, 0.001f)
        assertEquals(0.5f, point.y, 0.001f)
    }

    @Test
    fun rejectsTouchesInLetterboxArea() {
        val viewport = ImageViewport.fit(400f, 800f, 1600, 900)
        assertNull(viewport.mapToNormalizedPoint(200f, 100f))
    }

    @Test
    fun mapsBottomRightEdgeToOne() {
        val viewport = ImageViewport.fit(400f, 800f, 1600, 900)
        val point = viewport.mapToNormalizedPoint(400f, 512.5f)!!
        assertEquals(1f, point.x, 0.001f)
        assertEquals(1f, point.y, 0.001f)
    }
}
