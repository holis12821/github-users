package com.example.githubuserapp.presentation.ui.activity.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.githubuserapp.domain.usecase.GetThemeDataStoreUseCase

class SplashScreenViewModel(
    private val getThemeDataStoreUseCase: GetThemeDataStoreUseCase
): ViewModel() {
    //init state
    fun getThemeSettings(): LiveData<Boolean> {
        return getThemeDataStoreUseCase.execute().asLiveData()
    }
}