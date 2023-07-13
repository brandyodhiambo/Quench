package com.brandyodhiambo.quench.navigation

import com.brandyodhiambo.home.presentation.destinations.HomeScreenDestination
import com.brandyodhiambo.home.presentation.destinations.SleepAndWakeTimeScreenDestination
import com.brandyodhiambo.quench.ui.destinations.MainScreenDestination
import com.brandyodhiambo.quench.ui.destinations.SplashScreenDestination
import com.brandyodhiambo.settings.presentation.destinations.AddReminderScreenDestination
import com.brandyodhiambo.settings.presentation.destinations.NotificationScreenDestination
import com.brandyodhiambo.settings.presentation.destinations.SettingScreenDestination
import com.ramcosta.composedestinations.dynamic.routedIn
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object NavGraphs {
    val splash = object : NavGraphSpec {
        override val route: String = "splash"
        override val startRoute: Route = SplashScreenDestination routedIn this
        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            SplashScreenDestination,
            SleepAndWakeTimeScreenDestination,
        ).routedIn(this).associateBy { it.route }
    }
    val home = object : NavGraphSpec {
        override val route: String = "home"
        override val startRoute: Route = MainScreenDestination routedIn this
        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            HomeScreenDestination,
            MainScreenDestination,
        ).routedIn(this).associateBy { it.route }
    }

    val settings = object : NavGraphSpec {
        override val route: String = "settings"
        override val startRoute: Route = NotificationScreenDestination routedIn this
        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            SettingScreenDestination,
            NotificationScreenDestination,
            AddReminderScreenDestination,
        ).routedIn(this).associateBy { it.route }
    }

    val root = object : NavGraphSpec {
        override val route: String = "root"
        override val startRoute: Route = splash
        override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()
        override val nestedNavGraphs: List<NavGraphSpec> = listOf(splash, home, settings)
    }
}
