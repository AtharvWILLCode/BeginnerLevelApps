package com.example.todo2.DB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {
    @Insert
    suspend fun addTask(task: TaskTable)

    @Delete
    suspend fun delTask(task: TaskTable)

    @Update
    suspend fun updateTask(task: TaskTable)

    @Query("SELECT * FROM TaskTable ORDER BY date ASC")
    fun getAllTasks(): Flow<List<TaskTable>>

}


