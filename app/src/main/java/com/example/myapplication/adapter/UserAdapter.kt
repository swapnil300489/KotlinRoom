package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.R
import com.example.myapplication.entity.User

class UserAdapter(private val userList: List<User>,
                  private val onItemClick: (User) -> Unit):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

        var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        context = parent.context
        return UserViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.user_details,parent, false)
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.textView.text = userList[position].name
    }


   inner class UserViewHolder(itemView: View): ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.id_userName)

        init {
            itemView.setOnClickListener{
                onItemClick(userList[adapterPosition])
            }
        }
    }
}