/*
 * Copyright (C)2023 Brandy Odhiambo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.brandyodhiambo.statistics.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.brandyodhiambo.common.R
import com.brandyodhiambo.common.domain.model.Achievement
import com.brandyodhiambo.home.presentation.achievement.AchievementViewModel
import com.brandyodhiambo.home.presentation.homeScreen.HomeViewModel
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
    achievementViewModel: AchievementViewModel = hiltViewModel()
) {
    val dailyStatistics = statisticsViewModel.dailyStatisticsFromDB.observeAsState()
    val weeklyStatistics = statisticsViewModel.weeklyStatisticsFromDB.observeAsState()
    val monthlyStatistics = statisticsViewModel.monthlyStatisticsFromDB.observeAsState()
    val idealWaterIntake = homeViewModel.idealWaterIntakeFromDb.observeAsState()
    val goalWaterIntake = homeViewModel.goalWaterIntakeFromDb.observeAsState()

    val barChartDataDaily: List<BarChartEntity> = dailyStatistics.value?.map { dailyStat ->
        BarChartEntity(
            dailyStat.amountTaken,
            MaterialTheme.colorScheme.primary,
            dailyStat.day.take(3)
        )
    } ?: emptyList()

    val barChartDataWeek: List<BarChartEntity> = weeklyStatistics.value?.map { weekStat ->
        BarChartEntity(weekStat.amountTaken, MaterialTheme.colorScheme.primary, weekStat.week)
    } ?: emptyList()

    val barChartDataMonth: List<BarChartEntity> = monthlyStatistics.value?.map { monthStat ->
        BarChartEntity(
            monthStat.amountTaken,
            MaterialTheme.colorScheme.primary,
            monthStat.month.take(3)
        )
    } ?: emptyList()

    val verticalAxisValues =
        listOf(0.0f, 20.0f, 40.0f, 60.0f, 80.0f, 100.0f)

    val setGraphDaily = remember { mutableStateOf(true) }
    val setGraphMonthly = remember { mutableStateOf(false) }
    val setGraphWeek = remember { mutableStateOf(false) }

    val weeklyAverage = weeklyStatistics.value?.map { it.amountTaken }?.average() ?: 0.0
    val monthlyAverage = monthlyStatistics.value?.map { it.amountTaken }?.average() ?: 0.0
    val dailyAverage = dailyStatistics.value?.map { it.amountTaken }?.average() ?: 0.0

    val drinkFrequency =
        goalWaterIntake.value?.waterIntake?.div(idealWaterIntake.value?.waterIntake ?: 1) ?: 0

    val average = if (setGraphDaily.value) {
        dailyAverage
    } else if (setGraphWeek.value) {
        weeklyAverage
    } else {
        monthlyAverage
    }

    val weekAchievement = achievementViewModel.isAchieved.observeAsState(initial = emptyList())

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp, start = 8.dp, end = 8.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Box(
                                    modifier = Modifier
                                        .height(30.dp)
                                        .width(100.dp)
                                        .background(
                                            color = if (setGraphDaily.value) {
                                                MaterialTheme.colorScheme.primary
                                            } else {
                                                MaterialTheme.colorScheme.primary.copy(
                                                    alpha = 0.2f
                                                )
                                            },
                                            shape = RoundedCornerShape(8.dp)
                                        ).clickable {
                                            setGraphDaily.value = true
                                            setGraphWeek.value = false
                                            setGraphMonthly.value = false
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Daily",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .height(30.dp)
                                        .width(100.dp)
                                        .background(
                                            color = if (setGraphWeek.value) {
                                                MaterialTheme.colorScheme.primary
                                            } else {
                                                MaterialTheme.colorScheme.primary.copy(
                                                    alpha = 0.2f
                                                )
                                            },
                                            shape = RoundedCornerShape(8.dp)
                                        ).clickable {
                                            setGraphDaily.value = false
                                            setGraphWeek.value = true
                                            setGraphMonthly.value = false
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Weekly",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .height(30.dp)
                                        .width(100.dp)
                                        .background(
                                            color = if (setGraphMonthly.value) {
                                                MaterialTheme.colorScheme.primary
                                            } else {
                                                MaterialTheme.colorScheme.primary.copy(
                                                    alpha = 0.2f
                                                )
                                            },
                                            shape = RoundedCornerShape(8.dp)
                                        ).clickable {
                                            setGraphDaily.value = false
                                            setGraphWeek.value = false
                                            setGraphMonthly.value = true
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Monthly",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                            if (setGraphDaily.value) {
                                BarChart(
                                    modifier = Modifier.fillMaxSize().padding(16.dp),
                                    barChartData = barChartDataDaily,
                                    verticalAxisValues = verticalAxisValues,
                                    isShowHorizontalLines = true,
                                    isShowVerticalAxis = true
                                )
                            }
                            if (setGraphWeek.value) {
                                BarChart(
                                    modifier = Modifier.fillMaxSize().padding(16.dp),
                                    barChartData = barChartDataWeek,
                                    verticalAxisValues = verticalAxisValues,
                                    isShowHorizontalLines = true,
                                    isShowVerticalAxis = true
                                )
                            }
                            if (setGraphMonthly.value) {
                                BarChart(
                                    modifier = Modifier.fillMaxSize().padding(16.dp),
                                    barChartData = barChartDataMonth,
                                    verticalAxisValues = verticalAxisValues,
                                    isShowHorizontalLines = true,
                                    isShowVerticalAxis = true
                                )
                            }
                        }
                    }
                }
                item {
                    Last7DayGoals(
                        weekAchivement = weekAchievement.value ?: emptyList()
                    )
                }
                item {
                    DrinkWaterReport(
                        currentDailyAverage = dailyAverage.toInt(),
                        currentWeeklyAverage = weeklyAverage.toInt(),
                        currentMonthlyAverage = monthlyAverage.toInt(),
                        currentAverage = average.toInt(),
                        currentDrinkFrequency = drinkFrequency
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
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Last 7 Days Goals Achieve",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                if (weekAchivement.isEmpty()) {
                    Text(
                        text = "You have no achievements yet",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    items(weekAchivement.takeLast(7)) { weeks ->
                        WeeksAcheive(weeks = weeks)
                    }
                }
            }
        }
    }
}

@Composable
fun WeeksAcheive(
    weeks: Achievement
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (weeks.isAchieved) {
            GoldCup()
        } else {
            BlackCup()
        }
        Text(
            text = weeks.day.take(3),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun GoldCup() {
    Card(
        shape = CircleShape,
        border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .size(48.dp)
            .padding(4.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_cup),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp),
            contentDescription = null
        )
    }
}

@Composable
fun BlackCup() {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            2.dp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
        ),
        modifier = Modifier
            .size(48.dp)
            .padding(4.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_black_cup),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp),
            contentDescription = null
        )
    }
}

@Composable
fun DrinkWaterReport(
    currentDailyAverage: Int,
    currentWeeklyAverage: Int,
    currentMonthlyAverage: Int,
    currentDrinkFrequency: Int,
    currentAverage: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "Drink Water Report",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Daily Average",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Text(
                    text = "$currentDailyAverage ml/day",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Divider(
                modifier = Modifier.height(1.dp).padding(start = 8.dp, end = 8.dp),
                color = Color.Gray,
                thickness = 1.dp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Weekly Average",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Text(
                    text = "$currentWeeklyAverage ml/day",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Divider(
                modifier = Modifier.height(1.dp).padding(start = 8.dp, end = 8.dp),
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.2f),
                thickness = 1.dp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Monthly Average",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Text(
                    text = "$currentMonthlyAverage ml/day",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Divider(
                modifier = Modifier.height(1.dp).padding(start = 8.dp, end = 8.dp),
                color = Color.Gray,
                thickness = 1.dp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Average Completion",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Text(
                    text = "$currentAverage%",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Divider(
                modifier = Modifier.height(1.dp).padding(start = 8.dp, end = 8.dp),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.12f),
                thickness = 1.dp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Drink Frequency",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Text(
                    text = "$currentDrinkFrequency Times/day",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
