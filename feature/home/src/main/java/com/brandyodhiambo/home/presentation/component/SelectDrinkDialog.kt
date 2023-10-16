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
package com.brandyodhiambo.home.presentation.component

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandyodhiambo.common.R
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime
import java.time.format.DateTimeFormatter

val selectedDrinks = listOf(
    SelectDrink(
        icon = R.drawable.small_cup,
        size = "300ml"
    ),
    SelectDrink(
        icon = R.drawable.small_cup,
        size = "350ml"
    ),
    SelectDrink(
        icon = R.drawable.small_glass,
        size = "400ml"
    ),
    SelectDrink(
        icon = R.drawable.ic_glass,
        size = "450ml"
    ),
    SelectDrink(
        icon = R.drawable.ic_cup,
        size = "500ml"
    ),
    SelectDrink(
        icon = R.drawable.big_cup,
        size = "550ml"
    ),
    SelectDrink(
        icon = R.drawable.kettle,
        size = "600ml"
    ),
    SelectDrink(
        icon = R.drawable.big_cup,
        size = "650ml"
    ),
    SelectDrink(
        icon = R.drawable.kettle,
        size = "700ml"
    ),
    SelectDrink(
        icon = R.drawable.kettle,
        size = "1000ml"
    )
)

@Composable
fun SelectDrinkComposable(
    modifier: Modifier = Modifier,
    openDialog: MutableState<Boolean>,
    onCurrentSelectedDrinkTime: (String) -> Unit,
    onCurrentSelectedDrinkSize: (String) -> Unit,
    onCurrentSelectedDrinkIcon: (Int) -> Unit,
    onClick: () -> Unit
) {
    val openTimeDialog = rememberMaterialDialogState()
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(10.dp, 5.dp, 5.dp, 5.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp)
        ) {
            Text(
                text = "Select Drink",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth(),
                maxLines = 2,
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(12.dp))
            LazyVerticalGrid(columns = GridCells.Fixed(count = 4)) {
                items(selectedDrinks) { selected ->
                    SelectCard(
                        selectDrink = selected,
                        openTimeDialog = openTimeDialog,
                        onCurrentSelectedDrinkTime = onCurrentSelectedDrinkTime,
                        onCurrentSelectedDrinkSize = onCurrentSelectedDrinkSize,
                        onCurrentSelectedDrinkIcon = onCurrentSelectedDrinkIcon
                    )
                }
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(Color.White),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextButton(onClick = {
                    openDialog.value = false
                }) {
                    Text(
                        "Cancel",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
                TextButton(onClick = {
                    openDialog.value = false
                    onClick()
                }) {
                    Text(
                        "Okay",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SelectCard(
    selectDrink: SelectDrink,
    openTimeDialog: MaterialDialogState,
    onCurrentSelectedDrinkSize: (String) -> Unit,
    onCurrentSelectedDrinkIcon: (Int) -> Unit,
    onCurrentSelectedDrinkTime: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            openTimeDialog.show()
            onCurrentSelectedDrinkIcon(selectDrink.icon)
            onCurrentSelectedDrinkSize(selectDrink.size)
        }
    ) {
        Image(
            painter = painterResource(id = selectDrink.icon),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .padding(top = 8.dp)
                .height(20.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = selectDrink.size,
            modifier = Modifier
                .padding(top = 5.dp),
            maxLines = 2,
            fontSize = 14.sp,
            fontWeight = FontWeight.W300,
            overflow = TextOverflow.Ellipsis
        )
        if (openTimeDialog.showing) {
            TimeDialog(
                openDialog = openTimeDialog,
                onCurrentSelectedDrinkTime = onCurrentSelectedDrinkTime
            )
        }
    }
}

@Composable
fun TimeDialog(
    onCurrentSelectedDrinkTime: (String) -> Unit,
    openDialog: MaterialDialogState
) {
    var pickedTime by remember {
        mutableStateOf(LocalTime.MAX)
    }
    MaterialDialog(
        dialogState = openDialog,
        backgroundColor = MaterialTheme.colorScheme.background.copy(alpha = 0.67f),
        buttons = {
            positiveButton(text = "Ok", textStyle = TextStyle(color = MaterialTheme.colorScheme.primary)) {
                onCurrentSelectedDrinkTime(pickedTime.format(DateTimeFormatter.ofPattern("HH:mm a")))
            }
            negativeButton(text = "Cancel", textStyle = TextStyle(color = MaterialTheme.colorScheme.primary))
        }
    ) {
        timepicker(
            initialTime = pickedTime,
            title = "Pick a time",
            is24HourClock = false,
            colors = TimePickerDefaults.colors(
                selectorColor = MaterialTheme.colorScheme.primary,
                selectorTextColor = MaterialTheme.colorScheme.primary,
                headerTextColor = MaterialTheme.colorScheme.background,
                activeBackgroundColor = MaterialTheme.colorScheme.primary,
                activeTextColor = MaterialTheme.colorScheme.background,
                inactiveBackgroundColor = MaterialTheme.colorScheme.background,
                inactiveTextColor = MaterialTheme.colorScheme.onBackground
            )
        ) {
            pickedTime = it
        }
    }
}

data class SelectDrink(
    val size: String,
    val icon: Int
)
