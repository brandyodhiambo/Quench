package com.brandyodhiambo.common.domain.repository

import androidx.lifecycle.LiveData
import com.brandyodhiambo.common.domain.model.Level

interface LevelRepository {

    suspend fun insertLevel(level: Level)
    suspend fun updateLevel(level: Level)
    fun getLevel(): LiveData<Level?>
    suspend fun deleteLevel(level: Level)
    suspend fun deleteAllLevel()
}
