package com.example.zingproject.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
val name:String,
val email:String
, val seqNo : Int,
val title: String,
val senderRef :String,
val senderId: String,
val body: String,
val msgRef:String,
val msgType:String,
val dataPart:String,
val date: String,
val deleted:Boolean,
val msgRead:Boolean)
