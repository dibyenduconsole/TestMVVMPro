package com.buzzbites.testmvvmpro.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buzzbites.testmvvmpro.model.Users
import com.buzzbites.testmvvmpro.repository.MainRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel constructor(private val mainRepository: MainRepository): ViewModel() {

    val errorMsg = MutableLiveData<String>()
    val userList  =MutableLiveData<List<Users>>()

    var job: Job? = null

    val loading = MutableLiveData<Boolean>()

    //
    fun getAllUser(){
        loading.postValue(true)
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getAllUsers()
            withContext(Dispatchers.Main){
                if (response.isSuccessful && response.body() != null){
                    Log.d(">>userlist","-Sucess")
                    userList.postValue(response.body()!!.users)
                    loading.postValue(false)
                }else{
                    // Error Exception
                    // Need to handle Exception by Coroutine Exception Handler
                    Log.d(">>userlist","-Error")
                    loading.postValue(false)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        loading.postValue(false)
        job?.cancel()
    }

}