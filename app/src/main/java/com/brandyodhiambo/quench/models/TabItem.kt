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
package com.brandyodhiambo.quench.models

import androidx.compose.runtime.Composable
import com.brandyodhiambo.home.presentation.homeScreen.HomeScreen
import com.brandyodhiambo.quench.R
import com.brandyodhiambo.settings.presentation.SettingScreen
import com.brandyodhiambo.settings.presentation.SettingsNavigator
import com.brandyodhiambo.statistics.presentation.StatisticsScreen
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

typealias ComposableFun = @Composable (onClick: () -> Unit) -> Unit

sealed class TabItem(var icon: Int, var title: String, var screen: ComposableFun) {
    data class Home(val navigator: DestinationsNavigator) :
        TabItem(R.drawable.ic_home, "Home", { HomeScreen() })

    data class Statistic(val navigator: DestinationsNavigator) :
        TabItem(R.drawable.ic_statistics, "Statistic", { StatisticsScreen(navigator = navigator) })

    data class Settings(val navigator: SettingsNavigator) :
        TabItem(R.drawable.ic_settings, "Settings", { SettingScreen(navigator = navigator) })
}
