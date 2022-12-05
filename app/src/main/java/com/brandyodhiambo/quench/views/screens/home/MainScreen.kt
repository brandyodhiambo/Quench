package com.brandyodhiambo.quench.views.screens.home

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.brandyodhiambo.quench.models.TabItem
import com.brandyodhiambo.quench.views.composables.CustomTopBar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Destination
fun MainScreen(
    navigator: DestinationsNavigator
) {

    Scaffold {
        val tabs = listOf(
            TabItem.Home(navigator = navigator),
            TabItem.Statistic(navigator = navigator),
            TabItem.Settings(navigator = navigator),
        )
        val pagerState = rememberPagerState()
        CustomTopBar(tabs, pagerState)
    }

}