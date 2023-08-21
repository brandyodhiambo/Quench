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
package com.brandyodhiambo.statistics.worker.weeklyWorker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.brandyodhiambo.common.domain.model.WeeklyStatistics
import com.brandyodhiambo.common.domain.repository.DailyStatisticsRepository
import com.brandyodhiambo.common.domain.repository.WeeklyStatisticRepository
import com.brandyodhiambo.common.util.awaitValue
import com.brandyodhiambo.common.util.getCurrentWeekNumber
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

@HiltWorker
class WeeklyWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val dailyStatisticsRepository: DailyStatisticsRepository,
    private val weeklyStatisticRepository: WeeklyStatisticRepository
) : CoroutineWorker(context, params) {
    companion object {
        const val WEEKLY_WORK_NAME = "com.brandyodhiambo.common.worker.weekly_worker.WeeklyWorker"
        private const val TAG = "WeeklyWorker"
    }

    override suspend fun doWork(): Result {
        return try {
            val amountTaken = dailyStatisticsRepository.getDailyStatistics().awaitValue()
                ?.sumByDouble { it.amountTaken.toDouble() }
            val totalAmountTaken = amountTaken?.div(7) // 7 days in a week

            val lastDayOfWeek = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
            if (LocalDate.now() == lastDayOfWeek) {
                weeklyStatisticRepository.insertWeeklyStatistic(
                    WeeklyStatistics(
                        amountTaken = totalAmountTaken?.toFloat() ?: 0f,
                        week = getCurrentWeekNumber().toString()
                    )
                )
            }

            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
