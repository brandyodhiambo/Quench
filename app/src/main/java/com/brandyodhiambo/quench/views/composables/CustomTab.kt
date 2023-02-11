package com.brandyodhiambo.quench.views.composables

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.brandyodhiambo.quench.models.TabItem
import com.brandyodhiambo.designsystem.theme.primaryColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CustomTab(
    tabs: List<TabItem>,
    pagerState: PagerState,
) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = primaryColor,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState,tabPositions)
            )
        }) {
        tabs.forEachIndexed { index, tabItem ->
            LeadingIconTab(
                icon = { Icon(painter = painterResource(id = tabItem.icon), contentDescription = "") },
                selected = pagerState.currentPage ==index,
                onClick = { scope.launch {
                    pagerState.animateScrollToPage(index)
                } },
                text = { Text(text = tabItem.title) }
            )
        }
    }
}