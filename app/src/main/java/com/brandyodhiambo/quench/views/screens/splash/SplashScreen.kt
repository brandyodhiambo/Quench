package com.brandyodhiambo.quench.views.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.brandyodhiambo.quench.R
import com.brandyodhiambo.quench.views.screens.destinations.SleepAndWakeTimeScreenDestination
import com.brandyodhiambo.quench.views.screens.dialogs.Loader
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Destination(start = true)
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator
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
                navigator.navigate(SleepAndWakeTimeScreenDestination)
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Loader()
    }
}