package com.example.zingproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class],version = 2)
abstract class UserDatabase:RoomDatabase(){

    abstract val userDao:UserDAO

    companion object{
        @Volatile
        private var INSTANCE:UserDatabase?=null

        fun getInstance(context: Context):UserDatabase
        {
            synchronized(lock =this){
                var instance:UserDatabase? = INSTANCE
                if (instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "User Database"
                    ).fallbackToDestructiveMigration().
                    build()

                }
                return instance
            }

        }
    }
}