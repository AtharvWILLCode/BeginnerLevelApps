package com.example.calculatorcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculatorcompose.ui.theme.CalculatorComposeTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculatorcompose.CalcViewModel



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge() //Makes your app use the full screen including under the system bars.
        setContent {
            CalculatorComposeTheme {
                val viewModel = viewModel<CalcViewModel>() //This helps manage and preserve UI state during recompositions.
                val state = viewModel.state //Gets the current UI state from the ViewModel.
                val buttonspacing = 8.dp
                Calculator(
                    state = state,//The current calculator state
                    onAction = viewModel::onAction,//A reference to the onAction function in the ViewModel,
                    buttonspacing = buttonspacing,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.DarkGray)
                )
                //Calls the Calculator composable function, passing it:



            }
        }
    }
}





