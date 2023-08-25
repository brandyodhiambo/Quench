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
package com.brandyodhiambo.home.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.brandyodhiambo.common.domain.model.Level
import com.brandyodhiambo.common.domain.repository.LevelRepository
import com.brandyodhiambo.dao.LevelDao
import com.brandyodhiambo.home.data.mapper.toLevel
import com.brandyodhiambo.home.data.mapper.toLevelEntity

class LevelRepositoryImpl(
    private val levelDao: LevelDao
) : LevelRepository {
    override suspend fun insertLevel(level: Level) {
        levelDao.insertLevel(level.toLevelEntity())
    }

    override suspend fun updateLevel(level: Level) {
        val entityData = level.toLevelEntity()
        levelDao.updateLevel(
            id = entityData.id,
            amountTaken = entityData.amountTaken,
            waterTaken = entityData.waterTaken
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
