package com.brandyodhiambo.quench.views.composables

import androidx.compose.ui.graphics.Color
import com.brandyodhiambo.quench.views.ui.theme.primaryColor
import com.mahmoud.composecharts.barchart.BarChartEntity


val barChartDataYear = listOf(
    BarChartEntity(30f, primaryColor, "2018"),
    BarChartEntity(20f, primaryColor, "2019"),
    BarChartEntity(100f, primaryColor, "2020"),
    BarChartEntity(70f, primaryColor, "2021"),
    BarChartEntity(10f, primaryColor, "2022")
)

val barChartDataMonths = listOf(
    BarChartEntity(30f, primaryColor, "J"),
    BarChartEntity(50f, primaryColor, "F"),
    BarChartEntity(20f, primaryColor, "M"),
    BarChartEntity(80f, primaryColor, "A"),
    BarChartEntity(20f, primaryColor, "M"),
    BarChartEntity(90f, primaryColor, "J"),
    BarChartEntity(05f, primaryColor, "J"),
    BarChartEntity(100f, primaryColor, "A"),
    BarChartEntity(40f, primaryColor, "S"),
    BarChartEntity(70f, primaryColor, "O"),
    BarChartEntity(70f, primaryColor, "N"),
    BarChartEntity(70f, primaryColor, "D"),
)

val verticalAxisValues =
    listOf(0.0f, 20.0f, 40.0f, 60.0f, 80.0f, 100.0f)

val verticalAxisValues2 = listOf(1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f)