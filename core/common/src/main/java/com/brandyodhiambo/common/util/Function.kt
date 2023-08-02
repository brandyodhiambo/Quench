package com.brandyodhiambo.common.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.*

@SuppressLint("SimpleDateFormat")
fun formatDate(timestamp: Long): String {
    val df1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
/*            val timeZone = TimeZone.getDefault()
            df1.timeZone = timeZone*/
    val result = Date(timestamp)
    val startCalendar = Calendar.getInstance()
    startCalendar.time = result
    val format = SimpleDateFormat("EEEE, MMMM d, yyyy 'at' hh:mm a")

    return format.format(startCalendar.time)
}

fun String.toInitials(): String {
    return this
        .split(' ')
        .mapNotNull { it.firstOrNull()?.toString() }
        .reduce { acc, s -> acc + s }
}

fun isEndOfDay(dateTime: LocalDateTime): Boolean {
    val endOfDay = LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MAX)
    return dateTime == endOfDay
}

fun isEndOfWeek(date: LocalDate): Boolean {
    val endOfWeek = date.with(TemporalAdjusters.next(DayOfWeek.SUNDAY))
    return date == endOfWeek
}

fun isEndOfMonth(date: LocalDate): Boolean {
    val endOfMonth = date.with(TemporalAdjusters.lastDayOfMonth())
    return date == endOfMonth
}

fun getCurrentDay(): String {
    val now = LocalDateTime.now()
    val currentDayOfWeek = now.dayOfWeek
    return currentDayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
}
fun getCurrentMonth(): String {
    val now = LocalDateTime.now()
    val currentMonth = now.month
    return currentMonth.getDisplayName(TextStyle.FULL, Locale.getDefault())
}

fun getCurrentWeekNumber(): Int {
    val now = LocalDate.now()
    val weekFields = WeekFields.of(Locale.getDefault())
    return now.get(weekFields.weekOfWeekBasedYear())
}

fun getCurrentYear(): String {
    val now = LocalDateTime.now()
    return now.year.toString()
}






