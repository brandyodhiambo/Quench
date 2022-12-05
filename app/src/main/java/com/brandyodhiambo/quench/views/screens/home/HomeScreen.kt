package com.brandyodhiambo.quench.views.screens.home

import com.brandyodhiambo.quench.R
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.brandyodhiambo.quench.views.composables.CircularRating
import com.brandyodhiambo.quench.views.screens.dialogs.*
import com.brandyodhiambo.quench.views.ui.theme.lightBlue
import com.brandyodhiambo.quench.views.ui.theme.primaryColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {

    val openTimeDialog = remember { mutableStateOf(false) }
    val waterIntakeDialog = remember { mutableStateOf(false) }
    val idealWaterIntakeDialog = remember { mutableStateOf(false) }
    val selectedDrinkDialog = remember { mutableStateOf(false) }
    val waterleveltime = listOf(
        Intake("100 Ml", "12:30 Pm"),
        Intake("300 Ml", "18:30 Pm"),
        Intake("250 Ml", "11:30 Pm"),
        Intake("150 Ml", "19:30 Pm"),
    )
    Scaffold(
        backgroundColor = primaryColor
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn {
                item {
                    WaterIntake(openDialog = waterIntakeDialog, idealWaterDialog = idealWaterIntakeDialog)
                }
                item {
                    WaterRecord(openDialog = openTimeDialog,selectedDrinkDialog = selectedDrinkDialog)
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(waterleveltime) { intake ->
                    WaterIntakeTimeAndLevel(intake = intake)
                }
            }


            if (openTimeDialog.value) {
                Dialog(onDismissRequest = { openTimeDialog.value }) {
                    TimeSetterDialog(openDialogCustom = openTimeDialog)
                    //CongratulationsDialog(openDialogCustom = openTimeDialog)
                }
            }

            if (waterIntakeDialog.value){
                Dialog(onDismissRequest = { waterIntakeDialog.value }) {
                    WaterIntakeDialog(openCustomDialog = waterIntakeDialog)
                }
            }

            if (idealWaterIntakeDialog.value){
                Dialog(onDismissRequest = { idealWaterIntakeDialog.value }) {
                    IdealIntakeGoalDialog(idealCustomDialog = idealWaterIntakeDialog)
                }
            }

            if (selectedDrinkDialog.value){
                Dialog(onDismissRequest = { selectedDrinkDialog.value }) {
                    SelectDrinkComposable(openDialog = selectedDrinkDialog)
                }
            }


        }
    }
}


@Composable
fun WaterIntake(openDialog: MutableState<Boolean>,idealWaterDialog: MutableState<Boolean>) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .padding(16.dp)
            .fillMaxWidth(), elevation = 4.dp
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
                Image(
                    painter = painterResource(id = R.drawable.ic_glass), contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        idealWaterDialog.value = true
                    },
                ) {
                    Text(text = "Ideal water intake", fontSize = 14.sp, color = Color.Gray)
                    Text(
                        text = "2810 ml",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp),
                thickness = 2.dp,
                color = primaryColor
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
                        openDialog.value = true
                    },
                ) {
                    Text(text = "Water intake goal",
                        fontSize = 14.sp,
                        color = Color.Gray)
                    Text(
                        text = "2400 ml",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


@Composable
fun WaterRecord(openDialog:MutableState<Boolean>,selectedDrinkDialog:MutableState<Boolean>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(start = 16.dp, end = 16.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            CircularRating(percentage = 7f, drunk = 800)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Card(
                    backgroundColor = lightBlue,
                    shape = CircleShape,
                    elevation = 8.dp,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(12.dp)
                        .clickable {
                            openDialog.value = true
                        },
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_clock),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = " 12:04 AM", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Card(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(12.dp)
                        .clickable {
                            //Todo
                        },
                    backgroundColor = lightBlue,
                    shape = CircleShape,
                    elevation = 8.dp
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Image(imageVector = Icons.Default.Add, contentDescription = null)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = " Add Level", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Card(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(12.dp)
                        .clickable {
                            selectedDrinkDialog.value = true
                        },
                    backgroundColor = lightBlue,
                    shape = CircleShape,
                    elevation = 8.dp
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_glass),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = " 100 Ml", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun WaterIntakeTimeAndLevel(
    intake: Intake
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_glass), contentDescription = null
                )
                Text(text = intake.level, fontSize = 16.sp, fontWeight = FontWeight.W400)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = intake.time,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.W300
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chevron),
                        tint = Color.Gray,
                        contentDescription = null
                    )
                }
            }

        }
        Divider(
            thickness = 1.dp, color = Color.Gray
        )
    }

}

data class Intake(
    val level: String, val time: String
)