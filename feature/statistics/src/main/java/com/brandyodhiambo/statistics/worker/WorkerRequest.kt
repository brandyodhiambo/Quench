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
package com.brandyodhiambo.statistics.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.brandyodhiambo.statistics.worker.achievement.AchievementWorker
import com.brandyodhiambo.statistics.worker.dailyWorker.DailyWorker
import com.brandyodhiambo.statistics.worker.monthlyWorker.MonthlyWorker
import com.brandyodhiambo.statistics.worker.weeklyWorker.WeeklyWorker
import java.util.Calendar
import java.util.concurrent.TimeUnit

fun startDailyOnetimeWorkRequest(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
        .build()

    val currentDate = Calendar.getInstance()
    val midnight = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 59)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    if (currentDate.after(midnight)) {
        midnight.add(Calendar.DAY_OF_MONTH, 1)
    }

    val timeDiff = midnight.timeInMillis - currentDate.timeInMillis

    val dailyWorkRequest = OneTimeWorkRequestBuilder<DailyWorker>()
        .setConstraints(constraints)
        .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
        .build()

    WorkManager.getInstance(context).enqueue(dailyWorkRequest)
}

// weekly requests
fun startWeeklyOnetimeWorkRequest(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
        .build()

    val currentDate = Calendar.getInstance()

    val endOfWeek = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 59)
        set(Calendar.SECOND, 59)
        set(Calendar.MILLISECOND, 999)
        add(Calendar.WEEK_OF_YEAR, 1) // Move to the next week
    }

    val timeDiff = endOfWeek.timeInMillis - currentDate.timeInMillis

    val weeklyWorkRequest = OneTimeWorkRequestBuilder<WeeklyWorker>()
        .setConstraints(constraints)
        .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
        .build()

    WorkManager.getInstance(context).enqueue(weeklyWorkRequest)
}

// monthly requests
fun startMonthlyOnetimeWorkRequest(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
        .build()

    val currentDate = Calendar.getInstance()
    val endOfMonth = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_MONTH, getActualMaximum(Calendar.DAY_OF_MONTH))
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 59)
        set(Calendar.SECOND, 59)
        set(Calendar.MILLISECOND, 999)
    }

    if (currentDate.after(endOfMonth)) {
        endOfMonth.add(Calendar.MONTH, 1)
    }

    val timeDiff = endOfMonth.timeInMillis - currentDate.timeInMillis

    val endOfMonthWorkRequest = OneTimeWorkRequestBuilder<MonthlyWorker>()
        .setConstraints(constraints)
        .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
        .build()

    WorkManager.getInstance(context).enqueue(endOfMonthWorkRequest)
}

// achievement worker request
fun startAchievementOnetimeWorkRequest(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
        .build()

    val currentDate = Calendar.getInstance()
    val midnight = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 59)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    if (currentDate.after(midnight)) {
        midnight.add(Calendar.DAY_OF_MONTH, 1)
    }

    val timeDiff = midnight.timeInMillis - currentDate.timeInMillis

    val achievementWorkRequest = OneTimeWorkRequestBuilder<AchievementWorker>()
        .setConstraints(constraints)
        .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
        .build()

    WorkManager.getInstance(context).enqueue(achievementWorkRequest)
}
