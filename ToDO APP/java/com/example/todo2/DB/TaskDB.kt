package com.example.todo2.DB

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [TaskTable::class] , version = 3 ,exportSchema = false)
abstract class TaskDB: RoomDatabase() {

    abstract fun taskDao(): TaskDao

}