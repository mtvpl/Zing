package com.example.zingproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zingproject.database.User
import com.example.zingproject.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository):ViewModel() {

    val inputName = MutableLiveData<String>()

    val inputEmail = MutableLiveData<String>()


    val users = userRepository.users

    fun insert(user: User){
        viewModelScope.launch {
            userRepository.insert(user)
        }
    }

    fun update(user:User){
        viewModelScope.launch {
            userRepository.update(user)
        }
    }

    fun delete(user: User){
        viewModelScope.launch {
            userRepository.delete(user)
        }
    }
}