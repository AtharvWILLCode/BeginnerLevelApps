package com.example.todo2.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todo2.TaskVM

@Composable
fun CalendarScreen(
    viewModel: TaskVM,
    onClick: () -> Unit
) {
    var selectedDate by remember { mutableStateOf("") }

    val allTasks by viewModel.tasks.collectAsState(initial = emptyList())
    val filteredTasks = if (selectedDate.isNotEmpty())
        viewModel.filterTasksByDate(selectedDate, allTasks)
    else
        viewModel.getFilteredTasks(allTasks)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black,
        topBar = {
            AddTaskHead(onClick = { onClick() }, viewModel = viewModel)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            PickDate(
                selectedDate = if (selectedDate.isEmpty()) "Pick a date" else selectedDate,
                onDateSelected = {
                    selectedDate = it
                }
            )

            Spacer(Modifier.height(24.dp))

            TaskList(tasks = filteredTasks, viewModel = viewModel)
        }
    }
}




