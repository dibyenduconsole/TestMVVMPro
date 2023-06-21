package com.buzzbites.testmvvmpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.buzzbites.testmvvmpro.adapter.UserListAdapter
import com.buzzbites.testmvvmpro.databinding.ActivityMainBinding
import com.buzzbites.testmvvmpro.network.RetrofitServices
import com.buzzbites.testmvvmpro.repository.MainRepository
import com.buzzbites.testmvvmpro.viewmodel.MainViewModel
import com.buzzbites.testmvvmpro.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var adapter: UserListAdapter
    lateinit var mainViewlModel: MainViewModel

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitServices = RetrofitServices.getInstance()
        val mainRepository = MainRepository(retrofitServices)

        adapter = UserListAdapter()
        binding.rcvUserList.adapter = adapter

        mainViewlModel = ViewModelProvider(this, MainViewModelFactory(mainRepository)).get(MainViewModel::class.java)//ViewModelProvider(this, MainViewModelFactory(mainRepository)).get


        mainViewlModel.userList.observe(this) { userList ->
            // Set list to recyclerview adapter
            Log.d(">>userlist","-"+userList.size)
            adapter.setUserList(userList)
        }

        mainViewlModel.loading.observe(this){
            if (it)
                binding.progress.visibility = View.VISIBLE
            else
                binding.progress.visibility = View.GONE
        }


        // Check internet connection before init API call method
        mainViewlModel.getAllUser()

    }
}