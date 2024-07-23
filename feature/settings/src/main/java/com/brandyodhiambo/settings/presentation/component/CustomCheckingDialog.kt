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
package com.brandyodhiambo.settings.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomCheckinDialog(
    onCustomCheckDialog:(customDays:List<String>)->Unit,
    title: String,
    items: List<String>
) {
    val selectedValues = remember { mutableStateListOf<String>() }
    val isSelectedItem: (String) -> Boolean = { selectedValues.contains(it) }
    val onChangeState: (String, Boolean) -> Unit = { item, isChecked ->
        if (isChecked) {
            selectedValues.add(item)
        } else {
            selectedValues.remove(item)
        }
    }

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp)
    ) {
        Column(
            Modifier
                .padding(8.dp)
                .background(Color.White)
        ) {
            Text(
                text = "Select $title",
                modifier = Modifier.padding(8.dp)
            )
            items.forEach { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Checkbox(
                        checked = isSelectedItem(item),
                        onCheckedChange = { onChangeState(item, it) }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item,
                        modifier = Modifier.fillMaxWidth()
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
                Card(
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(10.dp)
                        .clickable {
                            onCustomCheckDialog(emptyList())
                        },
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
                ) {
                    Text(
                        "Cancel",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(10.dp),
                        textAlign = TextAlign.Center
                    )
                }
                Card(
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            onCustomCheckDialog(selectedValues.toList())
                        },
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        "Okay",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(10.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
