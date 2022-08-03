package com.brandyodhiambo.quench.views.screens.home

import com.brandyodhiambo.quench.R
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandyodhiambo.quench.views.composables.CircularRating
import com.brandyodhiambo.quench.views.ui.theme.lightBlue
import com.brandyodhiambo.quench.views.ui.theme.primaryColor
import com.ramcosta.composedestinations.annotation.Destination


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun HomeScreen() {

    val waterleveltime = listOf(
        Intake("100 Ml","12:30 Pm"),
        Intake("100 Ml","12:30 Pm"),
        Intake("100 Ml","12:30 Pm"),
        Intake("100 Ml","12:30 Pm"),
    )
   Scaffold(
       backgroundColor = primaryColor
   ) { paddingValues ->
       Box(
           modifier = Modifier
               .fillMaxSize()
               .padding(paddingValues)
       ) {
            LazyColumn{
                item {
                    WaterIntake()
                }
                item {
                    WaterRecord()
                }
                items(waterleveltime){ intake->
                    WaterIntakeTimeAndLevel(intake = intake)
                }
            }
       }
   }
}


@Composable
fun WaterIntake() {
    Card(
        modifier = Modifier
            .height(100.dp)
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = 4.dp
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
                Image(painter = painterResource(id = R.drawable.ic_glass), contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Ideal water intake", fontSize = 14.sp, color = Color.Gray)
                    Text(text = "2810 ml", fontSize = 16.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
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
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Water intake goal", fontSize = 14.sp, color = Color.Gray)
                    Text(text = "2400 ml", fontSize = 16.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}


@Composable
fun WaterRecord() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
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
            CircularRating(percentage = 7f)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(16.dp),
                    backgroundColor = lightBlue,
                    shape = CircleShape
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Image(painter = painterResource(id = R.drawable.ic_clock), contentDescription = null )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text =" 12:04 AM", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Card(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(16.dp),
                    backgroundColor = lightBlue,
                    shape = CircleShape
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Image(painter = painterResource(id = R.drawable.ic_glass), contentDescription = null )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text =" 100 Ml", fontSize = 12.sp, fontWeight = FontWeight.Bold)
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.ic_glass), contentDescription = null)
                Text(text = intake.level, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = intake.time, fontSize = 14.sp, color = Color.Gray)
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.ic_chevron), tint = Color.Gray, contentDescription = null )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}
data class Intake(
    val level:String,
    val time:String
)