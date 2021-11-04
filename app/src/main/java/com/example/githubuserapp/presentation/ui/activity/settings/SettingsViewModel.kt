package com.example.githubuserapp.presentation.ui.activity.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.githubuserapp.domain.usecase.AddThemeDataStoreUseCase
import com.example.githubuserapp.domain.usecase.GetThemeDataStoreUseCase
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getThemeDataStoreUseCase: GetThemeDataStoreUseCase,
    private val saveThemeDataStoreUseCase: AddThemeDataStoreUseCase
) : ViewModel() {
    //init state
    fun getThemeSettings(): LiveData<Boolean> {
        return getThemeDataStoreUseCase.execute().asLiveData()
    }

    fun saveThemeSettings(isDarkModeActive: Boolean) {
        viewModelScope.launch() {
            saveThemeDataStoreUseCase.execute(isDarkModeActive)
        }
    }
}
