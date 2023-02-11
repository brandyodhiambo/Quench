package com.brandyodhiambo.quench.views.screens.notification

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.brandyodhiambo.quench.R
import com.brandyodhiambo.quench.views.screens.dialogs.RepeatModeDialog
import com.brandyodhiambo.quench.views.screens.dialogs.WeighUnitDialog
import com.brandyodhiambo.quench.views.ui.theme.blackColor
import com.chargemap.compose.numberpicker.AMPMHours
import com.chargemap.compose.numberpicker.Hours
import com.chargemap.compose.numberpicker.HoursNumberPicker
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun AddReminderScreen(
    navigator: DestinationsNavigator
) {
    val repeateModeDialog = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBarAddReminder(navigator = navigator)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ReminderTimePickerInHours()
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Reminder Sound",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = blackColor
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Default Sound",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = blackColor
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chevron_right),
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
                    text = "Reminder Mode",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = blackColor
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Mon to Fri",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = blackColor
                    )
                    IconButton(onClick = {repeateModeDialog.value = true}) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chevron_right),
                            contentDescription = null
                        )
                    }
                }

            }
            if (repeateModeDialog.value) {
                Dialog(onDismissRequest = { repeateModeDialog.value }) {
                    RepeatModeDialog(openDialog = repeateModeDialog)
                }
            }
        }
    }

}

@Composable
fun TopAppBarAddReminder(navigator: DestinationsNavigator) {
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
        backgroundColor = Color.White
    )
}

@Composable
fun ReminderTimePickerInHours() {
    var pickerValue by remember { mutableStateOf<Hours>(AMPMHours(0, 0, AMPMHours.DayTime.AM)) }
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