package com.brandyodhiambo.home.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.sp
import com.brandyodhiambo.common.R
import com.brandyodhiambo.common.domain.model.Days
import com.brandyodhiambo.common.domain.model.ReminderTime
import com.brandyodhiambo.designsystem.theme.blackColor
import com.brandyodhiambo.designsystem.theme.primaryColor
import com.brandyodhiambo.designsystem.theme.roboto
import com.brandyodhiambo.home.presentation.home_screen.HomeViewModel
import com.chargemap.compose.numberpicker.AMPMHours
import com.chargemap.compose.numberpicker.Hours
import com.chargemap.compose.numberpicker.HoursNumberPicker

/*
*  Todo: to be changed with value from viewmodel
*
* Todo Add type converter for days
* */
val selectedDays = listOf(
    Days("M", true),
    Days("T", true),
    Days("W", true),
    Days("T", true),
    Days("F", true),
    Days("S", true),
    Days("S", false),
)

@Composable
fun TimeSetterDialog(
    modifier: Modifier = Modifier,
    openDialogCustom: MutableState<Boolean>,
    viewModel: HomeViewModel,
) {
    val isAllDaySelected = remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
        elevation = 8.dp,
    ) {
        Column(
            modifier
                .background(Color.White),
        ) {
            Image(
                painter = painterResource(id = R.drawable.alarm_clock),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(
                    color = primaryColor,
                ),
                modifier = Modifier
                    .padding(top = 35.dp)
                    .height(70.dp)
                    .fillMaxWidth(),

            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Reminder Time",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = roboto,
                )
                Spacer(modifier = Modifier.height(8.dp))
                TimePickerForDialogInHours(
                    currentPickerValueText = viewModel.reminderTimePickerValue.value,
                    onPickerValueChange = {
                        viewModel.reminderTimePickerValue.value = it
                    },
                    onReminderTimeSelected = {
                        var ampm = ""
                        ampm = if (it.hours in 0..12) {
                            "AM"
                        } else {
                            "PM"
                        }
                        viewModel.onReminderTimeSelected(
                            hours = it.hours,
                            minutes = it.minutes,
                            amPm = ampm,
                            isReapeated = false,
                            isAllDay = isAllDaySelected.value,
                            days = listOf(
                                "M",
                                "T",
                                "W",
                                "T",
                                "F",
                                "S",
                                "S",
                            ),

                        )
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
                        fontSize = 14.sp,
                        fontFamily = roboto,
                        modifier = Modifier
                            .padding(top = 5.dp),
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    TextButton(onClick = {
                        isAllDaySelected.value = !isAllDaySelected.value
                    }) {
                        Text(
                            text = "All Day",
                            color = primaryColor,
                            fontSize = 12.sp,
                            fontFamily = roboto,
                            modifier = Modifier
                                .padding(top = 5.dp),
                            fontWeight = FontWeight.W100,
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
                    items(viewModel.reminderSelectedTime.value.days) { singleDay ->
                        DayItem(
                            color = /*if () */primaryColor, /*else blackColor*/
                            text = singleDay,
                            onClick = {
                            },
                        )
                    }
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(Color.White),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                TextButton(onClick = {
                    openDialogCustom.value = false
                }) {
                    Text(
                        "Cancel",
                        fontWeight = FontWeight.Bold,
                        fontFamily = roboto,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                    )
                }
                TextButton(onClick = {
                    openDialogCustom.value = false
                    viewModel.insertRemindTime(
                        ReminderTime(
                            hour = viewModel.reminderSelectedTime.value.hour,
                            minute = viewModel.reminderSelectedTime.value.minute,
                            ampm = viewModel.reminderSelectedTime.value.ampm,
                            isRepeated = false,
                            isAllDay = isAllDaySelected.value,
                            days = listOf(
                                "M",
                                "T",
                                "W",
                                "T",
                                "F",
                                "S",
                                "S",
                            ),
                        ),
                    )
                }) {
                    Text(
                        "Okay",
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = roboto,
                        color = primaryColor,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun TimePickerForDialogInHours(
    currentPickerValueText: Hours,
    onPickerValueChange: (Hours) -> Unit,
    onReminderTimeSelected: (Hours) -> Unit,
) {
    var pickerValue by remember { mutableStateOf<Hours>(AMPMHours(0, 0, AMPMHours.DayTime.AM)) }
    HoursNumberPicker(
        dividersColor = blackColor,
        value = currentPickerValueText,
        onValueChange = {
            onPickerValueChange(it)
            onReminderTimeSelected(it)
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
                fontSize = 14.sp,
                fontFamily = roboto,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
