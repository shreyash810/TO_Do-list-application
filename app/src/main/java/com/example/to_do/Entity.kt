package com.example.to_do

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "TODO")
class Entity (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var task:String,
    var priority:String,
    var time : String,
    var date : String
        )