package com.xpayback.machinetest.ui.user_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xpayback.machinetest.models.network_result.NetworkResult
import com.xpayback.machinetest.models.user_details.UserDetailsResponse
import com.xpayback.machinetest.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDetailViewModel(val repository: AppRepository) :ViewModel() {

    private val _usersDetailsResponseMutableLiveDate = MutableLiveData<NetworkResult<UserDetailsResponse>>()
    val userDetailsResponseLiveData: LiveData<NetworkResult<UserDetailsResponse>> = _usersDetailsResponseMutableLiveDate

    fun fetchUserDetails(id:Int) = viewModelScope.launch(Dispatchers.IO){
        try {
            _usersDetailsResponseMutableLiveDate.postValue(NetworkResult.Loading())
            val response = repository.fetchUserDetails(id)
            if (response.isSuccessful){
                _usersDetailsResponseMutableLiveDate.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _usersDetailsResponseMutableLiveDate.postValue( NetworkResult.Error(response.errorBody().toString()))
            }
        }catch (e: Exception){
            _usersDetailsResponseMutableLiveDate.postValue( NetworkResult.Error(e.message.toString()))
        }
    }

}