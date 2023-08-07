package com.brandyodhiambo.statistics.worker.daily_worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.brandyodhiambo.common.domain.model.DailyStatistics
import com.brandyodhiambo.common.util.getCurrentDay
import com.brandyodhiambo.common.util.isEndOfDay
import com.brandyodhiambo.statistics.presentation.StatisticsViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.LocalDateTime

@HiltWorker
class DailyWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val statisticsViewModel: StatisticsViewModel,
) : CoroutineWorker(context, params) {

    private val amountTaken = statisticsViewModel.levelFromDB.value?.amountTaken
    companion object {
        const val DAILY_WORK_NAME = "com.brandyodhiambo.common.worker.daily_worker.DailyWorker"
        private const val TAG = "DailyWorker"
    }

    override suspend fun doWork(): Result {
        return try {
            if (isEndOfDay(dateTime = LocalDateTime.now())) {
                statisticsViewModel.insertDailyStatistic(
                    DailyStatistics(
                        amountTaken = amountTaken ?: 0f,
                        day = getCurrentDay(),
                    ),
                )
                statisticsViewModel.emptyDBFromDailyData()
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
