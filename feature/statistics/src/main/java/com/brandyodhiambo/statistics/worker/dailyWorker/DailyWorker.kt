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
package com.brandyodhiambo.statistics.worker.dailyWorker

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
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

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

    override suspend fun doWork(): Result {
        return try {
            val amountTaken = levelRepository.getLevel().awaitValue()?.amountTaken ?: 1f
            dailyStatisticsRepository.insertDailyStatistics(
                DailyStatistics(
                    amountTaken = amountTaken,
                    day = getCurrentDay(),
                ),
            )
            goalWaterIntakeRepository.deleteAllGoalWaterIntakes()
            idealWaterIntakeRepository.deleteAllIdealWaterIntakes()
            selectedDrinkRepository.deleteAllSelectedDrinks()
            reminderTimeRepository.dellAllReminderTimes()
            levelRepository.deleteAllLevel()

            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
