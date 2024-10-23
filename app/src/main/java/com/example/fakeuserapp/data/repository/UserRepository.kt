package com.example.fakeuserapp.data.repository

import com.example.fakeuserapp.data.remote.RetrofitInstance
import com.example.fakeuserapp.data.model.User

class UserRepository {
    suspend fun getUser(): User? {
        val response = RetrofitInstance.api.getUser()
        return if (response.isSuccessful) response.body()?.results?.firstOrNull() else null
    }
}
