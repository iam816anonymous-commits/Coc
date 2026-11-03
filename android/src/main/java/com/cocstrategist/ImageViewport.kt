package com.cocstrategist

import kotlin.math.min

data class NormalizedPoint(val x: Float, val y: Float)

/**
 * Describes the exact fit-center rectangle occupied by an image inside a view.
 * The conversion is deliberately independent of Android/Compose for unit testing.
 */
data class ImageViewport(
    val left: Float,
    val top: Float,
    val width: Float,
    val height: Float
) {
    fun mapToNormalizedPoint(viewX: Float, viewY: Float): NormalizedPoint? {
        if (width <= 0f || height <= 0f) return null
        if (viewX < left || viewX > left + width || viewY < top || viewY > top + height) return null
        return NormalizedPoint(
            x = ((viewX - left) / width).coerceIn(0f, 1f),
            y = ((viewY - top) / height).coerceIn(0f, 1f)
        )
    }

    companion object {
        fun fit(viewWidth: Float, viewHeight: Float, imageWidth: Int, imageHeight: Int): ImageViewport {
            require(viewWidth > 0f && viewHeight > 0f) { "View dimensions must be positive" }
            require(imageWidth > 0 && imageHeight > 0) { "Image dimensions must be positive" }
            val scale = min(viewWidth / imageWidth, viewHeight / imageHeight)
            val width = imageWidth * scale
            val height = imageHeight * scale
            return ImageViewport(
                left = (viewWidth - width) / 2f,
                top = (viewHeight - height) / 2f,
                width = width,
                height = height
            )
        }
    }
}
