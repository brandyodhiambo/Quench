package com.brandyodhiambo.statistics.worker.monthly_worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.brandyodhiambo.common.domain.model.MonthlyStatistics
import com.brandyodhiambo.common.domain.repository.MonthlyStatisticsRepository
import com.brandyodhiambo.common.domain.repository.WeeklyStatisticRepository
import com.brandyodhiambo.common.util.awaitValue
import com.brandyodhiambo.common.util.getCurrentMonth
import com.brandyodhiambo.common.util.isEndOfMonth
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.LocalDate

@HiltWorker
class MonthlyWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val weeklyStatisticRepository: WeeklyStatisticRepository,
    private val monthlyStatisticsRepository: MonthlyStatisticsRepository,
) : CoroutineWorker(context, params) {

    companion object {
        const val MONTHLY_WORK_NAME = "com.brandyodhiambo.common.worker.monthly_worker.MonthlyWorker"
        private const val TAG = "MonthlyWorker"
    }

    override suspend fun doWork(): Result {
        return try {
            val amountTaken =
                weeklyStatisticRepository.getWeeklyStatistic().awaitValue()?.sumByDouble { it.amountTaken.toDouble() }
            val totalAmountTaken = amountTaken?.div(4) // 4 weeks in a month

            if (isEndOfMonth(LocalDate.now())) {
                monthlyStatisticsRepository.insertMonthlyStatistics(
                    MonthlyStatistics(
                        amountTaken = totalAmountTaken?.toFloat() ?: 0f,
                        month = getCurrentMonth(),
                    ),
                )
            }

            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
