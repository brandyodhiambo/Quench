package com.brandyodhiambo.quench.navigation

import androidx.navigation.NavController
import com.brandyodhiambo.home.presentation.destinations.SleepAndWakeTimeScreenDestination
import com.brandyodhiambo.home.presentation.sleep_wake_screen.SleepAndWakeUpScreenScreenNavigator
import com.brandyodhiambo.quench.ui.splash.SplashScreenNavigator
import com.brandyodhiambo.settings.presentation.AddReminderNavigator
import com.brandyodhiambo.settings.presentation.NotificationNavigator
import com.brandyodhiambo.settings.presentation.SettingsNavigator
import com.brandyodhiambo.settings.presentation.destinations.AddReminderScreenDestination
import com.ramcosta.composedestinations.dynamic.within
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.NavGraphSpec

class FeatureNavigator(
    private val navController: NavController,
    private val navGraph: NavGraphSpec,
) : SplashScreenNavigator,
    SleepAndWakeUpScreenScreenNavigator,
    SettingsNavigator,
    NotificationNavigator,
    AddReminderNavigator {
    override fun navigateToNotificationScreen() {
        navController.navigate(
            NavGraphs.settings.route,
        )
    }

    override fun navigateToReminderScreen() {
        navController.navigate(AddReminderScreenDestination within navGraph)
    }

    override fun navigateToSleepWakeTimeScreen() {
        navController.navigate(SleepAndWakeTimeScreenDestination within navGraph)
    }

    override fun popBackStack() {
        navController.popBackStack()
    }

    override fun navigateToMainScreen() {
        navController.navigate(
            NavGraphs.home.route,
        ) {
            popUpTo(NavGraphs.splash.route) {
                inclusive = true
            }
        }
    }

    override fun navigateUp() {
        navController.navigateUp()
    }
}
