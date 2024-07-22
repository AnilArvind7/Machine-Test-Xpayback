package com.xpayback.machinetest.ui.users_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xpayback.machinetest.models.network_result.NetworkResult
import com.xpayback.machinetest.models.users.User
import com.xpayback.machinetest.models.users.UsersListResponse
import com.xpayback.machinetest.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersListViewModel(private val repository: AppRepository) :ViewModel() {

    var currentPage = 1
    private var skip = 0
    var perPage = 10
    var isLockPaging = false
    var totalPage = 0
    private val _usersResponseMutableLiveDate = MutableLiveData<NetworkResult<UsersListResponse>>()
    val userResponseLiveData: LiveData<NetworkResult<UsersListResponse>> = _usersResponseMutableLiveDate
    var list = mutableListOf<User>()

    fun fetchUsers() = viewModelScope.launch(Dispatchers.IO){
        try {
            skip =  (currentPage - 1) * perPage
            _usersResponseMutableLiveDate.postValue(NetworkResult.Loading())
            val response = repository.fetchUsers(limit = perPage, skip = skip)
            if (response.isSuccessful){
                _usersResponseMutableLiveDate.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _usersResponseMutableLiveDate.postValue( NetworkResult.Error(response.errorBody().toString()))
            }
        }catch (e: Exception){
            _usersResponseMutableLiveDate.postValue( NetworkResult.Error(e.message.toString()))
        }
    }
}