@file:OptIn(ExperimentalPagerApi::class)

package com.brandyodhiambo.quench.views.composables

import androidx.compose.runtime.Composable
import com.brandyodhiambo.quench.models.TabItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(tabs:List<TabItem>,pagerState: PagerState) {
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        tabs[page].screen()
    }
}