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
package com.brandyodhiambo.common.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.brandyodhiambo.common.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WaterIntakeDialog(
    modifier: Modifier = Modifier,
    openCustomDialog: MutableState<Boolean>,
    currentWaterIntakeText: String,
    currentWaterIntakeFormText: String,
    onCurrentWaterIntakeTextChange: (String) -> Unit,
    onCurrentWaterIntakeFormTextChange: (String) -> Unit,
    onOkayClick: () -> Unit,

) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_cup),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(
                    color = MaterialTheme.colorScheme.secondary,
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 35.dp)
                    .height(70.dp)
                    .fillMaxWidth(),
            )
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Text(
                    text = "Water intake goal",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    maxLines = 2,
                    style = MaterialTheme.typography.labelLarge,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(start = 8.dp, end = 8.dp),
                        value = currentWaterIntakeText,
                        onValueChange = onCurrentWaterIntakeTextChange,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                        ),
                        label = {
                            Text(
                                "2810",
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                        },
                        shape = RoundedCornerShape(30.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground,
                        ),
                    )

                    val options = listOf("ml", "l")
                    var expanded by remember { mutableStateOf(false) }

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        },
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            readOnly = true,
                            value = currentWaterIntakeFormText,
                            onValueChange = {
                                onCurrentWaterIntakeFormTextChange(it)
                            },
                            label = { Text("ml") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expanded,
                                )
                            },
                            shape = RoundedCornerShape(45.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onBackground,
                            ),
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = {
                                expanded = false
                            },
                        ) {
                            options.forEach { selectionOption ->
                                DropdownMenuItem(
                                    onClick = {
                                        onCurrentWaterIntakeFormTextChange(selectionOption)
                                        expanded = false
                                    },
                                ) {
                                    Text(text = selectionOption)
                                }
                            }
                        }
                    }
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(Color.White),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                TextButton(onClick = {
                    openCustomDialog.value = false
                }) {
                    Text(
                        "Cancel",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                    )
                }
                TextButton(onClick = {
                    openCustomDialog.value = false
                    onOkayClick()
                }) {
                    Text(
                        "Okay",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                    )
                }
            }
        }
    }
}
