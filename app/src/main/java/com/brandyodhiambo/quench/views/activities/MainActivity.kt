package com.brandyodhiambo.quench.views.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.brandyodhiambo.quench.models.TabItem
import com.brandyodhiambo.quench.views.composables.CustomTab
import com.brandyodhiambo.quench.views.composables.CustomTopBar
import com.brandyodhiambo.quench.views.composables.TabContent
import com.brandyodhiambo.quench.views.screens.NavGraphs
import com.brandyodhiambo.quench.views.screens.destinations.HomeScreenDestination
import com.brandyodhiambo.quench.views.screens.destinations.SettingScreenDestination
import com.brandyodhiambo.quench.views.screens.destinations.StatisticsScreenDestination
import com.brandyodhiambo.quench.views.ui.theme.QuenchTheme
import com.brandyodhiambo.quench.views.ui.theme.primaryColor
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
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

                    val showTopAppBar: Boolean
                    val navController = rememberAnimatedNavController()
                    val navHostEngine = rememberNavHostEngine()

                    val newBackStackEntry by navController.currentBackStackEntryAsState()
                    val route = newBackStackEntry?.destination?.route

                    val tabs = listOf(
                        TabItem.Home,
                        TabItem.Statistic,
                        TabItem.Settings
                    )
                    val pagerState = rememberPagerState()

                    showTopAppBar = route in listOf(
                        HomeScreenDestination.route,
                        StatisticsScreenDestination.route,
                        SettingScreenDestination.route,
                    )
                    Scaffold(
                        topBar = {
                            if (showTopAppBar) {
                                CustomTopBar(tabs, pagerState)
                            }
                        }
                    ) { paddingValues ->
                        Box(modifier = Modifier.padding(paddingValues)) {
                            DestinationsNavHost(
                                navGraph = NavGraphs.root,
                                navController = navController,
                                engine = navHostEngine
                            )
                        }

                    }
                }
            }
        }
    }
}
