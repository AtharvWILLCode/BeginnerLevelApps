package com.example.todo.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo2.TaskVM
import com.example.todo2.ui.theme.LightGrey1
import kotlinx.coroutines.launch

@Composable
fun NavSheet(
    viewModel: TaskVM,
    drawerState: DrawerState,
    navigateToCalendar: () -> Unit
) {
    val scope = rememberCoroutineScope()

    ModalDrawerSheet(
        modifier = Modifier.width(300.dp),
        drawerContainerColor = LightGrey1,
        drawerContentColor = Color.White
    ) {
        Text("Tick-DO", fontSize = 24.sp, modifier = Modifier.padding(12.dp))
        HorizontalDivider(thickness = 3.dp, modifier = Modifier.padding(12.dp))

        NavigationDrawerItem(
            label = { Text("Home") },
            selected = viewModel.currentFilter == "ALL",
            onClick = {
                viewModel.today = false
                viewModel.cals = false
                viewModel.setFilter("ALL")
                scope.launch { drawerState.close() }
            },
            icon = { Icon(Icons.Default.Home, contentDescription = null) }
        )

        NavigationDrawerItem(
            label = { Text("Today") },
            selected = viewModel.currentFilter == "TODAY",
            onClick = {
                viewModel.today = true
                viewModel.cals = false
                viewModel.setFilter("TODAY")
                scope.launch { drawerState.close() }
            },
            icon = { Icon(Icons.Default.Check, contentDescription = null) }
        )

        NavigationDrawerItem(
            label = { Text("Calendar") },
            selected = false,
            onClick = {
                viewModel.today = false
                viewModel.cals = true
                navigateToCalendar()
                scope.launch { drawerState.close() }
            },
            icon = { Icon(Icons.Default.DateRange, contentDescription = null) }
        )
    }
}



