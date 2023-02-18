package com.brandyodhiambo.quench.views.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandyodhiambo.designsystem.theme.primaryColor

@Composable
fun CircularRating(
    modifier: Modifier = Modifier,
    percentage: Float,
    drunk: Int,
    number: Int = 10,
    fontSize: TextUnit = 16.sp,
    radius: Dp = 110.dp,
    color: Color = primaryColor,
    strokeWidth: Dp = 8.dp,
    animationDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val currentPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animDelay
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(
            modifier = Modifier
                .size(radius * 2f)
        ) {
            drawArc(
                color = color,
                startAngle = 90f,
                sweepAngle = (360 * (currentPercentage.value * 0.1)).toFloat(),
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
            )
        }
     Column(
         horizontalAlignment = Alignment.CenterHorizontally
     ) {
         Text(
             text = "$drunk /2600 ml",
             color = primaryColor,
             fontSize = fontSize,
             fontWeight = FontWeight.Bold
         )
         Text(
             text = " You have completed ${(currentPercentage.value * number).toInt()}% of daily target",
             color = Color.Gray,
             fontSize = 12.sp,
             fontWeight = FontWeight.Normal
         )
     }
    }
}