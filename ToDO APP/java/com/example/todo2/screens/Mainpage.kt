package com.example.todo.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FabPosition
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todo2.TaskVM
import com.example.todo2.screens.AddScreen
import com.example.todo2.screens.CalendarScreen
import com.example.todo2.screens.CustomFAB
import com.example.todo2.screens.HeaderBar
import com.example.todo2.screens.MenuDrop
import com.example.todo2.screens.TaskList
import kotlinx.coroutines.launch


@Composable
fun MainScreen(viewModel: TaskVM) {

    val focusManager = LocalFocusManager.current
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    var menuExpanded by remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavSheet(
                viewModel = viewModel,
                drawerState = drawerState,
                navigateToCalendar = {
                    navController.navigate("calendar")
                }
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = "main"
        ) {
            composable("main") {
                val allTasks by viewModel.tasks.collectAsState(initial = emptyList())
                val filteredTasks = viewModel.searchTasks(
                    viewModel.getFilteredTasks(allTasks)
                )

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { focusManager.clearFocus() },
                    containerColor = Color.Black,
                    topBar = {
                        Column {
                            HeaderBar(
                                viewModel = viewModel,
                                onTickClick = { scope.launch { drawerState.open() } },
                                onMenuClick = { menuExpanded = true }
                            )
                            MenuDrop(
                                expanded = menuExpanded,
                                onDismiss = { menuExpanded = false }
                            )
                        }
                    },
                    floatingActionButton = {
                        CustomFAB {
                            navController.navigate("addscreen")
                        }
                    },
                    floatingActionButtonPosition = FabPosition.End
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        TaskList(tasks = filteredTasks, viewModel = viewModel)
                    }
                }
            }


            composable("addscreen") {
                AddScreen(
                    viewModel = viewModel,
                    onClick = { navController.popBackStack() }
                )
            }

            composable("calendar") {
                CalendarScreen(
                    viewModel = viewModel,
                    onClick = { navController.popBackStack() }
                )
            }
        }
    }
}





