package com.example.todo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo.screens.MainScreen
import com.example.todo2.ui.theme.ToDO2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // enableEdgeToEdge() extends your app's content to the full screen
        // including behind system bars (status bar and navigation bar)

        installSplashScreen()

        setContent {

            val viewModel: TaskVM = viewModel()

            ToDO2Theme {
                 MainScreen(viewModel)
                }
            }
        }
    }


