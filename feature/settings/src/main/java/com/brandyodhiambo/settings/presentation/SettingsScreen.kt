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
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.brandyodhiambo.common.R
import com.brandyodhiambo.common.domain.model.GoalWaterIntake
import com.brandyodhiambo.common.domain.model.TimeFormate
import com.brandyodhiambo.common.presentation.component.WaterIntakeDialog
import com.brandyodhiambo.home.presentation.homeScreen.HomeViewModel
import com.brandyodhiambo.settings.presentation.component.TimeFormateDialog
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
    settingViewModel: SettingsViewModel = hiltViewModel()
) {
    val openIntakeDialog = homeViewModel.openGoalDialog.value
    val openTimeDialog = settingViewModel.openTimeDialog.value


    SettingScreenContent(
        openIntakeDialog = openIntakeDialog,
        openTimeDialog = openTimeDialog,
        currentWaterIntakeText = homeViewModel.goalWaterIntakeValue.value,
        currentWaterIntakeFormText = homeViewModel.goalWaterForm.value,
        currentIntake = homeViewModel.goalWaterIntakeValue.value,
        currentForm = homeViewModel.goalWaterForm.value,
        selectedValue = settingViewModel.selectedTimeFormate.value,
        onRadioButtonClicked = {
            val insertTimeFormate = TimeFormate(
                formate = it.toInt()
            )
            settingViewModel.insertTimeFormate(insertTimeFormate)
        } ,
        onChangeState = {
            settingViewModel.onTimeFormatSelected(it)
        },
        onCurrentWaterIntakeTextChange = {
            homeViewModel.setGoalWaterIntakeValue(it)
        },
        onCurrentWaterIntakeFormTextChange = {
            homeViewModel.setGoalWaterForm(it)
        },
        onConfirmGoalDialog = {
            val goalWaterIntakeToInsert = GoalWaterIntake(
                waterIntake = homeViewModel.goalWaterIntakeValue.value.toInt(),
                form = homeViewModel.goalWaterForm.value
            )
            homeViewModel.insertGoalWaterIntake(goalWaterIntakeToInsert)
        },
        onCustomDialogChange = {
            homeViewModel.setOpenGoalDialog(it)
        },
        onDismissGoalDialog = {
            homeViewModel.setOpenGoalDialog(false)
        },
        onGoalDialog = {
            homeViewModel.setOpenGoalDialog(it)
        },
        onOpenTimeFormatDialog = {
            settingViewModel.setOpenTimeDialog(it)
        },
        onNavigate = {
            navigator.navigateToNotificationScreen()
        },
        onDismissTimeDialog = {
            settingViewModel.setOpenTimeDialog(false)
        },
    )


}


@Composable
fun SettingScreenContent(
    modifier: Modifier = Modifier,
    openIntakeDialog: Boolean,
    openTimeDialog: Boolean,
    currentIntake: String,
    currentForm: String,
    currentWaterIntakeText: String,
    currentWaterIntakeFormText: String,
    onCurrentWaterIntakeTextChange: (String) -> Unit,
    onCurrentWaterIntakeFormTextChange: (String) -> Unit,
    onDismissGoalDialog: () -> Unit,
    onConfirmGoalDialog: () -> Unit,
    onCustomDialogChange: (Boolean) -> Unit,
    onGoalDialog: (Boolean) -> Unit,
    onOpenTimeFormatDialog: (Boolean) -> Unit,
    onNavigate: () -> Unit,
    onDismissTimeDialog:()->Unit,
    selectedValue: String,
    onChangeState: (String) -> Unit,
    onRadioButtonClicked: (String) -> Unit,

    ) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn {
                item {
                    UnitsWaterIntake(
                        timeFormate = selectedValue,
                        onOpenTimeFormatDialog = onOpenTimeFormatDialog,
                    )
                }
                item {
                    Goals(
                        currentIntake = currentIntake,
                        currentForm = currentForm,
                        onGoalDialog = onGoalDialog
                    )
                }
                item {
                    ReminderWaterIntake(
                        onNavigate = onNavigate
                    )
                }
            }

            if (openIntakeDialog) {
                Dialog(onDismissRequest = onDismissGoalDialog) {
                    WaterIntakeDialog(
                        currentWaterIntakeText = currentWaterIntakeText,
                        currentWaterIntakeFormText = currentWaterIntakeFormText,
                        onCurrentWaterIntakeTextChange = onCurrentWaterIntakeTextChange,
                        onCurrentWaterIntakeFormTextChange = onCurrentWaterIntakeFormTextChange,
                        onOkayClick = onConfirmGoalDialog,
                        onCustomDialogChange = onCustomDialogChange
                    )
                }
            }
            if (openTimeDialog) {
                Dialog(onDismissRequest = { onDismissTimeDialog() }) {
                    val time = listOf("12", "24")
                    TimeFormateDialog(
                        items = time,
                        title = "Time Format",
                        selectedValue = selectedValue,
                        onChangeState = onChangeState,
                        onRadioButtonClicked = onRadioButtonClicked,
                        onCustomReminderDialog = onDismissTimeDialog
                    )
                }
            }
        }
    }
}

@Composable
fun UnitsWaterIntake(
    timeFormate: String,
    onOpenTimeFormatDialog: (Boolean) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
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
                    text = "Time Format",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                TextButton(onClick = { onOpenTimeFormatDialog(true) }) {
                    Text(
                        text = "$timeFormate hours",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
fun ReminderWaterIntake(onNavigate:()->Unit) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
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
                        text = "Reminder Sound",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground
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
                                    uris[which]
                                )
                            }
                            builder.show()
                        } else {
                            Toast.makeText(
                                context,
                                "Allow modify system settings ==> ON ",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(context, "unable to set as Ringtone ", Toast.LENGTH_SHORT)
                            .show()
                    }
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
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                IconButton(onClick = {
                    onNavigate()
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
    currentIntake: String,
    currentForm: String,
    onGoalDialog: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
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
                        text = "Intake Goal",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.clickable {
                        onGoalDialog(true)
                    }
                ) {
                    Text(
                        text = "$currentIntake $currentForm",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
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

private fun checkSystemWritePermission(context: Context): Boolean {
    if (Settings.System.canWrite(context)) return true else openAndroidPermissionsMenu(context)
    return false
}

private fun openAndroidPermissionsMenu(context: Context) {
    val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
    intent.setData(Uri.parse("package:" + context.getPackageName()))
    context.startActivity(intent)
}
