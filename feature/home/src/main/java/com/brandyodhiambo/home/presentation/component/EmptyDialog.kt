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
fun EmptyDialog(
    onDismiss: () -> Unit,
    onConfirmClick: () -> Unit,
) {
    AlertDialog(
        backgroundColor = Color.White,
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "No Drinks",
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
                "You have no drinks in your list. Add a drink to get started.",
                fontWeight = FontWeight.Normal,
                fontFamily = roboto,
                color = blackColor,
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
            )
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirmClick()
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
