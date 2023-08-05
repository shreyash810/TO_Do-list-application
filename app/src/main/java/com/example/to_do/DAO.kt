package com.example.to_do

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DAO {
    @Insert
    suspend fun insertTask(entity: Entity)

    @Update
    suspend fun updateTask(entity: Entity)

    @Delete
    suspend fun deleteTask(entity: Entity)

    @Query("Delete from TODO")
    suspend fun deleteAll()

    @Query("Select * from TODO")
    suspend fun getTask(): List<CardInfo>
}