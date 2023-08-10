package com.brandyodhiambo.statistics.worker.weekly_worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.brandyodhiambo.common.domain.model.WeeklyStatistics
import com.brandyodhiambo.common.domain.repository.DailyStatisticsRepository
import com.brandyodhiambo.common.domain.repository.WeeklyStatisticRepository
import com.brandyodhiambo.common.util.awaitValue
import com.brandyodhiambo.common.util.getCurrentWeekNumber
import com.brandyodhiambo.common.util.isEndOfWeek
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.LocalDate

@HiltWorker
class WeeklyWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val dailyStatisticsRepository: DailyStatisticsRepository,
    private val weeklyStatisticRepository: WeeklyStatisticRepository,
) : CoroutineWorker(context, params) {
    companion object {
        const val WEEKLY_WORK_NAME = "com.brandyodhiambo.common.worker.weekly_worker.WeeklyWorker"
        private const val TAG = "WeeklyWorker"
    }

    override suspend fun doWork(): Result {
        return try {
            val amountTaken = dailyStatisticsRepository.getDailyStatistics().awaitValue()?.sumByDouble { it.amountTaken.toDouble() }
            val totalAmountTaken = amountTaken?.div(7) // 7 days in a week

            if (isEndOfWeek(LocalDate.now())) {
                weeklyStatisticRepository.insertWeeklyStatistic(
                    WeeklyStatistics(
                        amountTaken = totalAmountTaken?.toFloat() ?: 0f,
                        week = getCurrentWeekNumber().toString(),
                    ),
                )
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
