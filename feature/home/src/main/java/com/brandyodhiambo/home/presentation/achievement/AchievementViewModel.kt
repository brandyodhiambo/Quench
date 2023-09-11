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
