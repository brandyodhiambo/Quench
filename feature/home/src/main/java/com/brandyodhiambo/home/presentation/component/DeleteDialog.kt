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

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandyodhiambo.designsystem.theme.roboto

@Composable
fun DeleteDialog(
    id: Int,
    onDismiss: () -> Unit,
    onConfirmClick: (Int) -> Unit
) {
    AlertDialog(
        backgroundColor = Color.White,
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Delete Drink",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontFamily = roboto
            )
        },
        text = {
            Divider(
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 5.dp)
            )
            Text(
                "Are you sure you want to delete this drink?",
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium
            )
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirmClick(id)
            }) {
                Text(
                    "Confirm",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                Text(
                    "Cancel",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                )
            }
        }
    )
}
