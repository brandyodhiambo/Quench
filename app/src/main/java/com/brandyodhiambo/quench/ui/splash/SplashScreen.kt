package com.brandyodhiambo.quench.ui.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.brandyodhiambo.quench.R
import com.brandyodhiambo.quench.views.screens.dialogs.Loader
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


interface SplashScreenNavigator {
    fun navigateToSleepWakeTimeScreen()
    fun popBackStack()
    fun navigateToMainScreen()

}
@Composable
@Destination(start = true)
fun SplashScreen(
    navigator: SplashScreenNavigator
) {
    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LaunchedEffect(key1 = true) {
            withContext(Dispatchers.Main) {
                delay(3000)
                navigator.popBackStack()
                navigator.navigateToSleepWakeTimeScreen()
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Loader(R.raw.water_drops)
    }
}
