package com.xpayback.machinetest.ui.user_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xpayback.machinetest.network.RetrofitInstance
import com.xpayback.machinetest.repository.AppRepository

@Suppress("UNCHECKED_CAST")
class UsersDetailsViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = AppRepository(RetrofitInstance.getInstance())
        return if (modelClass.isAssignableFrom(UserDetailViewModel::class.java)) {
            UserDetailViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}