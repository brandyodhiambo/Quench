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
package com.brandyodhiambo.home.presentation.sleepWakeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.brandyodhiambo.common.domain.model.SleepTime
import com.brandyodhiambo.common.domain.model.WakeTime
import com.chargemap.compose.numberpicker.Hours
import com.chargemap.compose.numberpicker.HoursNumberPicker
import com.ramcosta.composedestinations.annotation.Destination

interface SleepAndWakeUpScreenScreenNavigator {
    fun navigateToMainScreen()
    fun popBackStack()
}

@Destination
@Composable
fun SleepAndWakeTimeScreen(
    navigator: SleepAndWakeUpScreenScreenNavigator,
    viewModel: SleepWakeViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "What's your wake up time?",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(16.dp))
            WakeTimePickerInHours(
                currentPickerValueText = viewModel.wakeTimePickerValue.value,
                onCurrentPickerValueTextChange = { viewModel.wakeTimePickerValue.value = it },
                onTimeWakeSelected = {
                    var ampm = ""
                    ampm = if (it.hours in 0..12) {
                        "AM"
                    } else {
                        "PM"
                    }
                    viewModel.onTimeWakeSelected(it.hours, it.minutes, ampm)
                }
            )

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "What's your Sleeping time?",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(16.dp))
            SleepTimePickerInHours(
                currentPickerValueText = viewModel.sleepTimePickerValue.value,
                onCurrentPickerValueTextChange = {
                    viewModel.sleepTimePickerValue.value = it
                },
                onTimeSleepSelected = {
                    var ampm = ""
                    ampm = if (it.hours in 0..12) {
                        "AM"
                    } else {
                        "PM"
                    }
                    viewModel.onTimeSleepSelected(it.hours, it.minutes, ampm)
                }
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    viewModel.insertSleepTime(
                        SleepTime(
                            viewModel.sleepSelectedTime.value.hours,
                            viewModel.sleepSelectedTime.value.minutes,
                            viewModel.sleepSelectedTime.value.amPm
                        )
                    )

                    viewModel.insertWakeTime(
                        WakeTime(
                            viewModel.wakeSelectedTime.value.hours,
                            viewModel.wakeSelectedTime.value.minutes,
                            viewModel.wakeSelectedTime.value.amPm
                        )
                    )

                    navigator.popBackStack()
                    navigator.navigateToMainScreen()
                },
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Next", Modifier.padding(8.dp), color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}

@Composable
fun SleepTimePickerInHours(
    currentPickerValueText: Hours,
    onCurrentPickerValueTextChange: (Hours) -> Unit,
    onTimeSleepSelected: (Hours) -> Unit
) {
    HoursNumberPicker(
        dividersColor = MaterialTheme.colorScheme.onBackground,
        value = currentPickerValueText,
        hoursRange = 0..23,
        onValueChange = {
            onCurrentPickerValueTextChange(it)
            onTimeSleepSelected(it)
        },
        hoursDivider = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                text = ":"
            )
        },
        minutesDivider = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                text = " "
            )
        }
    )
}

@Composable
fun WakeTimePickerInHours(
    currentPickerValueText: Hours,
    onCurrentPickerValueTextChange: (Hours) -> Unit,
    onTimeWakeSelected: (Hours) -> Unit
) {
    HoursNumberPicker(
        dividersColor = MaterialTheme.colorScheme.onBackground,
        value = currentPickerValueText,
        hoursRange = 0..12,
        onValueChange = {
            onCurrentPickerValueTextChange(it)
            onTimeWakeSelected(it)
        },
        hoursDivider = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                text = ":"
            )
        },
        minutesDivider = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                text = " "
            )
        }
    )
}
