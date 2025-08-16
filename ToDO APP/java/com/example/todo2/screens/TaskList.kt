package com.example.todo2.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo2.DB.TaskTable
import com.example.todo2.TaskVM
import kotlinx.coroutines.launch

@Composable
fun TaskList(
    tasks: List<TaskTable>,
    viewModel: TaskVM
) {
    val scope = rememberCoroutineScope()

    var showDialog by rememberSaveable { mutableStateOf(false) }
    var taskToEdit by rememberSaveable { mutableStateOf<TaskTable?>(null) }

    val sortedTasks = tasks.sortedBy { it.isChecked } // ✅ Sort unchecked first

    Box(modifier = Modifier.fillMaxSize()) {
        if (tasks.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No tasks",
                    color = Color.White,
                    fontSize = 40.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 30.dp, start = 10.dp, end = 10.dp)
            ) {
                items(sortedTasks, key = { it.id }) { task ->

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .padding(vertical = 10.dp)
                            .neomorphic(cornerRadius = 24.dp)
                            .alpha(if (task.isChecked) 0.2f else 1f)
                            .clickable {
                                taskToEdit = task
                                showDialog = true
                            }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = task.taskName,
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = task.date,
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            }

                            IconButton(onClick = {
                                scope.launch {
                                    viewModel.deleteTask(task)
                                }
                            }) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "Delete Task",
                                    tint = Color.White
                                )
                            }

                            Checkbox(
                                checked = task.isChecked,
                                onCheckedChange = {
                                    viewModel.toggleChecked(task)
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color.White,
                                    uncheckedColor = Color.White,
                                    checkmarkColor = Color.Black
                                )
                            )
                        }
                    }
                }
            }
        }

        if (taskToEdit != null) {
            UpdateDialog(
                showDialog = showDialog,
                onDismiss = {
                    showDialog = false
                    taskToEdit = null
                },
                taskToEdit = taskToEdit!!,
                viewModel = viewModel
            )
        }
    }
}




