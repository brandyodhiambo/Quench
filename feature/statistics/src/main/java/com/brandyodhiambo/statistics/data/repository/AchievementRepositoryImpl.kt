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
package com.brandyodhiambo.statistics.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.brandyodhiambo.common.domain.model.Achievement
import com.brandyodhiambo.common.domain.repository.AchievementRepository
import com.brandyodhiambo.dao.AchievementDao
import com.brandyodhiambo.statistics.data.mapper.toAchievement
import com.brandyodhiambo.statistics.data.mapper.toAchievementsEntity

class AchievementRepositoryImpl(
    private val achievementDao: AchievementDao
) : AchievementRepository {
    override suspend fun insertAchievement(achievement: Achievement) {
        achievementDao.insertAchievement(achievement.toAchievementsEntity())
    }

    override suspend fun updateAchievement(achievement: Achievement) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAchievement(achievement: Achievement) {
        achievementDao.deleteAchievement(id = achievement.toAchievementsEntity().id)
    }

    override suspend fun deleteAllAchievement() {
        achievementDao.deleteAllAchievement()
    }

    override fun getAchievement(): LiveData<List<Achievement>?> {
        return Transformations.map(achievementDao.getAchievement()) { achievementEntity ->
            achievementEntity?.map { it.toAchievement() }
        }
    }
}
