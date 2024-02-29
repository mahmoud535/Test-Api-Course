package com.example.testapicourse.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapicourse.domain.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUser(user:User)

    // Updated function to handle a list of users
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUser(user:List<User>)

    @Delete
    suspend fun deleteUser(user:User)

    @Query("select * from user_table")
    suspend fun getUser():List<User>

}