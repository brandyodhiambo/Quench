package com.brandyodhiambo.settings.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.brandyodhiambo.common.R
import com.brandyodhiambo.designsystem.theme.blackColor
import com.brandyodhiambo.designsystem.theme.primaryColor
import com.brandyodhiambo.settings.presentation.component.CustomReminderDialog
import com.ramcosta.composedestinations.annotation.Destination

interface SettingsNavigator {
    fun navigateToNotificationScreen()
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun SettingScreen(
    navigator: SettingsNavigator
) {
    val openIntakeDialog = remember { mutableStateOf(false) }
    val openGenderDialog = remember { mutableStateOf(false) }
    val openTimeDialog = remember { mutableStateOf(false) }
    val openWaterUnitDialog = remember { mutableStateOf(false) }
    val openWeightUnitDialog = remember { mutableStateOf(false) }

    Scaffold(
        backgroundColor = primaryColor
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn {
                item {
                    UnitsWaterIntake(
                        openTimeFormatDialog = openTimeDialog,
                        openWaterUnitDialog = openWaterUnitDialog,
                        openWeightUnitDialog = openWeightUnitDialog
                    )
                }
                item {
                    Goals(
                        openDialog = openIntakeDialog,
                        openGenderDialog = openGenderDialog
                    )
                }
                item {
                    ReminderWaterIntake(navigator = navigator)
                }
            }

            if (openIntakeDialog.value) {
                Dialog(onDismissRequest = { openIntakeDialog.value }) {
                    //WaterIntakeDialog(openCustomDialog = openIntakeDialog)
                }
            }

            if (openGenderDialog.value) {
                Dialog(onDismissRequest = { openGenderDialog.value }) {
                    val gender = listOf("Male","Female","Other")
                    CustomReminderDialog(openDialog = openGenderDialog, items = gender, title = "Gender")
                }
            }
            if (openTimeDialog.value) {
                Dialog(onDismissRequest = { openTimeDialog.value }) {
                    val time = listOf("12 Hour","24 Hour")
                    CustomReminderDialog(openDialog = openTimeDialog, items = time, title = "Time Format")
                }
            }

            if (openWaterUnitDialog.value) {
                Dialog(onDismissRequest = { openWaterUnitDialog.value }) {
                    val waterUnit = listOf("ml","oz","cup","pint","gallon","liter")
                    CustomReminderDialog(openDialog = openWaterUnitDialog, items = waterUnit, title = "Water Unit")
                }
            }

            if (openWeightUnitDialog.value) {
                Dialog(onDismissRequest = { openWeightUnitDialog.value }) {
                    val weightUnit = listOf("kg","lb","oz")
                    CustomReminderDialog(openDialog = openWeightUnitDialog, items = weightUnit, title = "Weight Unit")
                }
            }
        }
    }
}

@Composable
fun UnitsWaterIntake(
    openTimeFormatDialog: MutableState<Boolean>,
    openWaterUnitDialog: MutableState<Boolean>,
    openWeightUnitDialog: MutableState<Boolean>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Water Unit",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = blackColor
                )

                TextButton(onClick = { openWaterUnitDialog.value = true }) {
                    Text(
                        text = "ml",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300,
                        color = primaryColor
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .padding(start = 8.dp, end = 8.dp),
                color = Color.Gray,
                thickness = 1.dp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Weight Unit",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = blackColor
                )

                TextButton(onClick = { openWeightUnitDialog.value = true }) {
                    Text(
                        text = "Kg",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300,
                        color = primaryColor
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .padding(start = 8.dp, end = 8.dp),
                color = Color.Gray,
                thickness = 1.dp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Time Format",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = blackColor
                )
                TextButton(onClick = { openTimeFormatDialog.value = true }) {
                    Text(
                        text = "12 hours",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300,
                        color = primaryColor
                    )
                }
            }
        }
    }
}

@Composable
fun ReminderWaterIntake(navigator: SettingsNavigator) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Reminder Mode",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = blackColor
                    )
                }
                IconButton(onClick = {
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_chevron_right),
                        contentDescription = null
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .padding(start = 8.dp, end = 8.dp),
                color = Color.Gray,
                thickness = 1.dp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Reminder Sound",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = blackColor
                    )
                }
                IconButton(onClick = {
                    val intent = Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER)
                    context.startActivity(intent)
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_chevron_right),
                        contentDescription = null
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .padding(start = 8.dp, end = 8.dp),
                color = Color.Gray,
                thickness = 1.dp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Notifications",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = blackColor
                    )
                }
                IconButton(onClick = {
                    navigator.navigateToNotificationScreen()
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_chevron_right),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun Goals(
    openDialog: MutableState<Boolean>,
    openGenderDialog: MutableState<Boolean>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Gender",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = blackColor
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.clickable {
                        openGenderDialog.value = true
                    }
                ) {
                    Text(
                        text = "Male",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300,
                        color = primaryColor
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_chevron_right),
                        contentDescription = null
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .padding(start = 8.dp, end = 8.dp),
                color = Color.Gray,
                thickness = 1.dp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Intake Goal",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = blackColor
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.clickable {
                        openDialog.value = true
                    }
                ) {
                    Text(
                        text = "2400ml",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300,
                        color = primaryColor
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_chevron_right),
                        contentDescription = null
                    )
                }
            }
        }
    }
}
