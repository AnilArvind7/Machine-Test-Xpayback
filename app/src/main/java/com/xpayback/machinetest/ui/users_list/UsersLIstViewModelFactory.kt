package com.xpayback.machinetest.ui.users_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xpayback.machinetest.network.RetrofitInstance
import com.xpayback.machinetest.repository.AppRepository

@Suppress("UNCHECKED_CAST")
class UsersLIstViewModelFactory(): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = AppRepository(RetrofitInstance.getInstance())
        return if (modelClass.isAssignableFrom(UsersListViewModel::class.java)) {
            UsersListViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}