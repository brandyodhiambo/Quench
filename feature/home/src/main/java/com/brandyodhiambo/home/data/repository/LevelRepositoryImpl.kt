package com.brandyodhiambo.home.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.brandyodhiambo.common.domain.model.Level
import com.brandyodhiambo.common.domain.repository.LevelRepository
import com.brandyodhiambo.dao.LevelDao
import com.brandyodhiambo.home.data.mapper.toLevel
import com.brandyodhiambo.home.data.mapper.toLevelEntity

class LevelRepositoryImpl(
    private val levelDao: LevelDao,
) : LevelRepository {
    override suspend fun insertLevel(level: Level) {
        levelDao.insertLevel(level.toLevelEntity())
    }

    override suspend fun updateLevel(level: Level) {
        val entityData = level.toLevelEntity()
        levelDao.updateLevel(
            id = entityData.id,
            amountTaken = entityData.amountTaken,
            waterTaken = entityData.waterTaken,
        )
    }

    override fun getLevel(): LiveData<Level?> {
        return Transformations.map(levelDao.getLevel()) { levelEntity ->
            levelEntity?.toLevel()
        }
    }

    override suspend fun deleteLevel(level: Level) {
        levelDao.deleteLevel(level.toLevelEntity())
    }

    override suspend fun deleteAllLevel() {
        levelDao.deleteAllLevel()
    }
}
