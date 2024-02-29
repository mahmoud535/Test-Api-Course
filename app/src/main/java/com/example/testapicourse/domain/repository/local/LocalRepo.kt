package com.example.testapicourse.domain.repository.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapicourse.domain.model.User

interface LocalRepo {

    suspend fun insertOrUpdateUser(user: User)

    suspend fun insertOrUpdateUser(user:List<User>)

    suspend fun deleteUser(user: User)

    suspend fun getUser():List<User>

}