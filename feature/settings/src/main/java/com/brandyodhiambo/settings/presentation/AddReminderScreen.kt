package com.brandyodhiambo.settings.presentation

import android.annotation.SuppressLint
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.brandyodhiambo.common.R
import com.brandyodhiambo.designsystem.components.NotificationSwitcher
import com.brandyodhiambo.designsystem.theme.blackColor
import com.brandyodhiambo.settings.presentation.component.CustomReminderDialog
import com.chargemap.compose.numberpicker.AMPMHours
import com.chargemap.compose.numberpicker.Hours
import com.chargemap.compose.numberpicker.HoursNumberPicker
import com.ramcosta.composedestinations.annotation.Destination


interface AddReminderNavigator{
    fun navigateUp()
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun AddReminderScreen(
    navigator: AddReminderNavigator
) {
    val repeateModeDialog = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBarAddReminder(navigator = navigator)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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
                    IconButton(onClick = { repeateModeDialog.value = true }) {
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
                    text = "Vibrate when alarm sounds",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = blackColor
                )
                NotificationSwitcher(
                    isOn = true,
                    size = 35.dp,
                    padding = 5.dp,
                    onToggle = {
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
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = blackColor
                )
                NotificationSwitcher(
                    isOn = false,
                    size = 35.dp,
                    padding = 5.dp,
                    onToggle = {
                    }
                )
            }



            if (repeateModeDialog.value) {
                Dialog(onDismissRequest = { repeateModeDialog.value }) {
                    val repeatMode = listOf("Once", "Mon to Fri", "Daily", "Custom")
                    CustomReminderDialog(openDialog = repeateModeDialog, items = repeatMode, title = "Repeat Mode")
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
        backgroundColor = Color.White
    )
}

@Composable
fun ReminderTimePickerInHours() {
    Column(
        modifier = Modifier.fillMaxWidth().height(200.dp).padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
}
