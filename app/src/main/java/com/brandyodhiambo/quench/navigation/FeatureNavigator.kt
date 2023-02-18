package com.brandyodhiambo.quench.navigation

import androidx.navigation.NavController
import com.brandyodhiambo.settings.presentation.SettingsNavigator
import com.brandyodhiambo.settings.presentation.NotificationNavigator
import com.brandyodhiambo.settings.presentation.destinations.NotificationScreenDestination
import com.ramcosta.composedestinations.dynamic.within
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.NavGraphSpec

class FeatureNavigator(
    private val navController: NavController,
    private val navGraph: NavGraphSpec
) : SettingsNavigator, NotificationNavigator {
    override fun navigateToNotificationScreen() {
        navController.navigate(NotificationScreenDestination within navGraph)
    }

    override fun navigateToReminderScreen() {
        TODO("Not yet implemented")
    }
}