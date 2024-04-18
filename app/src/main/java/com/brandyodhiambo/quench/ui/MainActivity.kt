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
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.rememberNavController
import com.brandyodhiambo.common.domain.model.AlarmData
import com.brandyodhiambo.designsystem.theme.QuenchTheme
import com.brandyodhiambo.designsystem.theme.Theme
import com.brandyodhiambo.home.presentation.homeScreen.HomeViewModel
import com.brandyodhiambo.quench.R
import com.brandyodhiambo.quench.navigation.FeatureNavigator
import com.brandyodhiambo.quench.navigation.QuenchNavGraphs
import com.brandyodhiambo.quench.util.AlarmSchedularImpl
import com.brandyodhiambo.quench.util.createChannel
import com.brandyodhiambo.statistics.worker.startAchievementOnetimeWorkRequest
import com.brandyodhiambo.statistics.worker.startDailyOnetimeWorkRequest
import com.brandyodhiambo.statistics.worker.startMonthlyOnetimeWorkRequest
import com.brandyodhiambo.statistics.worker.startWeeklyOnetimeWorkRequest
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
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
            createChannel(this)
            QuenchTheme(
                theme = Theme.FOLLOW_SYSTEM.themeValue
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    startAchievementOnetimeWorkRequest(applicationContext)
                    startDailyOnetimeWorkRequest(applicationContext)
                    startMonthlyOnetimeWorkRequest(applicationContext)
                    startWeeklyOnetimeWorkRequest(applicationContext)

                    val reminderTimeFromDb = viewModel.reminderTime?.observeAsState()
                    val hours = reminderTimeFromDb?.value?.hour ?: 0
                    val minutes = reminderTimeFromDb?.value?.minute ?: 0
                    val scheduler = AlarmSchedularImpl(this)
                    val alarmItem = AlarmData(
                        time = LocalDateTime.now().withHour(hours).withMinute(minutes),
                        message = getString(R.string.it_s_time_to_drink_water)
                    )
                    alarmItem.let(scheduler::schedule)
                    val navController = rememberNavController()
                    val navHostEngine = rememberNavHostEngine()

                    DestinationsNavHost(
                        navGraph = QuenchNavGraphs.root,
                        navController = navController,
                        engine = navHostEngine,
                        dependenciesContainerBuilder = {
                            dependency(
                                currentNavigator()
                            )
                        }
                    )
                }
            }
        }
    }
}

fun DependenciesContainerBuilder<*>.currentNavigator(): FeatureNavigator {
    return FeatureNavigator(
        navController = navController,
        navGraph = navBackStackEntry.destination.navGraph()
    )
}

fun NavDestination.navGraph(): NavGraphSpec {
    hierarchy.forEach { destination ->
        QuenchNavGraphs.root.nestedNavGraphs.forEach { navGraph ->
            if (destination.route == navGraph.route) {
                return navGraph
            }
        }
    }
    throw RuntimeException("No NavGraph found for $route")
}
