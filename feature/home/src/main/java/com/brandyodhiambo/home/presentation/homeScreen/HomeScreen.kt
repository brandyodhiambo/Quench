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
package com.brandyodhiambo.home.presentation.homeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.brandyodhiambo.common.R
import com.brandyodhiambo.common.domain.model.Days
import com.brandyodhiambo.common.domain.model.GoalWaterIntake
import com.brandyodhiambo.common.domain.model.IdealWaterIntake
import com.brandyodhiambo.common.domain.model.Level
import com.brandyodhiambo.common.domain.model.ReminderTime
import com.brandyodhiambo.common.domain.model.SelectedDrink
import com.brandyodhiambo.common.presentation.component.WaterIntakeDialog
import com.brandyodhiambo.common.util.incrementProgressCircle
import com.brandyodhiambo.designsystem.components.CircularButton
import com.brandyodhiambo.home.presentation.component.CircularRating
import com.brandyodhiambo.home.presentation.component.CongratulationsDialog
import com.brandyodhiambo.home.presentation.component.DeleteDialog
import com.brandyodhiambo.home.presentation.component.EmptyDialog
import com.brandyodhiambo.home.presentation.component.IdealIntakeGoalDialog
import com.brandyodhiambo.home.presentation.component.SelectDrinkComposable
import com.brandyodhiambo.home.presentation.component.TimeSetterDialog
import com.chargemap.compose.numberpicker.Hours
import com.ramcosta.composedestinations.annotation.Destination

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val openTimeDialog = viewModel.openTimeDialog.value
    val openDeleteDialog = viewModel.openDeleteDialog.value
    val openCongratulationsDialog = viewModel.openCongratulationsDialog.value
    val openEmptyStateDialog = viewModel.openEmptyStateDialog.value
    val openGoalDialog = viewModel.openGoalDialog.value
    val idealWaterIntakeDialog = viewModel.idealWaterIntakeDialog.value
    val selectedDrinkDialog = viewModel.selectedDrinkDialog.value

    val selectedDrinksFromDB = viewModel.selectedDrinkFromDB.observeAsState(initial = emptyList())
    val idealWaterIntake = viewModel.idealWaterIntakeFromDb.observeAsState()
    val waterIntake = idealWaterIntake.value?.waterIntake ?: 0
    val waterIntakeForm = idealWaterIntake.value?.form ?: "ml"
    val goalWaterIntakeFromDb = viewModel.goalWaterIntakeFromDb.observeAsState()
    val goalWaterIntake = goalWaterIntakeFromDb.value?.waterIntake ?: 0
    val goalForm = goalWaterIntakeFromDb.value?.form ?: "ml"
    val levelFromDb = viewModel.levelFromDB.observeAsState()

    val amountTaken = levelFromDb.value?.amountTaken ?: 0f
    val waterTaken = levelFromDb.value?.waterTaken ?: 0
    val selectedId = viewModel.selectedId.value

    val reminderTimeFromDb = viewModel.reminderTime.observeAsState()
    val hour = reminderTimeFromDb.value?.hour
    val minute = reminderTimeFromDb.value?.minute

    HomeScreenContent(
        waterIntake = waterIntake,
        waterIntakeForm = waterIntakeForm,
        goalForm = goalForm,
        goalWaterIntake = goalWaterIntake,
        amountTaken = amountTaken,
        hour = hour,
        minute = minute,
        waterTaken = waterTaken,
        selectedDrinksFromDB = selectedDrinksFromDB,
        selectedId = selectedId,
        currentPickerValueText = viewModel.reminderTimePickerValue.value,
        reminderDays = viewModel.reminderDays.value,
        currentWaterIntakeText = viewModel.goalWaterIntakeValue.value,
        currentWaterIntakeFormText = viewModel.goalWaterForm.value,
        currentIdealIntakeText = viewModel.idealWaterIntakeValue.value,
        currentIdealIntakeFormText = viewModel.idealWaterForm.value,
        openEmptyStateDialog = openEmptyStateDialog,
        openDeleteDialog = openDeleteDialog,
        openCongratulationsDialog = openCongratulationsDialog,
        openTimeDialog = openTimeDialog,
        openGoalDialog = openGoalDialog,
        idealWaterIntakeDialog = idealWaterIntakeDialog,
        selectedDrinkDialog = selectedDrinkDialog,
        onGoalDialogChange = {
            viewModel.setOpenGoalDialog(it)
        },
        onIdealWaterIntakeDialogChange = {
            viewModel.setIdealWaterIntakeDialog(it)
        },
        onAddLevelClick = {
            if (selectedDrinksFromDB.value.isEmpty()) {
                viewModel.setOpenEmptyStateDialog(true)
                return@HomeScreenContent
            }
            val (amount, taken) = incrementProgressCircle(
                selectedDrinksFromDB = selectedDrinksFromDB,
                goalWaterIntake = goalWaterIntake,
                amountTakenFromDb = viewModel.levelFromDB.value?.amountTaken ?: 0f,
                waterTakenLevelFromDb = viewModel.levelFromDB.value?.waterTaken ?: 0,
                onSelectedDrinkDeleted = {
                    viewModel.deleteOneSelectedDrink(it)
                }
            )
            viewModel.deleteAllLevels()
            viewModel.insertLevel(
                Level(
                    amountTaken = amount,
                    waterTaken = taken
                )
            )
        },
        onOpenTimeDialog = {
            viewModel.setOpenTimeDialog(it)
        },
        onSelectedDrinkDialog = {
            viewModel.setselectedDrinkDialog(it)
        },
        onDeleteIconClick = { selectedDrink ->
            selectedDrink.id?.let { id ->
                viewModel.setSelectedId(id)
                viewModel.setOpenDeleteDialog(true)
            }
        },
        onConfirmEmptyDialog = {
            viewModel.setOpenEmptyStateDialog(false)
            viewModel.setselectedDrinkDialog(true)
        },
        onDismissEmptyDialog = { viewModel.setOpenEmptyStateDialog(false) },
        onDismissDeleteDialog = { viewModel.setOpenDeleteDialog(false) },
        onConfirmDeleteDialog = { id ->
            viewModel.deleteOneSelectedDrink(id)
            viewModel.setOpenDeleteDialog(false)
        },
        onOpenCongratulationDialog = {
            viewModel.setOpenCongratulationsDialog(it)
        },
        onDismissCongratulationDialog = {
            viewModel.setOpenCongratulationsDialog(false)
        },
        onConfirmCongratulationDialog = {
            viewModel.setOpenCongratulationsDialog(false)
        },
        onDismissTimeDialog = { viewModel.setOpenTimeDialog(false) },
        onCurrentPickerValueChanged = {
            viewModel.reminderTimePickerValue.value = it
        },
        onAllDayClicked = {
            viewModel.onAllDaySelected(
                isAllDay = true
            )
            viewModel.onReminderDays(
                days = listOf(
                    Days("M", viewModel.isAllDaySelected.value),
                    Days("T", viewModel.isAllDaySelected.value),
                    Days("W", viewModel.isAllDaySelected.value),
                    Days("T", viewModel.isAllDaySelected.value),
                    Days("F", viewModel.isAllDaySelected.value),
                    Days("S", viewModel.isAllDaySelected.value),
                    Days("S", viewModel.isAllDaySelected.value)
                )
            )
        },
        onConfirmTimeDialog = {
            viewModel.setOpenTimeDialog(false)
            val ampm: String = if (viewModel.reminderTimePickerValue.value.hours in 0..12) {
                "AM"
            } else {
                "PM"
            }
            viewModel.onReminderTimeSelected(
                hours = viewModel.reminderTimePickerValue.value.hours,
                minutes = viewModel.reminderTimePickerValue.value.minutes,
                amPm = ampm,
                isReapeated = false,
                isAllDay = viewModel.isAllDaySelected.value,
                days = viewModel.reminderDays.value
            )
            if (viewModel.reminderTime.value != null) {
                viewModel.deleteAllRemindTime()
            }
            viewModel.insertRemindTime(
                ReminderTime(
                    hour = viewModel.reminderSelectedTime.value.hour,
                    minute = viewModel.reminderSelectedTime.value.minute,
                    ampm = viewModel.reminderSelectedTime.value.ampm,
                    isRepeated = false,
                    isAllDay = viewModel.isAllDaySelected.value,
                    days = viewModel.reminderDays.value
                )
            )
        },
        onCurrentWaterIntakeTextChange = {
            viewModel.setGoalWaterIntakeValue(it)
        },
        onCurrentWaterIntakeFormTextChange = {
            viewModel.setGoalWaterForm(it)
        },
        onConfirmGoalDialog = {
            val goalWaterIntakeToInsert = GoalWaterIntake(
                waterIntake = viewModel.goalWaterIntakeValue.value.toInt(),
                form = viewModel.goalWaterForm.value
            )
            viewModel.insertGoalWaterIntake(goalWaterIntakeToInsert)
        },
        onCustomDialogChange = {
            viewModel.setOpenGoalDialog(it)
        },
        onDismissGoalDialog = {
            viewModel.setOpenGoalDialog(false)
        },
        onCurrentIdealIntakeTextChange = {
            viewModel.setIdealWaterIntakeValue(it)
        },
        onCurrentIdealIntakeFormTextChange = {
            viewModel.setIdealWaterForm(it)
        },
        onConfirmIdealDialog = {
            val idealWaterIntakeToInsert = IdealWaterIntake(
                waterIntake = viewModel.idealWaterIntakeValue.value.toInt(),
                form = viewModel.idealWaterForm.value
            )
            viewModel.insertIdealWaterIntake(idealWaterIntakeToInsert)
        },
        onIdealCustomDialog = {
            viewModel.setIdealWaterIntakeDialog(it)
        },
        onDismissIdealDialog = {
            viewModel.setIdealWaterIntakeDialog(false)
        },
        onCurrentSelectedDrinkTime = {
            viewModel.setSelectedTime(it)
        },
        onCurrentSelectedDrinkIcon = {
            viewModel.setSelectedIcon(it)
        },
        onCurrentSelectedDrinkSize = {
            viewModel.setSize(it)
        },
        onConfirmSelectedDrinkDialog = {
            viewModel.insertSelectedDrink(
                SelectedDrink(
                    drinkValue = viewModel.size.value,
                    icon = viewModel.selectedIcon.value,
                    time = viewModel.selectedTime.value
                )
            )
        },
        onOpenDialog = {
            viewModel.setselectedDrinkDialog(it)
        },
        onDismissSelectedDrinkDialog = {
            viewModel.setselectedDrinkDialog(false)
        }
    )
}

@Composable
fun HomeScreenContent(
    waterIntake: Int,
    waterIntakeForm: String,
    goalForm: String,
    goalWaterIntake: Int,
    amountTaken: Float,
    hour: Int?,
    minute: Int?,
    waterTaken: Int,
    selectedDrinksFromDB: State<List<SelectedDrink>>,
    selectedId: Int,
    currentPickerValueText: Hours,
    reminderDays: List<Days>,
    currentWaterIntakeText: String,
    currentWaterIntakeFormText: String,
    currentIdealIntakeText: String,
    currentIdealIntakeFormText: String,
    openEmptyStateDialog: Boolean,
    openDeleteDialog: Boolean,
    openCongratulationsDialog: Boolean,
    openTimeDialog: Boolean,
    openGoalDialog: Boolean,
    idealWaterIntakeDialog: Boolean,
    selectedDrinkDialog: Boolean,
    onGoalDialogChange: (Boolean) -> Unit,
    onIdealWaterIntakeDialogChange: (Boolean) -> Unit,
    onOpenTimeDialog: (Boolean) -> Unit,
    onSelectedDrinkDialog: (Boolean) -> Unit,
    onAddLevelClick: () -> Unit,
    onDeleteIconClick: (SelectedDrink) -> Unit,
    onDismissEmptyDialog: () -> Unit,
    onConfirmEmptyDialog: () -> Unit,
    onDismissDeleteDialog: () -> Unit,
    onConfirmDeleteDialog: (Int) -> Unit,
    onOpenCongratulationDialog: (Boolean) -> Unit,
    onDismissCongratulationDialog: () -> Unit,
    onConfirmCongratulationDialog: () -> Unit,
    onAllDayClicked: () -> Unit,
    onDismissTimeDialog: () -> Unit,
    onConfirmTimeDialog: () -> Unit,
    onCurrentPickerValueChanged: (Hours) -> Unit,
    onCurrentWaterIntakeTextChange: (String) -> Unit,
    onCurrentWaterIntakeFormTextChange: (String) -> Unit,
    onDismissGoalDialog: () -> Unit,
    onConfirmGoalDialog: () -> Unit,
    onCustomDialogChange: (Boolean) -> Unit,
    onCurrentIdealIntakeTextChange: (String) -> Unit,
    onCurrentIdealIntakeFormTextChange: (String) -> Unit,
    onIdealCustomDialog: (Boolean) -> Unit,
    onDismissIdealDialog: () -> Unit,
    onConfirmIdealDialog: () -> Unit,
    onCurrentSelectedDrinkTime: (String) -> Unit,
    onCurrentSelectedDrinkIcon: (Int) -> Unit,
    onCurrentSelectedDrinkSize: (String) -> Unit,
    onOpenDialog: (Boolean) -> Unit,
    onDismissSelectedDrinkDialog: () -> Unit,
    onConfirmSelectedDrinkDialog: () -> Unit,


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
                    WaterIntake(
                        waterIntake = waterIntake,
                        form = waterIntakeForm,
                        goalForm = goalForm,
                        goalWaterIntake = goalWaterIntake,
                        onGoalDialogChange = onGoalDialogChange,
                        onIdealWaterIntakeDialogChange = onIdealWaterIntakeDialogChange
                    )
                }
                item {
                    WaterRecord(
                        amountTaken = amountTaken,
                        time = if (hour != null && minute != null) "$hour:$minute" else "Add Time",
                        waterTaken = waterTaken,
                        goalWaterIntake = goalWaterIntake,
                        onAddLevelClick = onAddLevelClick,
                        onOpenTimeDialog = onOpenTimeDialog,
                        onSelectedDrinkDialog = onSelectedDrinkDialog
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(selectedDrinksFromDB.value) {
                    WaterIntakeTimeAndLevel(
                        intake = it,
                        onDeleteIconClick = onDeleteIconClick
                    )
                }
            }

            if (openEmptyStateDialog) {
                Dialog(onDismissRequest = onDismissEmptyDialog) {
                    EmptyDialog(
                        onConfirmClick = onConfirmEmptyDialog,
                        onDismiss = onDismissEmptyDialog
                    )
                }
            }

            if (openDeleteDialog) {
                Dialog(onDismissRequest = onDismissDeleteDialog) {
                    DeleteDialog(
                        id = selectedId,
                        onDismiss = onDismissDeleteDialog,
                        onConfirmClick = onConfirmDeleteDialog
                    )
                }
            }
            if ((waterTaken >= goalWaterIntake) && (goalWaterIntake != 0)) {
                onOpenCongratulationDialog(true)
            }

            if (openCongratulationsDialog) {
                Dialog(onDismissRequest = onDismissCongratulationDialog) {
                    CongratulationsDialog(
                        onCancelClicked = onDismissCongratulationDialog,
                        onOkayClicked = onConfirmCongratulationDialog
                    )
                }
            }

            if (openTimeDialog) {
                Dialog(onDismissRequest = onDismissTimeDialog) {
                    TimeSetterDialog(
                        currentPickerValueText = currentPickerValueText,
                        reminderDays = reminderDays,
                        onDismiss = onDismissTimeDialog,
                        onCurrentPickerValueChanged = onCurrentPickerValueChanged,
                        onAllDayClicked = onAllDayClicked,
                        onConfirmClick = onConfirmTimeDialog
                    )
                }
            }

            if (openGoalDialog) {
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

            if (idealWaterIntakeDialog) {
                Dialog(onDismissRequest = onDismissIdealDialog) {
                    IdealIntakeGoalDialog(
                        currentIdealIntakeText = currentIdealIntakeText,
                        currentIdealIntakeFormText = currentIdealIntakeFormText,
                        onCurrentIdealIntakeTextChange = onCurrentIdealIntakeTextChange,
                        onCurrentIdealIntakeFormTextChange = onCurrentIdealIntakeFormTextChange,
                        onOkayClick = onConfirmIdealDialog,
                        onIdealCustomDialog = onIdealCustomDialog
                    )
                }
            }

            if (selectedDrinkDialog) {
                Dialog(onDismissRequest = onDismissSelectedDrinkDialog) {
                    SelectDrinkComposable(
                        onCurrentSelectedDrinkTime = onCurrentSelectedDrinkTime,
                        onCurrentSelectedDrinkIcon = onCurrentSelectedDrinkIcon,
                        onCurrentSelectedDrinkSize = onCurrentSelectedDrinkSize,
                        onClick = onConfirmSelectedDrinkDialog,
                        onOpenDialog = onOpenDialog
                    )
                }
            }
        }
    }
}

@Composable
fun WaterIntake(
    waterIntake: Int,
    form: String,
    goalForm: String,
    goalWaterIntake: Int,
    onGoalDialogChange: (Boolean) -> Unit,
    onIdealWaterIntakeDialogChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_glass),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        onIdealWaterIntakeDialogChange(true)
                    }
                ) {
                    Text(
                        text = "Ideal water intake",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "$waterIntake $form",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                        style = MaterialTheme.typography.labelMedium

                    )
                }
            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primary
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.ic_cup), contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        onGoalDialogChange(true)
                    }
                ) {
                    Text(
                        text = "Water intake goal",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = "$goalWaterIntake $goalForm",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Composable
fun WaterRecord(
    amountTaken: Float,
    waterTaken: Int,
    time: String,
    goalWaterIntake: Int,
    onSelectedDrinkDialog: (Boolean) -> Unit,
    onAddLevelClick: () -> Unit,
    onOpenTimeDialog: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(start = 16.dp, end = 16.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            CircularRating(
                percentage = amountTaken,
                drunk = waterTaken,
                goal = goalWaterIntake
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CircularButton(
                    backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.67f),
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    icon = R.drawable.ic_clock,
                    title = time,
                    onClick = {
                        onOpenTimeDialog(true)
                    }
                )
                CircularButton(
                    backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.67f),
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    icon = R.drawable.ic_add,
                    title = "Add Level",
                    onClick = {
                        onAddLevelClick()
                    }
                )
                CircularButton(
                    backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.67f),
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    icon = R.drawable.ic_glass,
                    title = "Add Drink",
                    onClick = {
                        onSelectedDrinkDialog(true)
                    }
                )
            }
        }
    }
}

@Composable
fun WaterIntakeTimeAndLevel(
    intake: SelectedDrink,
    onDeleteIconClick: (SelectedDrink) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(MaterialTheme.shapes.medium)
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.ic_glass),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )
                Text(
                    text = intake.drinkValue,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = intake.time,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    style = MaterialTheme.typography.labelMedium
                )
                IconButton(onClick = { onDeleteIconClick(intake) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        tint = Color.Gray,
                        contentDescription = null
                    )
                }
            }
        }
        Divider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
        )
    }
}
