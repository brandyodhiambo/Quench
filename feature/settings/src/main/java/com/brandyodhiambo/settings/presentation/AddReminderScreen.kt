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
package com.brandyodhiambo.settings.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.brandyodhiambo.common.R
import com.brandyodhiambo.common.domain.model.ReminderMode
import com.brandyodhiambo.common.util.getCurrentDay
import com.brandyodhiambo.designsystem.components.NotificationSwitcher
import com.brandyodhiambo.settings.presentation.component.CustomReminderModeDialog
import com.chargemap.compose.numberpicker.AMPMHours
import com.chargemap.compose.numberpicker.Hours
import com.chargemap.compose.numberpicker.HoursNumberPicker
import com.ramcosta.composedestinations.annotation.Destination

interface AddReminderNavigator {
    fun navigateUp()
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun AddReminderScreen(
    navigator: AddReminderNavigator,
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    val repeateModeDialog = settingsViewModel.repeatModeDialog.value
    Scaffold(
        topBar = {
            TopAppBarAddReminder(navigator = navigator, onSave = {
                settingsViewModel.insertReminderMode(
                    ReminderMode(
                        mode = settingsViewModel.selectedMode.value,
                        days = settingsViewModel.selectedCustomDays.value,
                        isOn = true,
                        isDeleted = settingsViewModel.isDeleted.value ,
                        isVibrated = settingsViewModel.isVibrated.value,
                        hour = settingsViewModel.hour.value,
                        minutes = settingsViewModel.minutes.value,
                        ampm = settingsViewModel.ampm.value,
                    )
                )
            })
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // ReminderTime
            ReminderTimePickerInHours(
                currentPickerValueText = settingsViewModel.timePickerValue.value,
                onCurrentPickerValueTextChange = {
                    settingsViewModel.timePickerValue.value = it
                },
                onTimeSleepSelected = {
                    var ampm = ""
                    ampm = if (it.hours in 0..12) {
                        "AM"
                    } else {
                        "PM"
                    }
                    settingsViewModel.onTimeSelected(it.hours, it.minutes, ampm)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Reminder Mode",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = settingsViewModel.selectedMode.value,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    IconButton(onClick = { settingsViewModel.setRepeatModeDialog(true)}) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chevron_right),
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = null
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Vibrate when alarm sounds",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                NotificationSwitcher(
                    isOn = settingsViewModel.isVibrated.value,
                    size = 35.dp,
                    padding = 5.dp,
                    onToggle = {
                        settingsViewModel.setIsVibrated(settingsViewModel.isVibrated.value.not())
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Delete after goes off",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                NotificationSwitcher(
                    isOn = settingsViewModel.isDeleted.value,
                    size = 35.dp,
                    padding = 5.dp,
                    onToggle = {
                        settingsViewModel.setIsDeleted(settingsViewModel.isDeleted.value.not())
                    }
                )
            }

            if (repeateModeDialog) {
                Dialog(onDismissRequest = { settingsViewModel.setRepeatModeDialog(false)}) {
                    val repeatModes = listOf("Once", "Mon to Fri", "Daily", "Custom")
                    CustomReminderModeDialog(
                        items = repeatModes,
                        selectedValue = settingsViewModel.selectedMode.value,
                        title = "Repeat Mode",
                        isSelectedItem = {
                            settingsViewModel.selectedMode.value == it
                        },
                        onChangeState = {
                            settingsViewModel.onSelectedMode(it)
                        } ,
                        onCustomReminderDialog = {
                            settingsViewModel.setRepeatModeDialog(value = false)
                            when (settingsViewModel.selectedMode.value) {
                                "Once" -> {
                                    val currentDay = getCurrentDay()
                                    settingsViewModel.onSelectedDayChangeState(listOf(currentDay))
                                }
                                "Mon to Fri" -> {
                                    val weekdays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
                                    settingsViewModel.onSelectedDayChangeState(weekdays)
                                }
                                "Daily" -> {
                                    val allDays = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
                                    settingsViewModel.onSelectedDayChangeState(allDays)
                                }
                                "Custom" -> {
                                    //selectedCustomDays
                                    settingsViewModel.onSelectedDayChangeState(settingsViewModel.selectedCustomDays.value)
                                }
                            }
                                                 },
                        onSelectedDaysChangeState = {
                            settingsViewModel.onSelectedDayChangeState(it)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarAddReminder(navigator: AddReminderNavigator, onSave: ()->Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Add Reminder",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        navigationIcon = {
            IconButton(onClick = { navigator.navigateUp() }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            TextButton(onClick = onSave) {
                Text(
                    text = "Save",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}

@Composable
fun ReminderTimePickerInHours(
    currentPickerValueText: Hours,
    onCurrentPickerValueTextChange: (Hours) -> Unit,
    onTimeSleepSelected: (Hours) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HoursNumberPicker(
            dividersColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
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
                    text = ":",
                    color = MaterialTheme.colorScheme.onBackground
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
}
