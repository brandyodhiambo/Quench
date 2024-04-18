/*
 * Copyright (C)2023 Brandy Odhiambo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import com.brandyodhiambo.home.presentation.sleepWakeScreen.SleepWakeViewModel
import com.brandyodhiambo.quench.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

interface SplashScreenNavigator {
    fun navigateToSleepWakeTimeScreen()
    fun popBackStack()
    fun navigateToMainScreen()
}

@Composable
@Destination
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
