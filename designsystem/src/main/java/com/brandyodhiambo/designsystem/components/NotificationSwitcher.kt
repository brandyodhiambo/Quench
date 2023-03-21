package com.brandyodhiambo.designsystem.components

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brandyodhiambo.designsystem.theme.primaryColor

@Composable
fun NotificationSwitcher(
    isOn: Boolean = false,
    size: Dp = 150.dp,
    iconSize: Dp = 10.dp,
    padding: Dp = 10.dp,
    borderWidth: Dp = 1.dp,
    parentShape: Shape = CircleShape,
    toggleShape: Shape = CircleShape,
    animationSpecs: AnimationSpec<Dp> = tween(durationMillis = 300),
    onToggle: () -> Unit,
) {
    val offset by animateDpAsState(
        targetValue = if (isOn) 0.dp else size,
        animationSpec = animationSpecs
    )

    Box(modifier = Modifier
        .width(size * 2)
        .height(size)
        .clip(shape = parentShape)
        .clickable { onToggle() }
        .background(color = if (isOn) primaryColor else Color.LightGray)
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .offset(x = offset)
                .padding(all = padding)
                .clip(shape = toggleShape)
                .background(color = Color.White)
        ) {}
        Row(
            modifier = Modifier
                .border(
                    border = BorderStroke(
                        width = borderWidth,
                        color = Color.White
                    ),
                    shape = parentShape
                )

        ) {
            Box(
                modifier = Modifier
                    .size(size),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notification_icon",
                    tint = if (isOn) primaryColor else Color.LightGray
                )
            }

            Box(
                modifier = Modifier
                    .size(size),
                contentAlignment = Alignment.Center

            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = Icons.Default.Star,
                    contentDescription = "Notification_icon",
                    tint = if (isOn) Color.LightGray else primaryColor
                )
            }
        }
    }


}