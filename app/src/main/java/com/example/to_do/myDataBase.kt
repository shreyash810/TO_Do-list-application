package com.example.to_do

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Entity::class], version = 3)
abstract class myDataBase : RoomDatabase() {

    abstract  fun dao():DAO
}