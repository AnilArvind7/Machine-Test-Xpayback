package com.xpayback.machinetest.ui.splash_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel:ViewModel() {

    val timer = MutableLiveData<Boolean>().apply { value = false }

    init {
        navigateToUserScreen()
    }

    private fun navigateToUserScreen() = viewModelScope.launch {
        delay(3000L)
        timer.value = true
    }
}