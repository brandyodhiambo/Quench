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
package com.brandyodhiambo.statistics.worker.monthlyWorker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.brandyodhiambo.common.domain.model.MonthlyStatistics
import com.brandyodhiambo.common.domain.repository.MonthlyStatisticsRepository
import com.brandyodhiambo.common.domain.repository.WeeklyStatisticRepository
import com.brandyodhiambo.common.util.awaitValue
import com.brandyodhiambo.common.util.getCurrentMonth
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class MonthlyWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val weeklyStatisticRepository: WeeklyStatisticRepository,
    private val monthlyStatisticsRepository: MonthlyStatisticsRepository,
) : CoroutineWorker(context, params) {

    companion object {
        const val MONTHLY_WORK_NAME =
            "com.brandyodhiambo.common.worker.monthly_worker.MonthlyWorker"
        private const val TAG = "MonthlyWorker"
    }

    override suspend fun doWork(): Result {
        return try {
            val amountTaken =
                weeklyStatisticRepository.getWeeklyStatistic().awaitValue()
                    ?.sumByDouble { it.amountTaken.toDouble() }
            val totalAmountTaken = amountTaken?.div(4) // 4 weeks in a month

            monthlyStatisticsRepository.insertMonthlyStatistics(
                MonthlyStatistics(
                    amountTaken = totalAmountTaken?.toFloat() ?: 0f,
                    month = getCurrentMonth(),
                ),
            )

            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
