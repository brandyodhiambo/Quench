package com.brandyodhiambo.home.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandyodhiambo.designsystem.theme.blackColor
import com.brandyodhiambo.designsystem.theme.roboto

@Composable
fun DeleteDialog(
    id: Int,
    onDismiss: () -> Unit,
    onConfirmClick: (Int) -> Unit,
) {
    AlertDialog(
        backgroundColor = Color.White,
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Delete Drink",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontFamily = roboto,
            )
        },
        text = {
            Divider(
                color = blackColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 5.dp),
            )
            Text(
                "Are you sure you want to delete this drink?",
                fontWeight = FontWeight.Bold,
                fontFamily = roboto,
                color = blackColor,
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
            )
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirmClick(id)
            }) {
                Text(
                    "Confirm",
                    fontWeight = FontWeight.Bold,
                    fontFamily = roboto,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                Text(
                    "Cancel",
                    fontWeight = FontWeight.Bold,
                    fontFamily = roboto,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                )
            }
        },
    )
}
