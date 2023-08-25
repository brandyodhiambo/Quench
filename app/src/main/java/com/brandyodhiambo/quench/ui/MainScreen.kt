/*
 * Copyright (C)2023 Brandy Odhiambo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.brandyodhiambo.quench.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.Scaffold
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandyodhiambo.designsystem.theme.primaryColor
import com.brandyodhiambo.designsystem.theme.roboto
import com.brandyodhiambo.quench.models.TabItem
import com.brandyodhiambo.settings.presentation.SettingsNavigator
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Destination
fun MainScreen(
    navigator: DestinationsNavigator,
    settingsNavigator: SettingsNavigator
) {
    Scaffold {
        val tabs = listOf(
            TabItem.Home(navigator = navigator),
            TabItem.Statistic(navigator = navigator),
            TabItem.Settings(navigator = settingsNavigator)
        )
        val pagerState = rememberPagerState()

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = "Quench",
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = roboto
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = primaryColor,
                contentColor = Color.White,
                elevation = 4.dp
            )
            CustomTab(tabs = tabs, pagerState = pagerState)
            TabContent(
                tabs = tabs,
                pagerState = pagerState
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(
    tabs: List<TabItem>,
    pagerState: PagerState
) {
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        tabs[page].screen(
            onClick = { }
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CustomTab(
    tabs: List<TabItem>,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = primaryColor,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        tabs.forEachIndexed { index, tabItem ->
            LeadingIconTab(
                icon = {
                    Icon(
                        painter = painterResource(id = tabItem.icon),
                        contentDescription = ""
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = { Text(text = tabItem.title, fontFamily = roboto) }
            )
        }
    }
}
