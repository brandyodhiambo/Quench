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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.brandyodhiambo.common.R
import com.brandyodhiambo.designsystem.components.NotificationSwitcher
import com.brandyodhiambo.settings.presentation.component.CustomReminderDialog
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
) {
    val repeateModeDialog = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBarAddReminder(navigator = navigator)
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ReminderTimePickerInHours()
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Reminder Sound",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Default Sound",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chevron_right),
                            contentDescription = null,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Reminder Mode",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Mon to Fri",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    IconButton(onClick = { repeateModeDialog.value = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chevron_right),
                            contentDescription = null,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Vibrate when alarm sounds",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                NotificationSwitcher(
                    isOn = true,
                    size = 35.dp,
                    padding = 5.dp,
                    onToggle = {
                    },
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Delete after goes off",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                NotificationSwitcher(
                    isOn = false,
                    size = 35.dp,
                    padding = 5.dp,
                    onToggle = {
                    },
                )
            }

            if (repeateModeDialog.value) {
                Dialog(onDismissRequest = { repeateModeDialog.value }) {
                    val repeatMode = listOf("Once", "Mon to Fri", "Daily", "Custom")
                    CustomReminderDialog(
                        openDialog = repeateModeDialog,
                        items = repeatMode,
                        title = "Repeat Mode",
                    )
                }
            }
        }
    }
}

@Composable
fun TopAppBarAddReminder(navigator: AddReminderNavigator) {
    TopAppBar(
        title = {
            Text(text = "Add Reminder", color = Color.Black, fontSize = 16.sp)
        },
        navigationIcon = {
            IconButton(onClick = { navigator.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, tint = Color.Black, contentDescription = "Back")
            }
        },
        actions = {
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Save", color = Color.Black, fontSize = 16.sp)
            }
        },
        backgroundColor = Color.White,
    )
}

@Composable
fun ReminderTimePickerInHours() {
    Column(
        modifier = Modifier.fillMaxWidth().height(200.dp).padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var pickerValue by remember { mutableStateOf<Hours>(AMPMHours(0, 0, AMPMHours.DayTime.AM)) }
        HoursNumberPicker(
            dividersColor = MaterialTheme.colorScheme.onBackground,
            value = pickerValue,
            onValueChange = {
                pickerValue = it
            },
            hoursDivider = {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    textAlign = TextAlign.Center,
                    text = ":",
                )
            },
            minutesDivider = {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    textAlign = TextAlign.Center,
                    text = " ",
                )
            },
        )
    }
}
