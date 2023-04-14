package com.brandyodhiambo.home.presentation.component

import android.app.TimePickerDialog
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandyodhiambo.common.R
import com.brandyodhiambo.designsystem.theme.primaryColor
import java.util.Calendar

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
    onClick: () -> Unit,
) {
    val openTimeDialog = remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(10.dp, 5.dp, 5.dp, 5.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier
                .background(Color.White)
                .padding(8.dp)
        ) {
            Text(
                text = "Select Drink",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth(),
                maxLines = 2,
                fontSize = 20.sp,
                fontWeight = FontWeight.W500,
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
                        onCurrentSelectedDrinkIcon = onCurrentSelectedDrinkIcon,
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
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
                TextButton(onClick = {
                    openDialog.value = false
                    onClick()
                }) {
                    Text(
                        "Okay",
                        fontWeight = FontWeight.ExtraBold,
                        color = primaryColor,
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
    openTimeDialog: MutableState<Boolean>,
    onCurrentSelectedDrinkSize: (String) -> Unit,
    onCurrentSelectedDrinkIcon: (Int) -> Unit,
    onCurrentSelectedDrinkTime: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            openTimeDialog.value = true
            onCurrentSelectedDrinkIcon(selectDrink.icon)
            onCurrentSelectedDrinkSize(selectDrink.size)
        }
    ) {
        Image(
            painter = painterResource(id = selectDrink.icon),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(
                color = primaryColor
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
        if (openTimeDialog.value) {
            TimeDialog(openDialog = openTimeDialog, onCurrentSelectedDrinkTime = onCurrentSelectedDrinkTime)
        }
    }
}

@Composable
fun TimeDialog(
    modifier: Modifier = Modifier,
    onCurrentSelectedDrinkTime: (String) -> Unit,
    openDialog: MutableState<Boolean>,
) {
    val mContext = LocalContext.current

    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]



    val timePickerDialog = TimePickerDialog(
        mContext,
        { _, mHour: Int, mMinute: Int ->
            onCurrentSelectedDrinkTime("$mHour:$mMinute ")
        }, mHour, mMinute, false
    )
    if (openDialog.value) {
        timePickerDialog.show()
        openDialog.value = true
    }
}


data class SelectDrink(
    val size: String,
    val icon: Int,
)
