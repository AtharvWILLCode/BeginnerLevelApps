package com.example.todo2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import com.example.todo2.TaskVM
import kotlinx.coroutines.launch


@Composable
fun AddScreen(
    viewModel: TaskVM,
    onClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    var taskText by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("Pick A Date") }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .clickable { focusManager.clearFocus() },
            containerColor = Color.Black,
            topBar = {
                AddTaskHead(onClick = { onClick() }, viewModel = viewModel)
            },
            floatingActionButton = {
                AddFAB(
                    taskText = taskText,
                    onAddClick = {
                        if (taskText.isNotBlank() && selectedDate != "Pick A Date") {
                            scope.launch {
                                viewModel.addTask(taskText, selectedDate)
                            }
                            onClick()
                        }
                    }
                )

            },
            floatingActionButtonPosition = FabPosition.End
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                TaskAdderScreen(
                    taskText = taskText,
                    onTaskTextChange = { taskText = it },
                    selectedDate = selectedDate,
                    onDateSelected = { selectedDate = it }
                )
            }
        }
    }
}








