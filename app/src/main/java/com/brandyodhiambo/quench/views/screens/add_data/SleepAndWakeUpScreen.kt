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
import com.brandyodhiambo.quench.views.ui.theme.blackColor
import com.brandyodhiambo.quench.views.ui.theme.primaryColor
import com.chargemap.compose.numberpicker.AMPMHours
import com.chargemap.compose.numberpicker.Hours
import com.chargemap.compose.numberpicker.HoursNumberPicker
import com.ramcosta.composedestinations.annotation.Destination


@Destination
@Composable
fun SleepAndWakeTimeScreen() {
   Column(
       modifier = Modifier
           .fillMaxSize()
           .padding(8.dp),
       horizontalAlignment = Alignment.CenterHorizontally
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
       Button(
           modifier = Modifier
               .align(Alignment.End)
               .fillMaxWidth(),
           onClick = { /*TODO*/ },
           shape = RoundedCornerShape(15.dp),
           colors = ButtonDefaults.buttonColors(backgroundColor = primaryColor )
       ) {
           Text(text = "Next",Modifier.padding(4.dp), color = Color.White)
       }
   }
}

@Composable
fun TimePickerInHours() {
    var pickerValue by remember { mutableStateOf<Hours>(AMPMHours(9, 12, AMPMHours.DayTime.PM )) }
    HoursNumberPicker(
        dividersColor = blackColor,
        value = pickerValue,
        onValueChange = {
            pickerValue = it
        },
        hoursDivider = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                text = "hours"
            )
        },
        minutesDivider = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                text = "minutes"
            )
        }
    )
}