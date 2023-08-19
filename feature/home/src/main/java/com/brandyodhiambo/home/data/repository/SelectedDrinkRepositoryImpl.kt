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
import com.brandyodhiambo.common.domain.model.SelectedDrink
import com.brandyodhiambo.common.domain.repository.SelectedDrinkRepository
import com.brandyodhiambo.dao.SelectedDrinkDao
import com.brandyodhiambo.home.data.mapper.toSelectedDrink
import com.brandyodhiambo.home.data.mapper.toSelectedDrinkEntity

class SelectedDrinkRepositoryImpl(
    private val selectedDrinkDao: SelectedDrinkDao
) : SelectedDrinkRepository {
    override fun getSelectedDrink(): LiveData<List<SelectedDrink>> {
        return Transformations.map(selectedDrinkDao.getSelectedDrink()) { selectedDrinkEntity ->
            selectedDrinkEntity.map { it.toSelectedDrink() }
        }
    }

    override suspend fun insertSelectedDrink(selectedDrink: SelectedDrink) {
        selectedDrinkDao.insertSelectedDrink(selectedDrink.toSelectedDrinkEntity())
    }

    override suspend fun deleteAllSelectedDrinks() {
        selectedDrinkDao.deleteAllSelectedDrink()
    }

    override suspend fun deleteOneSelectedDrink(id: Int) {
        selectedDrinkDao.deleteSelectedDrink(id)
    }
}
