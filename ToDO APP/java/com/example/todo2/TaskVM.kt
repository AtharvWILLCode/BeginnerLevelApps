package com.example.todo2

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.todo2.DB.TaskDB
import com.example.todo2.DB.TaskTable
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TaskVM(application: Application) : AndroidViewModel(application)
{

    // ===== DATABASE SETUP =====
    private val db = Room.databaseBuilder(
        application.applicationContext, // App context for database creation
        TaskDB::class.java,
        "task.db" // Name of the database file stored on device
    )
        .fallbackToDestructiveMigration(false) // Don't delete data during version upgrades
        .build() // Actually create the database instance

    private val dao = db.taskDao()

    val tasks = dao.getAllTasks()

    // ===== CRUD OPERATIONS (Create, Read, Update, Delete) =====

    // CREATE: Add new task to database
    fun addTask(taskName: String , date : String)
    {
        viewModelScope.launch {
            dao.addTask(TaskTable(taskName = taskName, date = date))
        }
    }

    // DELETE: Remove task from database
    fun deleteTask(task: TaskTable) {
        viewModelScope.launch {
            dao.delTask(task)
        }
    }

    // UPDATE: Modify existing task in database
    fun updateTask(id: Int, newName: String, newDate: String) {
        viewModelScope.launch {
            dao.updateTask(TaskTable(id = id, taskName = newName, date = newDate))
        }
    }

    // ===== FILTERING FUNCTIONALITY =====

    var currentFilter by mutableStateOf("ALL") // "ALL" or "TODAY"
        private set

    fun setFilter(filter: String) {
        currentFilter = filter
    }

    // all task or today filter
    fun getFilteredTasks(allTasks: List<TaskTable>): List<TaskTable> {
        val today = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date())
        return if (currentFilter == "TODAY") {
            allTasks.filter { it.date == today }
        } else {
            allTasks
        }
    }

    fun filterTasksByDate(date: String, allTasks: List<TaskTable>): List<TaskTable> {
        return allTasks.filter { it.date == date }
    }

    // ===== SEARCH FUNCTIONALITY =====

    var searchQuery by mutableStateOf("")

    fun searchTasks(allTasks: List<TaskTable>): List<TaskTable> {
        return if (searchQuery.isBlank()) allTasks
        else allTasks.filter {
            it.taskName.contains(searchQuery, ignoreCase = true)
        }
    }

    // ===== TASK COMPLETION FUNCTIONALITY =====

    val checkedStates = mutableStateMapOf<Int, Boolean>()

    // Toggle task completion status (check/uncheck)
    fun toggleChecked(task: TaskTable) {
        viewModelScope.launch {
            val updatedTask = task.copy(isChecked = !task.isChecked)
            dao.updateTask(updatedTask)
        }
    }

    var today by mutableStateOf(false)
    var cals by mutableStateOf(false)
}