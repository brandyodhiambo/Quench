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
import androidx.lifecycle.map
import com.brandyodhiambo.common.domain.model.MonthlyStatistics
import com.brandyodhiambo.common.domain.repository.MonthlyStatisticsRepository
import com.brandyodhiambo.dao.MonthlyStatisticsDao
import com.brandyodhiambo.statistics.data.mapper.toMonthlyStatistics
import com.brandyodhiambo.statistics.data.mapper.toMonthlyStatisticsEntity

class MonthlyStatisticsRepositoryImpl(
    private val monthlyStatisticalDao: MonthlyStatisticsDao
) : MonthlyStatisticsRepository {
    override suspend fun insertMonthlyStatistics(monthlyStatistics: MonthlyStatistics) {
        monthlyStatisticalDao.insertMonthlyStatistic(monthlyStatistics.toMonthlyStatisticsEntity())
    }

    override suspend fun updateMonthlyStatistics(monthlyStatistics: MonthlyStatistics) {
        TODO("Not yet implemented")
    }

    override fun getMonthlyStatistics(): LiveData<List<MonthlyStatistics>?> {
        return monthlyStatisticalDao.getMonthlyStatistics().map { monthlyStatisticsEntity ->
            monthlyStatisticsEntity?.map { it.toMonthlyStatistics() }
        }
    }

    override suspend fun deleteMonthlyStatistics(monthlyStatistics: MonthlyStatistics) {
        monthlyStatisticalDao.deleteMonthlyStatistic(monthlyStatistics.toMonthlyStatisticsEntity())
    }

    override suspend fun deleteAllMonthlyStatistics() {
        monthlyStatisticalDao.deleteAllMonthlyStatistics()
    }
}
