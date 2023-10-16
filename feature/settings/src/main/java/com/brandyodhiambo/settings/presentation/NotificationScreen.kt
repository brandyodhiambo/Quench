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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandyodhiambo.common.domain.model.Day
import com.brandyodhiambo.common.util.toInitials
import com.brandyodhiambo.designsystem.components.NotificationSwitcher
import com.ramcosta.composedestinations.annotation.Destination

interface NotificationNavigator {
    fun navigateToReminderScreen()

    fun popBackStack()
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun NotificationScreen(
    navigator: NotificationNavigator
) {
    val days = listOf(
        Day("Monday", true),
        Day("Tuesday", true),
        Day("Wednesday", true),
        Day("Thursday", true),
        Day("Friday", true),
        Day("Saturday", true),
        Day("Sunday", false)
    )

    val reminder = listOf(
        Reminder(time = "8:20 AM", days = days, isOn = true),
        Reminder(time = "9:30 AM", days = days, isOn = false),
        Reminder(time = "10:20 AM", days = days, isOn = true),
        Reminder(time = "11:40 AM", days = days, isOn = true)

    )

    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.primary,
        topBar = {
            TopAppBar(
                title = { Text(text = "Notification", color = MaterialTheme.colorScheme.onPrimary, fontSize = 16.sp) },
                backgroundColor = MaterialTheme.colorScheme.onBackground,
                navigationIcon = {
                    IconButton(onClick = {
                        navigator.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
            ) {
                item {
                    AddReminder(navigator = navigator)
                }
                if (reminder.isEmpty()) {
                    item {
                        // Loader(compositions = R.raw.clock)
                    }
                } else {
                    items(reminder) { reminder ->
                        ReminderNotificationTime(reminder = reminder)
                    }
                }
            }
        }
    }
}

@Composable
fun AddReminder(navigator: NotificationNavigator) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Add New Reminder",
                color = Color.Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            IconButton(onClick = { navigator.navigateToReminderScreen() }) {
                Icon(imageVector = Icons.Default.Add, tint = Color.Gray, contentDescription = null)
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun ReminderNotificationTime(reminder: Reminder) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if (reminder.isOn) {
                        MaterialTheme.colorScheme.onPrimary
                    } else {
                        Color.LightGray
                    }
                )
                .padding(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Text(
                        text = reminder.time,
                        color = if (reminder.isOn) {
                            Color.Black
                        } else {
                            Color.Gray
                        },
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    LazyRow() {
                        items(reminder.days) { day ->
                            WeeksReminder(day = day, reminder = reminder)
                        }
                    }
                }
                // Switch
                NotificationSwitcher(
                    isOn = reminder.isOn,
                    size = 30.dp,
                    padding = 5.dp,
                    onToggle = {
                        !reminder.isOn
                    }
                )
            }
        }
    }
}

@Composable
fun WeeksReminder(day: Day, reminder: Reminder) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(4.dp)
                .size(25.dp)
                .border(
                    border = BorderStroke(
                        2.dp,
                        color = if (day.isOn && reminder.isOn) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
                        }
                    ),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = day.day.toInitials(),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

data class Reminder(
    val time: String,
    val days: List<Day>,
    val isOn: Boolean
)
