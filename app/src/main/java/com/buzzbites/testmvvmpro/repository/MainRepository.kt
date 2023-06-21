package com.buzzbites.testmvvmpro.repository

import com.buzzbites.testmvvmpro.network.RetrofitServices

class MainRepository constructor(private val retrofitServices: RetrofitServices) {
    suspend fun getAllUsers() = retrofitServices.getUserList()
}