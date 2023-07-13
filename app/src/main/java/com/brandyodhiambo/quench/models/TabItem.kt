package com.brandyodhiambo.quench.models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.brandyodhiambo.home.presentation.home_screen.HomeScreen
import com.brandyodhiambo.quench.R
import com.brandyodhiambo.settings.presentation.SettingScreen
import com.brandyodhiambo.settings.presentation.SettingsNavigator
import com.brandyodhiambo.statistics.presentation.StatisticsScreen
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

typealias ComposableFun = @Composable (onClick: () -> Unit) -> Unit

sealed class TabItem(var icon: Int, var title: String, var screen: ComposableFun) {
    @RequiresApi(Build.VERSION_CODES.O)
    data class Home(val navigator: DestinationsNavigator) :
        TabItem(R.drawable.ic_home, "Home", { HomeScreen() })

    data class Statistic(val navigator: DestinationsNavigator) :
        TabItem(R.drawable.ic_statistics, "Statistic", { StatisticsScreen(navigator = navigator) })

    data class Settings(val navigator: SettingsNavigator) :
        TabItem(R.drawable.ic_settings, "Settings", { SettingScreen(navigator = navigator) })
}
