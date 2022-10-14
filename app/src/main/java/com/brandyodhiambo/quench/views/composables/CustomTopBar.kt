package com.brandyodhiambo.quench.views.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandyodhiambo.quench.models.TabItem
import com.brandyodhiambo.quench.views.ui.theme.primaryColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CustomTopBar(tabs:List<TabItem>, pagerState: PagerState) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = { Text(
                text = "Quench",
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )},
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = primaryColor,
            contentColor = Color.White,
            elevation = 4.dp
        )
        CustomTab(tabs = tabs, pagerState = pagerState)
        TabContent(tabs = tabs, pagerState = pagerState)
    }



}