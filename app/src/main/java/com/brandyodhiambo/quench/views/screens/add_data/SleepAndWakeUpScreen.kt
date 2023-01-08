package com.brandyodhiambo.quench.views.screens.add_data

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandyodhiambo.quench.views.screens.destinations.MainScreenDestination
import com.brandyodhiambo.quench.views.ui.theme.blackColor
import com.brandyodhiambo.quench.views.ui.theme.primaryColor
import com.chargemap.compose.numberpicker.AMPMHours
import com.chargemap.compose.numberpicker.Hours
import com.chargemap.compose.numberpicker.HoursNumberPicker
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SleepAndWakeTimeScreen(
    navigator: DestinationsNavigator
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
                fontSize = 24.sp,
                color = blackColor
            )
            Spacer(modifier = Modifier.height(16.dp))
            TimePickerInHours()

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "What's your Sleeping time?",
                fontSize = 24.sp,
                color = blackColor
            )
            Spacer(modifier = Modifier.height(16.dp))
            TimePickerInHours()

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
                    navigator.navigate(MainScreenDestination)
                },
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = primaryColor)
            ) {
                Text(text = "Next", Modifier.padding(8.dp), color = Color.White)
            }
        }

    }
}

@Composable
fun TimePickerInHours() {
    var pickerValue by remember { mutableStateOf<Hours>(AMPMHours(0, 0, AMPMHours.DayTime.AM)) }
    HoursNumberPicker(
        dividersColor = blackColor,
        value = pickerValue,
        onValueChange = {
            pickerValue = it
            val hours = it.hours
            val minutes = it.minutes
            val amPm = if(hours < 12) "AM" else "PM"
            // Update the hours, minutes, and AM/PM fields here
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
