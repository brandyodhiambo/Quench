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
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.* // ktlint-disable no-wildcard-imports
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.brandyodhiambo.common.R
import com.brandyodhiambo.common.domain.model.GoalWaterIntake
import com.brandyodhiambo.common.presentation.component.WaterIntakeDialog
import com.brandyodhiambo.designsystem.theme.blackColor
import com.brandyodhiambo.designsystem.theme.primaryColor
import com.brandyodhiambo.home.presentation.homeScreen.HomeViewModel
import com.brandyodhiambo.settings.presentation.component.CustomReminderDialog
import com.ramcosta.composedestinations.annotation.Destination

interface SettingsNavigator {
    fun navigateToNotificationScreen()
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun SettingScreen(
    navigator: SettingsNavigator,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val openIntakeDialog = remember { mutableStateOf(false) }
    val openTimeDialog = remember { mutableStateOf(false) }
    val openWaterUnitDialog = remember { mutableStateOf(false) }
    val openWeightUnitDialog = remember { mutableStateOf(false) }

    val context = LocalContext.current

    Scaffold(
        backgroundColor = primaryColor,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            LazyColumn {
                item {
                    UnitsWaterIntake(
                        openTimeFormatDialog = openTimeDialog,
                        openWaterUnitDialog = openWaterUnitDialog,
                        openWeightUnitDialog = openWeightUnitDialog,
                    )
                }
                item {
                    Goals(
                        openDialog = openIntakeDialog,
                        currentIntake = homeViewModel.goalWaterIntakeValue.value,
                        currentForm = homeViewModel.goalWaterForm.value,
                    )
                }
                item {
                    ReminderWaterIntake(navigator = navigator)
                }
            }

            if (openIntakeDialog.value) {
                Dialog(onDismissRequest = { openIntakeDialog.value }) {
                    WaterIntakeDialog(
                        openCustomDialog = openIntakeDialog,
                        currentWaterIntakeText = homeViewModel.goalWaterIntakeValue.value,
                        currentWaterIntakeFormText = homeViewModel.goalWaterForm.value,
                        onCurrentWaterIntakeTextChange = {
                            homeViewModel.setGoalWaterIntakeValue(it)
                        },
                        onCurrentWaterIntakeFormTextChange = {
                            homeViewModel.setGoalWaterForm(it)
                        },
                        onOkayClick = {
                            val goalWaterIntakeToInsert = GoalWaterIntake(
                                waterIntake = homeViewModel.goalWaterIntakeValue.value.toInt(),
                                form = homeViewModel.goalWaterForm.value,
                            )
                            homeViewModel.insertGoalWaterIntake(goalWaterIntakeToInsert)
                        },
                    )
                }
            }
            if (openTimeDialog.value) {
                Dialog(onDismissRequest = { openTimeDialog.value }) {
                    val time = listOf("12 Hour", "24 Hour")
                    CustomReminderDialog(
                        openDialog = openTimeDialog,
                        items = time,
                        title = "Time Format",
                    )
                }
            }

            if (openWaterUnitDialog.value) {
                Dialog(onDismissRequest = { openWaterUnitDialog.value }) {
                    val waterUnit = listOf("ml", "oz", "cup", "pint", "gallon", "liter")
                    CustomReminderDialog(
                        openDialog = openWaterUnitDialog,
                        items = waterUnit,
                        title = "Water Unit",
                    )
                }
            }

            if (openWeightUnitDialog.value) {
                Dialog(onDismissRequest = { openWeightUnitDialog.value }) {
                    val weightUnit = listOf("kg", "lb", "oz")
                    CustomReminderDialog(
                        openDialog = openWeightUnitDialog,
                        items = weightUnit,
                        title = "Weight Unit",
                    )
                }
            }
        }
    }
}

@Composable
fun UnitsWaterIntake(
    openTimeFormatDialog: MutableState<Boolean>,
    openWaterUnitDialog: MutableState<Boolean>,
    openWeightUnitDialog: MutableState<Boolean>,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp),
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Water Unit",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = blackColor,
                )

                TextButton(onClick = { openWaterUnitDialog.value = true }) {
                    Text(
                        text = "ml",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300,
                        color = primaryColor,
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .padding(start = 8.dp, end = 8.dp),
                color = Color.Gray,
                thickness = 1.dp,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Weight Unit",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = blackColor,
                )

                TextButton(onClick = { openWeightUnitDialog.value = true }) {
                    Text(
                        text = "Kg",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300,
                        color = primaryColor,
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .padding(start = 8.dp, end = 8.dp),
                color = Color.Gray,
                thickness = 1.dp,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Time Format",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = blackColor,
                )
                TextButton(onClick = { openTimeFormatDialog.value = true }) {
                    Text(
                        text = "12 hours",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300,
                        color = primaryColor,
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
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Reminder Sound",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = blackColor,
                    )
                }
                IconButton(onClick = {
                    try {
                        if (checkSystemWritePermission(context = context)) {
                            val ringtoneManager = RingtoneManager(context)
                            ringtoneManager.setType(RingtoneManager.TYPE_NOTIFICATION)
                            val cursor = ringtoneManager.cursor
                            val titles = Array<String?>(cursor.count) { null }
                            val uris = Array<Uri?>(cursor.count) { null }
                            cursor.moveToFirst()
                            for (i in 0 until cursor.count) {
                                titles[i] = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX)
                                uris[i] = ringtoneManager.getRingtoneUri(i)
                                cursor.moveToNext()
                            }

                            val builder = android.app.AlertDialog.Builder(context)
                            builder.setTitle("Select a notification tone")
                            builder.setItems(titles) { _, which ->

                                val mediaPlayer = MediaPlayer.create(context, uris[which])
                                mediaPlayer.start()

                                RingtoneManager.setActualDefaultRingtoneUri(
                                    context,
                                    RingtoneManager.TYPE_NOTIFICATION,
                                    uris[which],
                                )
                            }
                            builder.show()
                        } else {
                            Toast.makeText(
                                context,
                                "Allow modify system settings ==> ON ",
                                Toast.LENGTH_LONG,
                            ).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(context, "unable to set as Ringtone ", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_chevron_right),
                        contentDescription = null,
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .padding(start = 8.dp, end = 8.dp),
                color = Color.Gray,
                thickness = 1.dp,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Notifications",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = blackColor,
                    )
                }
                IconButton(onClick = {
                    navigator.navigateToNotificationScreen()
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_chevron_right),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

@Composable
fun Goals(
    openDialog: MutableState<Boolean>,
    currentIntake: String,
    currentForm: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp),
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Intake Goal",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = blackColor,
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.clickable {
                        openDialog.value = true
                    },
                ) {
                    Text(
                        text = "$currentIntake $currentForm",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300,
                        color = primaryColor,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_chevron_right),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

private fun checkSystemWritePermission(context: Context): Boolean {
    if (Settings.System.canWrite(context)) return true else openAndroidPermissionsMenu(context)
    return false
}

private fun openAndroidPermissionsMenu(context: Context) {
    val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
    intent.setData(Uri.parse("package:" + context.getPackageName()))
    context.startActivity(intent)
}
