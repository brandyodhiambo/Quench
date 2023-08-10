package com.brandyodhiambo.statistics.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.brandyodhiambo.common.domain.model.Achievement
import com.brandyodhiambo.common.domain.repository.AchievementRepository
import com.brandyodhiambo.dao.AchievementDao
import com.brandyodhiambo.statistics.data.mapper.toAchievement
import com.brandyodhiambo.statistics.data.mapper.toAchievementsEntity

class AchievementRepositoryImpl(
    private val achievementDao: AchievementDao,
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
