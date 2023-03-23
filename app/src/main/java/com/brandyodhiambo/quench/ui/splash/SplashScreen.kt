package com.brandyodhiambo.quench.ui.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.brandyodhiambo.designsystem.components.Loader
import com.brandyodhiambo.home.presentation.sleep_wake_screen.SleepWakeViewModel
import com.brandyodhiambo.quench.R
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
    navigator: SplashScreenNavigator,
    viewModel: SleepWakeViewModel = hiltViewModel()
) {

    val sleepTime = viewModel.sleepTime.observeAsState()
    val wakeTime = viewModel.wakeTime.observeAsState()

    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LaunchedEffect(key1 = true) {
            withContext(Dispatchers.Main) {
                delay(3000)
                if (sleepTime.value == null || wakeTime.value == null) {
                    navigator.popBackStack()
                    navigator.navigateToSleepWakeTimeScreen()
                } else {
                    navigator.popBackStack()
                    navigator.navigateToMainScreen()
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Loader(R.raw.water_drops)
    }
}
