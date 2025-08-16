package com.example.todo2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo2.DB.TaskTable
import com.example.todo2.TaskVM
import com.example.todo2.ui.theme.LightGrey1
import kotlinx.coroutines.launch

@Composable
fun HeaderBar(
    viewModel: TaskVM,
    onTickClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    var isSearching by remember { mutableStateOf(false) }

    var headTitle: String = "All Tasks"

    if(viewModel.today){headTitle = "Today's Task"}

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(70.dp)
            .neomorphic(elevation = 10.dp, cornerRadius = 24.dp)
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(24.dp)
            ),
        contentAlignment = Alignment.Center,
    ) {
        if (isSearching) {
            // 🔍 Replace header content with search bar
            TaskSearch(
                viewModel = viewModel,
                onClose = { isSearching = false }
            )
        } else {
            // 🧭 Normal header content
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onTickClick,
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Icon(
                        Icons.Default.Check,
                        modifier = Modifier.size(28.dp),
                        contentDescription = "Check",
                        tint = Color.White
                    )
                }

                Text(
                    text = headTitle,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 10.dp)
                ) {
                    IconButton(
                        onClick = { isSearching = true },
                        modifier = Modifier.padding(start = 10.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(28.dp),
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Color.White
                        )
                    }

                    IconButton(onClick = onMenuClick) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More options",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}




@Composable
fun Bottoms() {
    var textf by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .navigationBarsPadding()
            .neomorphic(elevation = 2.dp, cornerRadius = 24.dp)
            .clip(RoundedCornerShape(20.dp))
    )
    {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .neomorphic(cornerRadius = 10.dp)

                .clip(RoundedCornerShape(20.dp)),
            value = textf,
            onValueChange = { textf = it },
            placeholder = {
                Text("Enter Quick Task Here")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Black, //
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color.Black, //Color.Black
                focusedTextColor = Color.White,
                unfocusedPlaceholderColor = Color.White,
                focusedPlaceholderColor = Color.White
            ),
            enabled = true
        )
    }

}


@Composable
fun CustomFAB(onClick: () -> Unit) {
    FloatingActionButton(
        modifier = Modifier
            .neomorphic(cornerRadius = 24.dp)
            .size(80.dp),
        containerColor = LightGrey1,
        contentColor = Color.White,
        onClick = {
            onClick()
        }

    ) {
        Icon(
            modifier = Modifier
                .size(35.dp),
            imageVector = Icons.Default.Add,
            contentDescription = "add icon",

            )
    }
}


fun Modifier.neomorphic(
    backgroundColor: Color = LightGrey1, // softer dark background instead of pure black
    cornerRadius: Dp = 10.dp,
    elevation: Dp = 6.dp // slightly higher to separate from the background
): Modifier {
    return this
        .shadow(
            elevation = elevation,
            shape = RoundedCornerShape(cornerRadius),
            ambientColor = Color(0xFF222222), // softer dark shadow instead of pure white
            spotColor = Color(0xFF000000),    // subtle spot shadow
            clip = false
        )
        .background(
            color = backgroundColor,
            shape = RoundedCornerShape(cornerRadius)
        )
        .clip(RoundedCornerShape(cornerRadius))
}


@Composable
fun AddTaskHead(onClick: () -> Unit ,viewModel: TaskVM) {

    var titlehead:String = "New Task"
    if(viewModel.cals){titlehead = "Calender"}

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(60.dp)
            .neomorphic(elevation = 10.dp, cornerRadius = 24.dp)
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(24.dp)
            ),
        contentAlignment = Alignment.Center
    )
    {
        Text(
            text = titlehead,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,

            )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                viewModel.cals = false
                onClick()
            }) {
                Icon(
                    Icons.Default.ArrowBack,
                    modifier = Modifier.size(30.dp),
                    contentDescription = "Check",
                    tint = Color.White
                )

            }

        }
    }
}

@Composable
fun TaskAdderScreen(
    taskText: String,
    onTaskTextChange: (String) -> Unit,
    selectedDate: String,
    onDateSelected: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier.padding(top = 50.dp)
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = "What task is to be done ?",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                OutlinedTextField(
                    value = taskText,
                    onValueChange = onTaskTextChange,
                    placeholder = { Text("Enter New Task") },
                    textStyle = TextStyle(color = Color.White),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White
                    )
                )
            }
            Spacer(Modifier.height(20.dp))
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = "What's the Due Date ?",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                PickDate(
                    selectedDate = selectedDate,
                    onDateSelected = onDateSelected
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickDate(selectedDate: String, onDateSelected: (String) -> Unit) {
    var showDate by remember { mutableStateOf(false) }
    val dateState = rememberDatePickerState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        TextButton(
            onClick = { showDate = true },

            modifier = Modifier
                .neomorphic(cornerRadius = 10.dp)
                .width(150.dp)
                .height(60.dp)
                .border(
                    width = 0.8.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                ),
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color.White,
                containerColor = LightGrey1
            )
        ) {
            Text(selectedDate, fontSize = 15.sp)
            Spacer(Modifier.width(10.dp))
            Icon(Icons.Default.DateRange, contentDescription = "Add Task")
        }
    }

    if (showDate) {
        DatePickerDialog(
            onDismissRequest = { showDate = false },
            confirmButton = {
                TextButton(onClick = {
                    showDate = false
                    dateState.selectedDateMillis?.let { millis ->
                        val sdf =
                            java.text.SimpleDateFormat("dd MMM yyyy", java.util.Locale.getDefault())
                        onDateSelected(sdf.format(java.util.Date(millis)))
                    }
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showDate = false }) { Text("Cancel") }
            }
        ) {
            DatePicker(state = dateState)
        }
    }
}


@Composable
fun AddFAB(
    taskText: String,
    onAddClick: () -> Unit
) {
    Box(
        modifier = Modifier.padding(bottom = 20.dp)
    ) {
        FloatingActionButton(
            modifier = Modifier
                .neomorphic(cornerRadius = 50.dp)
                .size(80.dp),
            shape = RoundedCornerShape(50.dp),
            containerColor = LightGrey1,
            contentColor = Color(0xFFE0E0E0),
            onClick = { onAddClick() }
        ) {
            Icon(Icons.Default.Check, contentDescription = "Add Task")
        }
    }
}


@Composable
fun MenuDrop(
    expanded: Boolean,
    onDismiss: () -> Unit
) {
    DropdownMenu(
        modifier = Modifier.width(150.dp),
        expanded = expanded,
        onDismissRequest = onDismiss,
        offset = DpOffset(x = (-16).dp, y = 0.dp),
        containerColor = LightGrey1,
    ) {
        DropdownMenuItem(
            text = { Text("Edit", color = Color.White) },
            onClick = {
                onDismiss()
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Edit,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        )
        DropdownMenuItem(
            text = { Text("Delete", color = Color.White) },
            onClick = {
                onDismiss()
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        )
        DropdownMenuItem(
            text = { Text("Settings", color = Color.White) },
            onClick = {
                onDismiss()
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        )
    }
}


@Composable
fun UpdateDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    taskToEdit: TaskTable,
    viewModel: TaskVM
) {
    if (showDialog) {
        var newText by remember { mutableStateOf(taskToEdit.taskName) }
        var selectedDate by remember { mutableStateOf(taskToEdit.date) }
        val scope = rememberCoroutineScope()

        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                Button(
                    onClick = {
                        if (newText.isNotBlank())
                        {
                            scope.launch {
                                viewModel.updateTask(taskToEdit.id, newText, selectedDate)
                            }
                        }

                        onDismiss()
                    },
                    modifier = Modifier
                        .padding(end = 40.dp, start = 10.dp)
                        .border(
                            width = 0.8.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(50.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(LightGrey1)
                ) {
                    Text("Save", color = Color.White)
                }
            },
            dismissButton = {
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(LightGrey1),
                    modifier = Modifier
                        .border(
                            width = 0.8.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(50.dp)
                        ),
                ) {
                    Text("Dismiss", color = Color.White)
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit icon"
                )
            },
            title = {
                Text(
                    "Edit Your Task",
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            },
            text = {
                Column {
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = "Edit Task Name",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(Modifier.height(20.dp))
                    OutlinedTextField(
                        value = newText,
                        onValueChange = { newText = it },
                        label = { Text("Task name") }
                    )
                    Spacer(Modifier.height(20.dp))
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = "Edit Due Date",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    PickDate(
                        selectedDate = selectedDate,
                        onDateSelected = { selectedDate = it }
                    )
                    Spacer(Modifier.height(20.dp))
                }
            },
            containerColor = LightGrey1,
            iconContentColor = Color.White,
            titleContentColor = Color.White,
            textContentColor = Color.White,
            tonalElevation = 100.dp
        )
    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskSearch(
    viewModel: TaskVM,
    onClose: () -> Unit
) {
    val searchQuery = viewModel.searchQuery

    SearchBar(
        query = searchQuery,
        onQueryChange = { viewModel.searchQuery = it },
        onSearch = {},
        active = true,
        onActiveChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(30.dp))

        ,
        placeholder = {
            Text(
                text = "Search tasks...",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.offset(y = -5.dp)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.White,
                modifier = Modifier
                    .size(35.dp)
                    .padding(bottom = 14.dp)
            )
        },
        trailingIcon = {
            IconButton(
                modifier = Modifier.padding(bottom = 14.dp),
                onClick = {
                    viewModel.searchQuery = ""
                    onClose()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear",
                    tint = Color.White,
                    modifier = Modifier.size(25.dp)

                )
            }
        },
        colors = SearchBarDefaults.colors(containerColor = LightGrey1)
    ) {}
}









