package com.example.testapicourse.data.remote

import com.example.testapicourse.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {


    @GET("/mahmoud535/repo/users")
    suspend fun getApiUsers(): Response<List<User>>

    @GET("/mahmoud535/repo/users/{id}")
    suspend fun getApiUser(@Path("id")id:Int): Response<User>

    @GET("/mahmoud535/repo/users/")
    suspend fun  getApiUserQuery(@Query("id")id: Int): Response<User>

    @POST("/mahmoud535/repo/users")
    suspend fun addApiUser(@Body user:User):Response<User>

    //For Update
    @PUT("/mahmoud535/repo/users/{id}")
    suspend fun updateApiUser(@Body user: User, @Path("id") id: Int):Response<User>


    @DELETE("/mahmoud535/repo/users/{id}")
    suspend fun deleteApiUser(@Path("id") id: Int)
}