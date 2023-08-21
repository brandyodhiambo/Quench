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
package com.brandyodhiambo.common.util

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.TextStyle
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

fun isEndOfDay(dateTime: LocalDateTime): Boolean {
    val currentHour = dateTime.hour
    val currentMinute = dateTime.minute

    val endOfDayHour = 23
    val endOfDayMinute = 59

    return currentHour >= endOfDayHour && currentMinute >= endOfDayMinute
}

suspend fun <T> LiveData<T>.awaitValue(): T = withContext(Dispatchers.Default) {
    val flow = MutableSharedFlow<T>(replay = 1)
    val observer = androidx.lifecycle.Observer<T> {
        it?.let { value -> flow.tryEmit(value) }
    }

    withContext(Dispatchers.Main) {
        observeForever(observer)
    }
    try {
        flow.first { value ->
            withContext(Dispatchers.Main) {
                removeObserver(observer)
            }
            true
        }
    } finally {
        withContext(Dispatchers.Main) {
            removeObserver(observer)
        }
    }
}
