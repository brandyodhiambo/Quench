package com.brandyodhiambo.quench.views.screens.notification

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandyodhiambo.quench.R
import com.brandyodhiambo.quench.utils.toInitials
import com.brandyodhiambo.quench.views.screens.destinations.AddReminderScreenDestination
import com.brandyodhiambo.quench.views.screens.settings.SettingScreen
import com.brandyodhiambo.quench.views.screens.statistics.BlackCup
import com.brandyodhiambo.quench.views.screens.statistics.GoldCup
import com.brandyodhiambo.quench.views.screens.statistics.WeeksAcheive
import com.brandyodhiambo.quench.views.screens.statistics.weekAchiement
import com.brandyodhiambo.quench.views.ui.theme.primaryColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator.navigateUp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun NotificationScreen(
    navigator: DestinationsNavigator,
) {

    val days = listOf(
        Day("Monday", true),
        Day("Tuesday", true),
        Day("Wednesday", true),
        Day("Thursday", true),
        Day("Friday", true),
        Day("Saturday", true),
        Day("Sunday", false),
    )

    val reminder = listOf(
        Reminder(time = "8:20 AM", days = days, isOn = true),
        Reminder(time = "9:30 AM", days = days, isOn = false),
        Reminder(time = "10:20 AM", days = days, isOn = true),
        Reminder(time = "11:40 AM", days = days, isOn = true),

        )



    Scaffold(
        backgroundColor = primaryColor,
        topBar = {
            TopAppBar(
                title = { Text(text = "Notification", color = Color.White, fontSize = 16.sp) },
                backgroundColor = primaryColor,
                navigationIcon = {
                    IconButton(onClick = { navigator.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            tint = Color.White,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
            ) {
                item {
                    AddReminder(navigator = navigator)
                }
                items(reminder) { reminder ->
                    ReminderNotificationTime(reminder = reminder)
                }
            }

        }
    }
}


@Composable
fun AddReminder(navigator: DestinationsNavigator) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Add New Reminder",
                color = Color.Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            IconButton(onClick = { navigator.navigate(AddReminderScreenDestination) }) {
                Icon(imageVector = Icons.Default.Add, tint = Color.Gray, contentDescription = null)
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}


@Composable
fun ReminderNotificationTime(reminder: Reminder) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if (reminder.isOn) {
                        Color.White
                    } else {
                        Color.LightGray
                    }
                )
                .padding(4.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Text(
                        text = reminder.time,
                        color = if (reminder.isOn) {
                            Color.Black
                        } else {
                            Color.Gray
                        },
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    LazyRow(
                    ) {
                        items(reminder.days) { day ->
                            WeeksReminder(day = day, reminder = reminder)
                        }
                    }

                }
                // Switch
                Icon(
                    modifier = Modifier
                        .size(50.dp)
                        .clickable {

                        },
                    painter = painterResource(
                        id = if (reminder.isOn) {
                            R.drawable.ic_toggle_on
                        } else {
                            R.drawable.ic_toggle_off
                        }
                    ),
                    tint = if (reminder.isOn) {
                        primaryColor
                    } else {
                        Color.Gray
                    },
                    contentDescription = null
                )

            }
        }
    }


}

@Composable
fun WeeksReminder(day: Day, reminder: Reminder) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DayIsOn(day = day, reminder = reminder)

    }
}

@Composable
fun DayIsOn(day: Day, reminder: Reminder) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(25.dp)
            .border(
                border = BorderStroke(
                    2.dp, color = if (day.isOn && reminder.isOn) {
                        primaryColor
                    } else {
                        Color.Gray
                    }
                ),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.day.toInitials(),
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )
    }
}


data class Reminder(
    val time: String,
    val days: List<Day>,
    val isOn: Boolean,
)

data class Day(
    val day: String,
    val isOn: Boolean,
)