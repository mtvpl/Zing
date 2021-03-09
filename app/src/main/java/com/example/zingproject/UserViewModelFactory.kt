package com.example.zingproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zingproject.database.User
import com.example.zingproject.repository.UserRepository
import java.lang.IllegalArgumentException

class UserViewModelFactory(private val userRepository: UserRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
         if (modelClass.isAssignableFrom(UserViewModel::class.java)){
             return UserViewModel(userRepository) as T
         }
        throw IllegalArgumentException("Unknown View Model class")
    }
}