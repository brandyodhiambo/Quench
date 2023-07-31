package com.brandyodhiambo.common.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
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

fun formatTime(inputTime: String): String {
    val inputFormat = SimpleDateFormat("HH:mm.SSSSSSSSS", Locale.getDefault())
    val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    try {
        val parsedTime = inputFormat.parse(inputTime)
        return outputFormat.format(parsedTime)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return ""
}


