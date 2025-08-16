package com.example.todo2.DB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskTable(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val taskName: String,
    val date: String,
    val isChecked: Boolean = false
)

