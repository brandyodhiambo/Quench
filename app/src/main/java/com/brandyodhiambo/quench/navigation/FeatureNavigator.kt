package com.brandyodhiambo.quench.navigation

import androidx.navigation.NavController
import com.brandyodhiambo.home.presentation.SleepAndWakeUpScreenScreenNavigator
import com.brandyodhiambo.home.presentation.destinations.SleepAndWakeTimeScreenDestination
import com.brandyodhiambo.quench.ui.destinations.MainScreenDestination
import com.brandyodhiambo.quench.ui.splash.SplashScreenNavigator
import com.brandyodhiambo.settings.presentation.AddReminderNavigator
import com.brandyodhiambo.settings.presentation.NotificationNavigator
import com.brandyodhiambo.settings.presentation.SettingsNavigator
import com.brandyodhiambo.settings.presentation.destinations.AddReminderScreenDestination
import com.brandyodhiambo.settings.presentation.destinations.NotificationScreenDestination
import com.ramcosta.composedestinations.dynamic.within
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.NavGraphSpec

class FeatureNavigator(
    private val navController: NavController,
    private val navGraph: NavGraphSpec
) : SplashScreenNavigator, SleepAndWakeUpScreenScreenNavigator,SettingsNavigator, NotificationNavigator, AddReminderNavigator {
    override fun navigateToNotificationScreen() {
        navController.navigate(NotificationScreenDestination within navGraph)
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
       navController.navigate(MainScreenDestination within navGraph)
    }

    override fun navigateUp() {
        navController.navigateUp()
    }
}
