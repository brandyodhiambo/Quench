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
package com.brandyodhiambo.home.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.brandyodhiambo.common.R
import com.brandyodhiambo.common.domain.model.Days
import com.chargemap.compose.numberpicker.Hours
import com.chargemap.compose.numberpicker.HoursNumberPicker

@Composable
fun TimeSetterDialog(
    currentPickerValueText: Hours,
    reminderDays: List<Days>,
    onCurrentPickerValueChanged: (Hours) -> Unit,
    onDismiss: () -> Unit,
    onAllDayClicked: () -> Unit,
    onConfirmClick: () -> Unit,
) {
    AlertDialog(
        backgroundColor = Color.White,
        onDismissRequest = { onDismiss() },
        title = {
            Image(
                painter = painterResource(id = R.drawable.alarm_clock),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(
                    color = MaterialTheme.colorScheme.primary,
                ),
                modifier = Modifier
                    .padding(top = 35.dp)
                    .height(70.dp)
                    .fillMaxWidth(),

            )
        },
        text = {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Reminder Time",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelMedium,
                )
                Spacer(modifier = Modifier.height(8.dp))
                TimePickerForDialogInHours(
                    currentPickerValueText = currentPickerValueText,
                    onPickerValueChange = {
                        onCurrentPickerValueChanged(it)
                    },
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Reminder Time",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .padding(top = 5.dp),
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    TextButton(onClick = {
                        onAllDayClicked()
                    }) {
                        Text(
                            text = "All Day",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier
                                .padding(top = 5.dp),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(reminderDays) { singleDay ->
                        DayItem(
                            color = if (singleDay.isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                            text = singleDay.day,
                            onClick = {
                                singleDay.isSelected = !singleDay.isSelected
                                // get the selected day and add it to the list
                            },
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirmClick()
            }) {
                Text(
                    "Confirm",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                Text(
                    "Cancel",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                )
            }
        },
    )
}

@Composable
fun TimePickerForDialogInHours(
    currentPickerValueText: Hours,
    onPickerValueChange: (Hours) -> Unit,
) {
    HoursNumberPicker(
        dividersColor = MaterialTheme.colorScheme.onBackground,
        value = currentPickerValueText,
        hoursRange = 0..23,
        onValueChange = {
            onPickerValueChange(it)
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

@Composable
fun DayItem(color: Color, text: String, onClick: () -> Unit = {}) {
    Card(
        shape = CircleShape,
        border = BorderStroke(2.dp, color = color),
        modifier = Modifier
            .size(40.dp)
            .padding(4.dp)
            .clickable {
                onClick()
            },
        elevation = 8.dp,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                color = color,
                style = MaterialTheme.typography.labelMedium,

            )
        }
    }
}
