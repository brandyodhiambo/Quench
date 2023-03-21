package com.brandyodhiambo.home.presentation.component

import android.graphics.Paint
import android.graphics.Rect
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandyodhiambo.designsystem.theme.primaryColor
import java.lang.Math.PI
import java.lang.Math.sin

@Composable
fun WaterProgress(progress: Float, modifier: Modifier = Modifier) {
    val animatedProgress = animateFloatAsState(progress, tween(durationMillis = 1000))
    val strokeSize = 10.dp
    val size = 100.dp

    Canvas(modifier = modifier.size(size)) {
        val center = Offset(size.toPx() / 2, size.toPx() / 2)
        val radius = size.toPx() / 2 - strokeSize.toPx() / 2

        // Draw the circle outline
        drawCircle(
            color = Color.Gray,
            radius = radius,
            style = Stroke(width = strokeSize.toPx())
        )

        // Draw the water-like progress
        val progressAngle = animatedProgress.value * 360f
        val sweepAngle = (progressAngle / 2) * sin((progressAngle * PI / 180f)).toFloat()
        drawArc(
            color = Color.Blue,
            startAngle = -90f,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(width = strokeSize.toPx())
        )

        // Draw the progress text in the center of the circle
        val progressText = "${(animatedProgress.value * 100).toInt()}%"
        val textBounds = Rect()
        val textPaint = Paint().apply {
            textSize = 24.sp.toPx()
            color = primaryColor.toArgb()
            textAlign = Paint.Align.CENTER
            getTextBounds(progressText, 0, progressText.length, textBounds)
        }
        drawContext.canvas.nativeCanvas.drawText(
            progressText,
            center.x,
            center.y + textBounds.height() / 2,
            textPaint
        )
    }
}