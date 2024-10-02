package com.muhammadzubair.koranger

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun ComposerRangeBar(
    modifier: Modifier = Modifier,
    minValue: Int = 0,
    maxValue: Int = 100,
    activeColor: Color = Color.Blue,
    inactiveColor: Color = Color.Gray,
    thumbRadius: Float = 20f,
    barThickness: Float = 8f,
    orientation: BarOrientation = BarOrientation.Horizontal,
    onValueChange: (Int) -> Unit
) {
    var progress by remember { mutableStateOf(0f) }

    Canvas(
        modifier = modifier
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    // Calculate progress based on drag location
                    progress = when (orientation) {
                        BarOrientation.Horizontal -> (change.position.x / size.width).coerceIn(0f, 1f)
                        BarOrientation.Vertical -> (1f - change.position.y / size.height).coerceIn(0f, 1f)
                    }
                    // Notify listener with the current value
                    onValueChange((minValue + progress * (maxValue - minValue)).toInt())
                }
            }
    ) {
        val paint = Paint().apply { this.strokeWidth = barThickness }
        val thumbPosition = when (orientation) {
            BarOrientation.Horizontal -> Offset(progress * size.width, size.height / 2)
            BarOrientation.Vertical -> Offset(size.width / 2, (1f - progress) * size.height)
        }

        // Drawing inactive part of the bar
        val barStart = if (orientation == BarOrientation.Horizontal) {
            Offset(0f, size.height / 2)
        } else {
            Offset(size.width / 2, size.height)
        }
        val barEnd = if (orientation == BarOrientation.Horizontal) {
            Offset(size.width, size.height / 2)
        } else {
            Offset(size.width / 2, 0f)
        }

        drawLine(
            color = inactiveColor,
            start = barStart,
            end = barEnd,
            strokeWidth = barThickness
        )

        // Drawing active part of the bar
        val activeBarEnd = thumbPosition
        drawLine(
            color = activeColor,
            start = barStart,
            end = activeBarEnd,
            strokeWidth = barThickness
        )

        // Drawing thumb
        drawCircle(
            color = activeColor,
            radius = thumbRadius,
            center = thumbPosition
        )
    }
}
