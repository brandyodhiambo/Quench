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
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.brandyodhiambo.common.R
import com.brandyodhiambo.common.domain.model.Achievement
import com.brandyodhiambo.designsystem.theme.blackColor
import com.brandyodhiambo.designsystem.theme.primaryColor
import com.brandyodhiambo.home.presentation.home_screen.HomeViewModel
import com.mahmoud.composecharts.barchart.BarChart
import com.mahmoud.composecharts.barchart.BarChartEntity
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun StatisticsScreen(
    navigator: DestinationsNavigator,
    statisticsViewModel: StatisticsViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val dailyStatistics = statisticsViewModel.dailyStatisticsFromDB.observeAsState()
    val weeklyStatistics = statisticsViewModel.weeklyStatisticsFromDB.observeAsState()
    val monthlyStatistics = statisticsViewModel.monthlyStatisticsFromDB.observeAsState()
    val idealWaterIntake = homeViewModel.idealWaterIntakeFromDb.observeAsState()
    val goalWaterIntake = homeViewModel.goalWaterIntakeFromDb.observeAsState()

    val barChartDataDaily: List<BarChartEntity> = dailyStatistics.value?.map { dailyStat ->
        BarChartEntity(dailyStat.amountTaken, primaryColor, dailyStat.day.take(3))
    } ?: emptyList()

    val barChartDataWeek: List<BarChartEntity> = weeklyStatistics.value?.map { weekStat ->
        BarChartEntity(weekStat.amountTaken, primaryColor, weekStat.week)
    } ?: emptyList()

    val barChartDataMonth: List<BarChartEntity> = monthlyStatistics.value?.map { monthStat ->
        BarChartEntity(monthStat.amountTaken, primaryColor, monthStat.month.take(3))
    } ?: emptyList()

    val verticalAxisValues =
        listOf(0.0f, 20.0f, 40.0f, 60.0f, 80.0f, 100.0f)

    val setGraphDaily = remember { mutableStateOf(true) }
    val setGraphMonthly = remember { mutableStateOf(false) }
    val setGraphWeek = remember { mutableStateOf(false) }

    val weeklyAverage = weeklyStatistics.value?.map { it.amountTaken }?.average() ?: 0.0
    val monthlyAverage = monthlyStatistics.value?.map { it.amountTaken }?.average() ?: 0.0
    val dailyAverage = dailyStatistics.value?.map { it.amountTaken }?.average() ?: 0.0

    val drinkFrequency = goalWaterIntake.value?.waterIntake?.div(idealWaterIntake.value?.waterIntake ?: 1) ?: 0

    val average = if (setGraphDaily.value) {
        dailyAverage
    } else if (setGraphWeek.value) {
        weeklyAverage
    } else {
        monthlyAverage
    }

    val weekAchiement = listOf(
        Achievement(isAchieved = true, "Sun"),
        Achievement(isAchieved = true, "Mon"),
        Achievement(isAchieved = false, "Tue"),
        Achievement(isAchieved = false, "Wed"),
        Achievement(isAchieved = true, "Thu"),
        Achievement(isAchieved = false, "Fri"),
        Achievement(isAchieved = false, "Sat"),
    )

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
                                        .width(100.dp)
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
                                            setGraphWeek.value = false
                                            setGraphMonthly.value = false
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
                                        .width(100.dp)
                                        .background(
                                            color = if (setGraphWeek.value) {
                                                primaryColor
                                            } else {
                                                primaryColor.copy(
                                                    alpha = 0.2f,
                                                )
                                            },
                                            shape = RoundedCornerShape(8.dp),
                                        ).clickable {
                                            setGraphDaily.value = false
                                            setGraphWeek.value = true
                                            setGraphMonthly.value = false
                                        },
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = "Weekly",
                                        style = MaterialTheme.typography.h6,
                                        fontWeight = FontWeight.Normal,
                                        color = blackColor,
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .height(30.dp)
                                        .width(100.dp)
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
                                            setGraphWeek.value = false
                                            setGraphMonthly.value = true
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
                            }
                            if (setGraphDaily.value) {
                                BarChart(
                                    modifier = Modifier.fillMaxSize().padding(16.dp),
                                    barChartData = barChartDataDaily,
                                    verticalAxisValues = verticalAxisValues,
                                    isShowHorizontalLines = true,
                                    isShowVerticalAxis = true,
                                )
                            }
                            if (setGraphWeek.value) {
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
                        }
                    }
                }
                item {
                    Last7DayGoals(
                        weekAchivement = weekAchiement,
                    )
                }
                item {
                    DrinkWaterReport(
                        currentDailyAverage = dailyAverage.toInt(),
                        currentWeeklyAverage = weeklyAverage.toInt(),
                        currentMonthlyAverage = monthlyAverage.toInt(),
                        currentAverage = average.toInt(),
                        currentDrinkFrequency = drinkFrequency,
                    )
                }
            }
        }
    }
}

@Composable
fun Last7DayGoals(weekAchivement: List<Achievement>) {
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
                items(weekAchivement.takeLast(7)) { weeks ->
                    WeeksAcheive(weeks = weeks)
                }
            }
        }
    }
}

@Composable
fun WeeksAcheive(
    weeks: Achievement,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (weeks.isAchieved) {
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
fun DrinkWaterReport(
    currentDailyAverage: Int,
    currentWeeklyAverage: Int,
    currentMonthlyAverage: Int,
    currentDrinkFrequency: Int,
    currentAverage: Int,
) {
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
                        text = "Daily Average",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = blackColor,
                    )
                }
                Text(
                    text = "$currentDailyAverage ml/day",
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
                        text = "Weekly Average",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = blackColor,
                    )
                }
                Text(
                    text = "$currentWeeklyAverage ml/day",
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
                        text = "Monthly Average",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = blackColor,
                    )
                }
                Text(
                    text = "$currentMonthlyAverage ml/day",
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
                        text = "Average Completion",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = blackColor,
                    )
                }
                Text(
                    text = "$currentAverage%",
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
                    text = "$currentDrinkFrequency Times/day",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = primaryColor,
                )
            }
        }
    }
}
