package com.example.zingproject.repository

import com.example.zingproject.database.User
import com.example.zingproject.database.UserDAO

class UserRepository(private val userDao:UserDAO) {

    val users = userDao.getAllUsers()

    suspend fun insert(user: User){
        userDao.insertUser(user)
    }

    suspend fun update(user:User){
        userDao.updateUser(user)
    }

    suspend fun delete(user: User){
        userDao.deleteUser(user)
    }



}