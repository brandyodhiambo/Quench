package com.brandyodhiambo.quench.views.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.brandyodhiambo.quench.models.TabItem
import com.brandyodhiambo.quench.views.screens.NavGraphs
import com.brandyodhiambo.quench.views.ui.theme.QuenchTheme
import com.brandyodhiambo.quench.views.ui.theme.primaryColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.DestinationsNavHost

@OptIn(ExperimentalPagerApi::class)
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuenchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val tabs = listOf(
                        TabItem.Home,
                        TabItem.Statistics,
                        TabItem.Settings
                    )
                    val pagerState = rememberPagerState()
                    Scaffold(
                        topBar = {

                        }
                    ) {

                    }
                   // DestinationsNavHost(navGraph = NavGraphs.root )
                }
            }
        }
    }
}
