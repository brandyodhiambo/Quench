package com.brandyodhiambo.statistics.worker.daily_worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.brandyodhiambo.common.domain.model.DailyStatistics
import com.brandyodhiambo.common.domain.repository.DailyStatisticsRepository
import com.brandyodhiambo.common.domain.repository.GoalWaterIntakeRepository
import com.brandyodhiambo.common.domain.repository.IdealWaterIntakeRepository
import com.brandyodhiambo.common.domain.repository.LevelRepository
import com.brandyodhiambo.common.domain.repository.ReminderTimeRepository
import com.brandyodhiambo.common.domain.repository.SelectedDrinkRepository
import com.brandyodhiambo.common.util.awaitValue
import com.brandyodhiambo.common.util.getCurrentDay
import com.brandyodhiambo.common.util.isEndOfDay
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.LocalDateTime

@HiltWorker
class DailyWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val levelRepository: LevelRepository,
    private val goalWaterIntakeRepository: GoalWaterIntakeRepository,
    private val idealWaterIntakeRepository: IdealWaterIntakeRepository,
    private val selectedDrinkRepository: SelectedDrinkRepository,
    private val reminderTimeRepository: ReminderTimeRepository,
    private val dailyStatisticsRepository: DailyStatisticsRepository,
) : CoroutineWorker(context, params) {

    companion object {
        const val DAILY_WORK_NAME = "com.brandyodhiambo.common.worker.daily_worker.DailyWorker"
        private const val TAG = "DailyWorker"
    }

    override suspend fun doWork(): Result {
        return try {
            val amountTaken = levelRepository.getLevel().awaitValue()?.amountTaken ?: 1f

            if (isEndOfDay(dateTime = LocalDateTime.now())) {
                dailyStatisticsRepository.insertDailyStatistics(
                    DailyStatistics(
                        amountTaken = amountTaken,
                        day = getCurrentDay(),
                    ),
                )
                // statisticsViewModel.emptyDBFromDailyData()
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
