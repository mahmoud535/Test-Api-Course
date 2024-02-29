package com.example.testapicourse.data.repositoryimp.local

import com.example.testapicourse.data.local.UserDatabase
import com.example.testapicourse.domain.model.User
import com.example.testapicourse.domain.repository.local.LocalRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalRepositoryImp @Inject constructor(private val db: UserDatabase):LocalRepo {

    override suspend fun insertOrUpdateUser(user: User)  = withContext(Dispatchers.IO){
        db.userDao().insertOrUpdateUser(user)
    }

    override suspend fun insertOrUpdateUser(user: List<User>) = withContext(Dispatchers.IO){
        db.userDao().insertOrUpdateUser(user)
    }

    override suspend fun deleteUser(user: User)  = withContext(Dispatchers.IO){
        db.userDao().deleteUser(user)
    }

    override suspend fun getUser(): List<User> = withContext(Dispatchers.IO){
        db.userDao().getUser()
    }
}