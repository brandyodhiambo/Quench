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
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.brandyodhiambo.statistics.worker.dailyWorker.DailyWorker
import com.brandyodhiambo.statistics.worker.dailyWorker.DailyWorker.Companion.DAILY_WORK_NAME
import com.brandyodhiambo.statistics.worker.monthlyWorker.MonthlyWorker
import com.brandyodhiambo.statistics.worker.monthlyWorker.MonthlyWorker.Companion.MONTHLY_WORK_NAME
import com.brandyodhiambo.statistics.worker.weeklyWorker.WeeklyWorker
import com.brandyodhiambo.statistics.worker.weeklyWorker.WeeklyWorker.Companion.WEEKLY_WORK_NAME
import java.util.concurrent.TimeUnit

fun startDailyOnetimeWorkRequest(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
        .build()

    val dailyWorkRequest = OneTimeWorkRequestBuilder<DailyWorker>()
        .setConstraints(constraints)

        .build()

    WorkManager.getInstance(context).enqueue(dailyWorkRequest)
}

fun startDailyPeriodicWorkRequest(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
        .setRequiresBatteryNotLow(true)
        .setRequiresCharging(true)
        .build()

    val workRequest = PeriodicWorkRequestBuilder<DailyWorker>(
        1,
        TimeUnit.DAYS
    )
        .setConstraints(constraints)
        .setBackoffCriteria(
            BackoffPolicy.LINEAR,
            PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS,
            TimeUnit.MILLISECONDS
        )
        .build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        DAILY_WORK_NAME,
        ExistingPeriodicWorkPolicy.REPLACE,
        workRequest
    )
}

// weekly requests
fun startWeeklyOnetimeWorkRequest(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
        .build()

    val weeklyWorkRequest = OneTimeWorkRequestBuilder<WeeklyWorker>()
        .setConstraints(constraints)
        .build()

    WorkManager.getInstance(context).enqueue(weeklyWorkRequest)
}

fun startWeeklyPeriodicWorkRequest(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
        .setRequiresBatteryNotLow(true)
        .setRequiresCharging(true)
        .build()

    val workRequest = PeriodicWorkRequestBuilder<WeeklyWorker>(
        1,
        TimeUnit.DAYS
    )
        .setConstraints(constraints)
        .setBackoffCriteria(
            BackoffPolicy.LINEAR,
            PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS,
            TimeUnit.MILLISECONDS
        )
        .build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        WEEKLY_WORK_NAME,
        ExistingPeriodicWorkPolicy.REPLACE,
        workRequest
    )
}

// monthly requests
fun startMonthlyOnetimeWorkRequest(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
        .build()

    val monthlyWorkRequest = OneTimeWorkRequestBuilder<MonthlyWorker>()
        .setConstraints(constraints)
        .build()

    WorkManager.getInstance(context).enqueue(monthlyWorkRequest)
}

fun startMonthlyPeriodicWorkRequest(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
        .setRequiresBatteryNotLow(true)
        .setRequiresCharging(true)
        .build()

    val workRequest = PeriodicWorkRequestBuilder<MonthlyWorker>(
        1,
        TimeUnit.DAYS
    )
        .setConstraints(constraints)
        .setBackoffCriteria(
            BackoffPolicy.LINEAR,
            PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS,
            TimeUnit.MILLISECONDS
        )
        .build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        MONTHLY_WORK_NAME,
        ExistingPeriodicWorkPolicy.REPLACE,
        workRequest
    )
}
