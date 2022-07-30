package com.brandyodhiambo.quench.views.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandyodhiambo.quench.views.ui.theme.primaryColor

@Preview(showBackground = true)
@Composable
fun CustomTopBar() {
    TopAppBar(
        title = { Text(
            text = "Quench",
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )},
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = primaryColor,
        contentColor = Color.White,
        elevation = 4.dp
    )
}