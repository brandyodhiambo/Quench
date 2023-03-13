package com.brandyodhiambo.quench.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<MainActivityUiState>(MainActivityUiState())
    val uiState: StateFlow<MainActivityUiState> = _uiState

    init {
        viewModelScope.launch {
            delay(3000)
            _uiState.value = MainActivityUiState(isLoading = false)
            _uiState.value = MainActivityUiState(isSuccessful = true)
        }
    }
}


data class MainActivityUiState(
    val isLoading: Boolean = true,
    val Error: String = "",
    val isSuccessful: Boolean = false,
)

