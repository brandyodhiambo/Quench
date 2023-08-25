package com.brandyodhiambo.home.presentation.achievement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandyodhiambo.common.domain.model.Achievement
import com.brandyodhiambo.common.domain.repository.AchievementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AchievementViewModel @Inject constructor(
    private val achievementRepository: AchievementRepository
) : ViewModel() {

    val isAchieved = achievementRepository.getAchievement()

    fun insertIsAchieved(achievement: Achievement) {
        viewModelScope.launch {
            achievementRepository.insertAchievement(achievement)
        }
    }

    fun deleteAchievement(achievement: Achievement) {
        viewModelScope.launch {
            achievementRepository.deleteAchievement(achievement)
        }
    }

    fun deleteAllAchievement() {
        viewModelScope.launch {
            achievementRepository.deleteAllAchievement()
        }
    }
}
