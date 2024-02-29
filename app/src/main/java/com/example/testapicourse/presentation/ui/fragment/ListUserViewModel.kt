package com.example.testapicourse.presentation.ui.fragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapicourse.data.local.UserDatabase
import com.example.testapicourse.data.remote.RetroBuilder
import com.example.testapicourse.data.remote.ServiceApi
import com.example.testapicourse.data.repositoryimp.local.LocalRepositoryImp
import com.example.testapicourse.data.repositoryimp.remote.RemoteRepositoryImp
import com.example.testapicourse.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListUserViewModel @Inject constructor(private val remoteRepositoryImp: RemoteRepositoryImp,private val localRepositoryImp: LocalRepositoryImp ) : ViewModel() {

//    private var localRepositoryImp: LocalRepositoryImp
//    private val remoteRepositoryImp: RemoteRepositoryImp

    private val userMutableLiveData = MutableLiveData<List<User>>()
    val userLiveData: LiveData<List<User>> get() = userMutableLiveData

    private val userApiMutableStateFlow = MutableStateFlow<List<User>>(emptyList())
    val userApiStateFlow: StateFlow<List<User>> get() = userApiMutableStateFlow

    private val adduserApiMutableStateFlow = MutableStateFlow<User?>(null)
    val adduserApiStateFlow: StateFlow<User?> get() = adduserApiMutableStateFlow

//    init {
//        var db: UserDatabase = UserDatabase.getInstance(app)
//        localRepositoryImp = LocalRepositoryImp(db)
//
//        val serviceInstance = RetroBuilder.getRetroBuilder().create(ServiceApi::class.java)
//        remoteRepositoryImp = RemoteRepositoryImp(serviceInstance)
//    }

    //Room
    fun getAllUsers() = viewModelScope.launch {
        userMutableLiveData.postValue(localRepositoryImp.getUser())
    }

    fun addUser(user: User) = viewModelScope.launch {
        localRepositoryImp.insertOrUpdateUser(user)
    }


    fun deleteUser(user: User) = viewModelScope.launch {
        localRepositoryImp.deleteUser(user)
    }

    //Retrofit
    fun getUserApi() = viewModelScope.launch {
        var result = remoteRepositoryImp.getApiUsers()

        if (result.isSuccessful) {
            if (result.body() != null) {
                userApiMutableStateFlow.value = result.body()!!
            }
        } else {
            Log.i("errMsg", result.message())
        }
    }

    fun addUserApi(user: User) = viewModelScope.launch {
        var result = remoteRepositoryImp.addApiUser(user)

        if (result.isSuccessful) {
            if (result.body() != null) {
                adduserApiMutableStateFlow.value = result.body()
            }
        } else {
            Log.i("errMsg", result.message())
        }
    }

    fun deleteApiUser(id: Int) = viewModelScope.launch {
        remoteRepositoryImp.deleteApiUser(id)
    }

}