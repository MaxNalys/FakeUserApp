package com.example.fakeuserapp.data.remote

import com.example.fakeuserapp.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {
    @GET("api/")
    suspend fun getUser(): Response<UserResponse>
}

data class UserResponse(val results: List<User>)
