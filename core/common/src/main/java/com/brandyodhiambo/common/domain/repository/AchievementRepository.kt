package com.brandyodhiambo.common.domain.repository

import androidx.lifecycle.LiveData
import com.brandyodhiambo.common.domain.model.Achievement

interface AchievementRepository {

    suspend fun insertAchievement(achievement: Achievement)

    suspend fun updateAchievement(achievement: Achievement)

    suspend fun deleteAchievement(achievement: Achievement)

    suspend fun deleteAllAchievement()

    fun getAchievement(): LiveData<List<Achievement>?>
}
