package com.brandyodhiambo.quench.models

import com.brandyodhiambo.quench.R

import androidx.compose.runtime.Composable
import com.brandyodhiambo.quench.views.screens.destinations.Destination
import com.brandyodhiambo.quench.views.screens.destinations.HomeScreenDestination
import com.brandyodhiambo.quench.views.screens.destinations.SettingScreenDestination
import com.brandyodhiambo.quench.views.screens.destinations.StatisticsScreenDestination
import com.brandyodhiambo.quench.views.screens.home.HomeScreen
import com.brandyodhiambo.quench.views.screens.notification.NotificationScreen
import com.brandyodhiambo.quench.views.screens.settings.SettingScreen
import com.brandyodhiambo.quench.views.screens.statistics.StatisticsScreen
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

typealias ComposableFun = @Composable (onClick: ()-> Unit) -> Unit


sealed class TabItem(var icon: Int, var title: String, var screen: ComposableFun) {
    data class Home(val navigator: DestinationsNavigator) : TabItem(R.drawable.ic_home, "Home", { HomeScreen(navigator = navigator) })
    data class Statistic(val navigator: DestinationsNavigator) : TabItem(R.drawable.ic_statistics, "Statistic", { StatisticsScreen(navigator = navigator) })
    data class Settings(val navigator: DestinationsNavigator) : TabItem(R.drawable.ic_settings, "Settings", { SettingScreen(navigator = navigator) })
    //object Notifications : TabItem(R.drawable.ic_notification, "Notifications", { NotificationScreen() })
}

