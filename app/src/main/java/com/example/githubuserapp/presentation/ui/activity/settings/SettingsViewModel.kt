package com.example.githubuserapp.presentation.ui.activity.settings

import androidx.lifecycle.*
import com.example.githubuserapp.domain.usecase.GetThemeDataStoreUseCase
import com.example.githubuserapp.domain.usecase.AddThemeDataStoreUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getThemeDataStoreUseCase: GetThemeDataStoreUseCase,
    private val  saveThemeDataStoreUseCase: AddThemeDataStoreUseCase
): ViewModel() {
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