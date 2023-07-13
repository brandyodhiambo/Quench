package com.brandyodhiambo.home.presentation.sleep_wake_screen

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.brandyodhiambo.common.domain.model.SleepTime
import com.brandyodhiambo.common.domain.model.WakeTime
import com.brandyodhiambo.designsystem.theme.blackColor
import com.brandyodhiambo.designsystem.theme.primaryColor
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
    viewModel: SleepWakeViewModel = hiltViewModel(),
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "What's your wake up time?",
                fontSize = 24.sp,
                color = blackColor,
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
                },
            )

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "What's your Sleeping time?",
                fontSize = 24.sp,
                color = blackColor,
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
                },
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    viewModel.insertSleepTime(
                        SleepTime(
                            viewModel.sleepSelectedTime.value.hours,
                            viewModel.sleepSelectedTime.value.minutes,
                            viewModel.sleepSelectedTime.value.amPm,
                        ),
                    )

                    viewModel.insertWakeTime(
                        WakeTime(
                            viewModel.wakeSelectedTime.value.hours,
                            viewModel.wakeSelectedTime.value.minutes,
                            viewModel.wakeSelectedTime.value.amPm,
                        ),
                    )

                    navigator.popBackStack()
                    navigator.navigateToMainScreen()
                },
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = primaryColor),
            ) {
                Text(text = "Next", Modifier.padding(8.dp), color = Color.White)
            }
        }
    }
}

@Composable
fun SleepTimePickerInHours(
    currentPickerValueText: Hours,
    onCurrentPickerValueTextChange: (Hours) -> Unit,
    onTimeSleepSelected: (Hours) -> Unit,
) {
    HoursNumberPicker(
        dividersColor = blackColor,
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
fun WakeTimePickerInHours(
    currentPickerValueText: Hours,
    onCurrentPickerValueTextChange: (Hours) -> Unit,
    onTimeWakeSelected: (Hours) -> Unit,
) {
    HoursNumberPicker(
        dividersColor = blackColor,
        value = currentPickerValueText,
        hoursRange = 0..23,
        onValueChange = {
            onCurrentPickerValueTextChange(it)
            onTimeWakeSelected(it)
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
