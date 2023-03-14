package com.brandyodhiambo.quench.navigation


import com.brandyodhiambo.home.presentation.destinations.HomeScreenDestination
import com.brandyodhiambo.quench.ui.destinations.MainScreenDestination
import com.brandyodhiambo.settings.presentation.destinations.AddReminderScreenDestination
import com.brandyodhiambo.settings.presentation.destinations.NotificationScreenDestination
import com.brandyodhiambo.settings.presentation.destinations.SettingScreenDestination
import com.brandyodhiambo.statistics.presentation.destinations.StatisticsScreenDestination
import com.ramcosta.composedestinations.dynamic.routedIn
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object NavGraphs {
    val home = object : NavGraphSpec {
        override val route: String = "home"
        override val startRoute: Route = MainScreenDestination routedIn this
        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            HomeScreenDestination,
            MainScreenDestination,
            NotificationScreenDestination,
            AddReminderScreenDestination
        ).routedIn(this).associateBy { it.route }
    }

    val statistics = object : NavGraphSpec {
        override val route: String = "statistics"
        override val startRoute: Route = StatisticsScreenDestination routedIn this
        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            StatisticsScreenDestination
        ).routedIn(this).associateBy { it.route }
    }

    val settings = object : NavGraphSpec {
        override val route: String = "settings"
        override val startRoute: Route = SettingScreenDestination routedIn this
        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            SettingScreenDestination,
            NotificationScreenDestination,
            AddReminderScreenDestination
        ).routedIn(this).associateBy { it.route }
    }

    val root = object : NavGraphSpec {
        override val route: String = "root"
        override val startRoute: Route = home
        override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()
        override val nestedNavGraphs: List<NavGraphSpec> = listOf(home, statistics, settings)
    }
}
