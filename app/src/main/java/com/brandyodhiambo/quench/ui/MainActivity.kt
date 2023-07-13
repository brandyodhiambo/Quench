package com.brandyodhiambo.quench.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.brandyodhiambo.common.domain.model.AlarmData
import com.brandyodhiambo.designsystem.theme.QuenchTheme
import com.brandyodhiambo.home.presentation.home_screen.HomeViewModel
import com.brandyodhiambo.quench.navigation.FeatureNavigator
import com.brandyodhiambo.quench.navigation.NavGraphs
import com.brandyodhiambo.quench.util.AlarmSchedularImpl
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.rememberNavHostEngine
import com.ramcosta.composedestinations.scope.DestinationScope
import com.ramcosta.composedestinations.spec.NavGraphSpec
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: HomeViewModel by viewModels()
        setContent {
            QuenchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    val reminderTime = (
                        viewModel.reminderTime.value?.hour?.times(60)
                            ?: 0
                        ) * 60 * 1000 + (
                        viewModel.reminderTime.value?.minute?.times(
                            60,
                        ) ?: 0
                        ) * 1000
                    val scheduler = AlarmSchedularImpl(this)
                    val alarmItem = AlarmData(
                        time = LocalDateTime.now()
                            .plusSeconds(reminderTime.toLong()),
                        message = "It's time to drink water",
                    )
                    alarmItem.let(scheduler::schedule)
                    val navController = rememberAnimatedNavController()
                    val navHostEngine = rememberNavHostEngine()

                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        navController = navController,
                        engine = navHostEngine,
                        dependenciesContainerBuilder = {
                            dependency(
                                currentNavigator(),
                            )
                        },
                    )
                }
            }
        }
    }
}

fun DestinationScope<*>.currentNavigator(): FeatureNavigator {
    return FeatureNavigator(
        navController = navController,
        navGraph = navBackStackEntry.destination.navGraph(),
    )
}

fun NavDestination.navGraph(): NavGraphSpec {
    hierarchy.forEach { destination ->
        NavGraphs.root.nestedNavGraphs.forEach { navGraph ->
            if (destination.route == navGraph.route) {
                return navGraph
            }
        }
    }
    throw RuntimeException("No NavGraph found for $route")
}
