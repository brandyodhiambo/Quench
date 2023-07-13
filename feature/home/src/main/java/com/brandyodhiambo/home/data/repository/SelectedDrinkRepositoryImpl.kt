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
):SelectedDrinkRepository {
    override fun getSelectedDrink(): LiveData<List<SelectedDrink>> {
        return Transformations.map(selectedDrinkDao.getSelectedDrink()){ selectedDrinkEntity ->
            selectedDrinkEntity.map { it.toSelectedDrink() }
        }
    }

    override suspend  fun insertSelectedDrink(selectedDrink: SelectedDrink) {
       selectedDrinkDao.insertSelectedDrink(selectedDrink.toSelectedDrinkEntity())
    }

    override suspend fun deleteAllSelectedDrinks() {
        selectedDrinkDao.deleteAllSelectedDrink()
    }

    override suspend fun deleteOneSelectedDrink(id: Int) {
        selectedDrinkDao.deleteSelectedDrink(id)
    }
}