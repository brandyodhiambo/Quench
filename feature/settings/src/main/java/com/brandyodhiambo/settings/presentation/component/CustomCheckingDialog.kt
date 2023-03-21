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
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.brandyodhiambo.designsystem.theme.primaryColor
import com.brandyodhiambo.designsystem.theme.secondaryWhite

@Composable
fun CustomCheckinDialog(
    openDialog: MutableState<Boolean>,
    title: String,
    items: List<String>,
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
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
        elevation = 8.dp
    ) {
        Column(Modifier
            .padding(8.dp)
            .background(Color.White)) {
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
                    elevation = 10.dp,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(10.dp)
                        .clickable {
                            openDialog.value = false
                        },
                    backgroundColor = Color.LightGray
                ) {
                    Text(
                        "Cancel",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(10.dp),
                        textAlign = TextAlign.Center
                    )
                }
                Card(
                    shape = RoundedCornerShape(20.dp),
                    elevation = 10.dp,
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            openDialog.value = false
                        },
                    backgroundColor = primaryColor
                ) {
                    Text(
                        "Okay",
                        fontWeight = FontWeight.Bold,
                        color = secondaryWhite,
                        modifier = Modifier.padding(10.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
    }
}
