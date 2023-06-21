package com.buzzbites.testmvvmpro.network

import com.buzzbites.testmvvmpro.model.UserResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface RetrofitServices {


    @GET("users")
    suspend fun getUserList():Response<UserResponse>


    companion object{
        var retrofitServices: RetrofitServices? = null
        fun getInstance(): RetrofitServices{
            if(retrofitServices == null){

                val retrofit = Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofitServices = retrofit.create(RetrofitServices::class.java)
            }
            return retrofitServices!!
        }
    }

}