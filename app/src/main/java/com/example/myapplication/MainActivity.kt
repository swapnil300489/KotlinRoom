package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.UserAdapter
import com.example.myapplication.entity.User
import com.example.myapplication.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserAdapter
    private val userList = mutableListOf<User>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etName)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val rvUsers = findViewById<RecyclerView>(R.id.rvUsers)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]


        adapter = UserAdapter(userList){
            user->
            AlertDialog.Builder(this)
                .setTitle("Delete User")
                .setMessage("Are you sure you want to delete ${user.name}?")
                .setPositiveButton("Yes") { _, _ -> viewModel.deleteUser(user.name) }
                .setNegativeButton("No", null)
                .show()


        }
        rvUsers.layoutManager = LinearLayoutManager(this)
        rvUsers.adapter = adapter


        viewModel.users.observe(this){
            userList.clear()
            userList.addAll(it)
            adapter.notifyDataSetChanged()
        }

        btnAdd.setOnClickListener {
            val uname = etName.text.toString().trim()
            if(uname.isNotEmpty()){
                viewModel.addUser(uname)
                etName.text.clear()
            }

        }
    }
}