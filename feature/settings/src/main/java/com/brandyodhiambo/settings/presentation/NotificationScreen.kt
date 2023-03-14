package com.brandyodhiambo.settings.presentation

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandyodhiambo.common.domain.model.Day
import com.brandyodhiambo.common.util.toInitials
import com.brandyodhiambo.designsystem.components.NotificationSwitcher
import com.brandyodhiambo.designsystem.theme.primaryColor
import com.brandyodhiambo.quench.views.screens.dialogs.Loader
import com.brandyodhiambo.settings.R
import com.ramcosta.composedestinations.annotation.Destination

interface NotificationNavigator {
    fun navigateToReminderScreen()

    fun popBackStack()

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun NotificationScreen(
    navigator: NotificationNavigator
) {
    val days = listOf(
        Day("Monday", true),
        Day("Tuesday", true),
        Day("Wednesday", true),
        Day("Thursday", true),
        Day("Friday", true),
        Day("Saturday", true),
        Day("Sunday", false)
    )

    val reminder = listOf(
        Reminder(time = "8:20 AM", days = days, isOn = true),
        Reminder(time = "9:30 AM", days = days, isOn = false),
        Reminder(time = "10:20 AM", days = days, isOn = true),
        Reminder(time = "11:40 AM", days = days, isOn = true)

    )

    Scaffold(
        backgroundColor = primaryColor,
        topBar = {
            TopAppBar(
                title = { Text(text = "Notification", color = Color.White, fontSize = 16.sp) },
                backgroundColor = primaryColor,
                navigationIcon = {
                    IconButton(onClick = {
                        navigator.popBackStack()
                    }) {
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
                if(reminder.isEmpty()){
                    item {
                        Loader(compositions = R.raw.clock)
                    }
                } else {
                    items(reminder) { reminder ->
                        ReminderNotificationTime(reminder = reminder)
                    }
                }
            }
        }
    }
}

@Composable
fun AddReminder(navigator: NotificationNavigator) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
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
            IconButton(onClick = { navigator.navigateToReminderScreen() }) {
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
        shape = RoundedCornerShape(8.dp)
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
                .padding(4.dp)
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
                    LazyRow() {
                        items(reminder.days) { day ->
                            WeeksReminder(day = day, reminder = reminder)
                        }
                    }
                }
                // Switch
                NotificationSwitcher(
                    isOn = reminder.isOn,
                    size = 30.dp,
                    padding = 5.dp,
                    onToggle = {
                        !reminder.isOn
                    }
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
        Box(
            modifier = Modifier
                .padding(4.dp)
                .size(25.dp)
                .border(
                    border = BorderStroke(
                        2.dp,
                        color = if (day.isOn && reminder.isOn) {
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
}


data class Reminder(
    val time: String,
    val days: List<Day>,
    val isOn: Boolean
)
