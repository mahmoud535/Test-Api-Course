package com.example.testapicourse.data.repositoryimp.remote

import com.example.testapicourse.data.remote.ServiceApi
import com.example.testapicourse.domain.model.User
import com.example.testapicourse.domain.repository.remote.RemoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RemoteRepositoryImp @Inject constructor(private val api: ServiceApi):RemoteRepo {

    override suspend fun getApiUsers() = withContext(Dispatchers.IO) {
        api.getApiUsers()
    }

    override suspend fun getApiUser(id: Int)= withContext(Dispatchers.IO) {
        api.getApiUser(id)
    }

    override suspend fun getApiUserQuery(id: Int)= withContext(Dispatchers.IO) {
        api.getApiUserQuery(id)
    }

    override suspend fun addApiUser(user: User)= withContext(Dispatchers.IO) {
        api.addApiUser(user)
    }

    override suspend fun updateApiUser(user: User, id: Int)= withContext(Dispatchers.IO) {
        api.updateApiUser(user, id)
    }

    override suspend fun deleteApiUser(id: Int) {
        api.deleteApiUser(id)
    }
}