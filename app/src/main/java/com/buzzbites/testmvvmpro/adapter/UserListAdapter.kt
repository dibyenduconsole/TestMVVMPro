package com.buzzbites.testmvvmpro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.buzzbites.testmvvmpro.R
import com.buzzbites.testmvvmpro.model.Users

class UserListAdapter(): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private var userList = listOf<Users>()

    fun setUserList(users: List<Users>){
        userList = users
        notifyDataSetChanged()
    }

    class ViewHolder (ItemView: View): RecyclerView.ViewHolder(ItemView){

        val tvUserName = itemView.findViewById<TextView>(R.id.tvUserName)
        val tvEmail = itemView.findViewById<TextView>(R.id.tvEmail)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rcv_userlist, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user = userList.get(position)
        holder.tvUserName.setText(""+user.firstName+ " " +user.lastName)
        holder.tvEmail.setText(""+user.email)
    }
}