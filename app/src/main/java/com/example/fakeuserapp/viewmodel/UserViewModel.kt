package com.example.fakeuserapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakeuserapp.data.model.User
import com.example.fakeuserapp.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    val user = MutableLiveData<User?>()

    init {
        refreshUser()
    }

    fun refreshUser() {
        viewModelScope.launch {
            val newUser = repository.getUser()
            user.value = newUser
        }
    }
}
