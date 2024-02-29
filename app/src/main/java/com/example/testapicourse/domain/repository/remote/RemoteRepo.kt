package com.example.testapicourse.domain.repository.remote

import com.example.testapicourse.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteRepo {

    suspend fun getApiUsers(): Response<List<User>>

    suspend fun getApiUser(id:Int): Response<User>

    suspend fun  getApiUserQuery(id: Int): Response<User>

    suspend fun addApiUser(user: User): Response<User>

    suspend fun updateApiUser(user: User, id: Int): Response<User>

    suspend fun deleteApiUser(id: Int)
}