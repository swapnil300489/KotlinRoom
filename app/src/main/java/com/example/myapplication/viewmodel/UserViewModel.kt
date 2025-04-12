package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.entity.User
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    private val userDao = AppDatabase.getDatabase(application).userDao()

    val users: LiveData<List<User>> = userDao.getAllUsers()

    fun addUser(name: String){
        viewModelScope.launch {
            userDao.insertUser(User(name = name))
        }
    }

    fun deleteUser(user: String){
        viewModelScope.launch {
            userDao.deleteUser(User(name = user))
        }
    }

}