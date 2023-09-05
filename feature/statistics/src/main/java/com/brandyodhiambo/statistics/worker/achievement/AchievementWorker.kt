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
package com.brandyodhiambo.statistics.worker.achievement

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.brandyodhiambo.common.domain.model.Achievement
import com.brandyodhiambo.common.domain.repository.AchievementRepository
import com.brandyodhiambo.common.domain.repository.GoalWaterIntakeRepository
import com.brandyodhiambo.common.domain.repository.LevelRepository
import com.brandyodhiambo.common.util.awaitValue
import com.brandyodhiambo.common.util.getCurrentDay
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class AchievementWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val achievementRepository: AchievementRepository,
    private val levelRepository: LevelRepository,
    private val goalWaterIntakeRepository: GoalWaterIntakeRepository
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return try {
            val amountTaken = levelRepository.getLevel().awaitValue()?.amountTaken ?: 1f
            val goalWaterIntake = goalWaterIntakeRepository.getGoalWaterIntake().awaitValue()?.waterIntake ?: 1f

            achievementRepository.insertAchievement(
                Achievement(
                    isAchieved = amountTaken.toInt() >= goalWaterIntake.toInt(),
                    day = getCurrentDay()
                )
            )
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
