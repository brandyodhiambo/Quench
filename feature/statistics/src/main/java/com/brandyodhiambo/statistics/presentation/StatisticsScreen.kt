package com.brandyodhiambo.statistics.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandyodhiambo.common.R
import com.brandyodhiambo.designsystem.theme.blackColor
import com.brandyodhiambo.designsystem.theme.primaryColor
import com.brandyodhiambo.statistics.presentation.component.barChartDataMonth
import com.brandyodhiambo.statistics.presentation.component.barChartDataWeek
import com.brandyodhiambo.statistics.presentation.component.barChartDataYear
import com.brandyodhiambo.statistics.presentation.component.verticalAxisValues
import com.mahmoud.composecharts.barchart.BarChart
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun StatisticsScreen(
    navigator: DestinationsNavigator,
) {
    val setGraphDaily = remember { mutableStateOf(true) }
    val setGraphMonthly = remember { mutableStateOf(false) }
    val setGraphYearly = remember { mutableStateOf(false) }

    Scaffold(
        backgroundColor = primaryColor,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            LazyColumn {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                        elevation = 4.dp,
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp, start = 8.dp, end = 8.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                            ) {
                                Box(
                                    modifier = Modifier
                                        .height(30.dp)
                                        .width(70.dp)
                                        .background(
                                            color = if (setGraphDaily.value) {
                                                primaryColor
                                            } else {
                                                primaryColor.copy(
                                                    alpha = 0.2f,
                                                )
                                            },
                                            shape = RoundedCornerShape(8.dp),
                                        ).clickable {
                                            setGraphDaily.value = true
                                            setGraphMonthly.value = false
                                            setGraphYearly.value = false
                                        },
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = "Daily",
                                        style = MaterialTheme.typography.h6,
                                        fontWeight = FontWeight.Normal,
                                        color = blackColor,
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .height(30.dp)
                                        .width(70.dp)
                                        .background(
                                            color = if (setGraphMonthly.value) {
                                                primaryColor
                                            } else {
                                                primaryColor.copy(
                                                    alpha = 0.2f,
                                                )
                                            },
                                            shape = RoundedCornerShape(8.dp),
                                        ).clickable {
                                            setGraphDaily.value = false
                                            setGraphMonthly.value = true
                                            setGraphYearly.value = false
                                        },
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = "Monthly",
                                        style = MaterialTheme.typography.h6,
                                        fontWeight = FontWeight.Normal,
                                        color = blackColor,
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .height(30.dp)
                                        .width(70.dp)
                                        .background(
                                            color = if (setGraphYearly.value) {
                                                primaryColor
                                            } else {
                                                primaryColor.copy(
                                                    alpha = 0.2f,
                                                )
                                            },
                                            shape = RoundedCornerShape(8.dp),
                                        ).clickable {
                                            setGraphDaily.value = false
                                            setGraphMonthly.value = false
                                            setGraphYearly.value = true
                                        },
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = "Yearly",
                                        style = MaterialTheme.typography.h6,
                                        fontWeight = FontWeight.Normal,
                                        color = blackColor,
                                    )
                                }
                            }
                            if (setGraphDaily.value) {
                                BarChart(
                                    modifier = Modifier.fillMaxSize().padding(16.dp),
                                    barChartData = barChartDataWeek,
                                    verticalAxisValues = verticalAxisValues,
                                    isShowHorizontalLines = true,
                                    isShowVerticalAxis = true,
                                )
                            }
                            if (setGraphMonthly.value) {
                                BarChart(
                                    modifier = Modifier.fillMaxSize().padding(16.dp),
                                    barChartData = barChartDataMonth,
                                    verticalAxisValues = verticalAxisValues,
                                    isShowHorizontalLines = true,
                                    isShowVerticalAxis = true,
                                )
                            }
                            if (setGraphYearly.value) {
                                BarChart(
                                    modifier = Modifier.fillMaxSize().padding(16.dp),
                                    barChartData = barChartDataYear,
                                    verticalAxisValues = verticalAxisValues,
                                    isShowHorizontalLines = true,
                                    isShowVerticalAxis = true,
                                )
                            }
                        }
                    }
                }
                item {
                    Last7DayGoals()
                }
                item {
                    DrinkWaterReport()
                }
            }
        }
    }
}

val weekAchiement = listOf(
    Weeks(isAcheived = true, "Sun"),
    Weeks(isAcheived = true, "Mon"),
    Weeks(isAcheived = false, "Tue"),
    Weeks(isAcheived = false, "Wed"),
    Weeks(isAcheived = true, "Thu"),
    Weeks(isAcheived = false, "Fri"),
    Weeks(isAcheived = false, "Sat"),
)

@Composable
fun Last7DayGoals() {
    Card(
        modifier = Modifier
            .height(135.dp)
            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            Text(
                text = "Last 7 Days Goals Achieve",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = primaryColor,
            )
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                items(weekAchiement) { weeks ->
                    WeeksAcheive(weeks = weeks)
                }
            }
        }
    }
}

@Composable
fun WeeksAcheive(
    weeks: Weeks,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (weeks.isAcheived == true) {
            GoldCup()
        } else {
            BlackCup()
        }
        Text(text = weeks.day, fontSize = 16.sp, fontWeight = FontWeight.W400)
    }
}

@Composable
fun GoldCup() {
    Card(
        shape = CircleShape,
        border = BorderStroke(2.dp, color = primaryColor),
        modifier = Modifier
            .size(48.dp)
            .padding(4.dp),
        elevation = 4.dp,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_cup),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp),
            contentDescription = null,
        )
    }
}

@Composable
fun BlackCup() {
    Card(
        shape = CircleShape,
        border = BorderStroke(2.dp, color = Color.Gray),
        modifier = Modifier
            .size(48.dp)
            .padding(4.dp),
        elevation = 4.dp,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_black_cup),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp),
            contentDescription = null,
        )
    }
}

@Composable
fun DrinkWaterReport() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp),
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            Text(
                text = "Drink Water Report",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = primaryColor,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        tint = primaryColor,
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Week Avarage",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = blackColor,
                    )
                }
                Text(
                    text = "1850ml/day",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = primaryColor,
                )
            }
            Divider(
                modifier = Modifier.height(1.dp).padding(start = 8.dp, end = 8.dp),
                color = Color.Gray,
                thickness = 1.dp,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        tint = primaryColor,
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Monthly Avarage",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = blackColor,
                    )
                }
                Text(
                    text = "1450ml/day",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = primaryColor,
                )
            }
            Divider(
                modifier = Modifier.height(1.dp).padding(start = 8.dp, end = 8.dp),
                color = Color.Gray,
                thickness = 1.dp,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        tint = primaryColor,
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Avarage Completion",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = blackColor,
                    )
                }
                Text(
                    text = "55%",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = primaryColor,
                )
            }
            Divider(
                modifier = Modifier.height(1.dp).padding(start = 8.dp, end = 8.dp),
                color = Color.Gray,
                thickness = 1.dp,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        tint = primaryColor,
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Drink Frequency",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = blackColor,
                    )
                }
                Text(
                    text = "5 Times/day",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = primaryColor,
                )
            }
        }
    }
}

data class Weeks(
    val isAcheived: Boolean,
    val day: String,
)
