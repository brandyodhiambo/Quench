package com.brandyodhiambo.quench.views.screens.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandyodhiambo.quench.R
import com.brandyodhiambo.designsystem.theme.primaryColor


val selectedDrinks = listOf(
    SelectDrink(
        icon = R.drawable.small_cup,
        size = "50ml"
    ),
    SelectDrink(
        icon = R.drawable.small_cup,
        size = "150ml"
    ),
    SelectDrink(
        icon = R.drawable.small_glass,
        size = "100ml"
    ),
    SelectDrink(
        icon = R.drawable.ic_glass,
        size = "200ml"
    ),
    SelectDrink(
        icon = R.drawable.ic_cup,
        size = "300ml"
    ),
    SelectDrink(
        icon = R.drawable.big_cup,
        size = "400ml"
    ),
    SelectDrink(
        icon = R.drawable.kettle,
        size = "500ml"
    ),
    SelectDrink(
        icon = R.drawable.big_cup,
        size = "600ml"
    ),
    SelectDrink(
        icon = R.drawable.kettle,
        size = "700ml"
    ),
    SelectDrink(
        icon = R.drawable.kettle,
        size = "1000ml"
    ),
)

@Composable
fun SelectDrinkComposable(
    modifier: Modifier = Modifier,
    openDialog: MutableState<Boolean>
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(10.dp, 5.dp, 5.dp, 5.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier.background(Color.White).padding(8.dp)
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
                    SelectCard(selectDrink = selected)
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
fun SelectCard(selectDrink: SelectDrink) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
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

    }
}

data class SelectDrink(
    val size: String,
    val icon: Int
)