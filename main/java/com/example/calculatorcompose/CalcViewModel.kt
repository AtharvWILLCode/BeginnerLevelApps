/*
This is like your calculator’s brain 🧠.

📦 It remembers your state (what’s written in the notebook from CalcStates.kt) and controls when it changes.

ViewModel is a class used in Android to separate your UI and logic.

mutableStateOf() is like saying: “Hey, this data might change later and the screen should update when it does!”

🎮 When someone presses a button, the ViewModel decides:

Should it add a number?

Start a new operation?

Clear the screen?
*/



package com.example.calculatorcompose

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class CalcViewModel: ViewModel() {
    //This defines CalcViewModel class which inherits from Android’s ViewModel.
    //ViewModel holds UI-related data in a lifecycle-aware way — survives config changes.
    var state by mutableStateOf(CalcStates())
        // `state` is a variable we can update, and Jetpack Compose will watch it
        // `by` is used with delegated properties
        // `mutableStateOf()` is a function that lets Jetpack Compose track changes in value
        private set
    // This means only this class can change the value of `state`

    fun onAction(action: CalcActions) //This function receives an action from the UI — like "button tapped".
    {
        when(action)
        {
            is CalcActions.Number -> enterNumber(action.number)
            is CalcActions.Decimal -> enterDecimal()
            is CalcActions.Clear -> state = CalcStates()
            is CalcActions.Operation -> enterOperation(action.operation)
            is CalcActions.Calculate -> performCalculation()
            is CalcActions.Delete -> performDeletion()
        }
    }

    private fun performDeletion() {
        when
        {
            state.number2.isNotBlank() ->state = state.copy(
                number2 = state.number2.dropLast(1)
            )
            //state.number2.isNotBlank() checks if the second number field has any characters.
            //If true, it updates the state using state.copy(...).
            //state.number2.dropLast(1) removes the last digit/character from number2.
            //Why use .copy()? Because CalcStates is an immutable data class — we can’t modify it directly, so we make a copy with the updated field.
            state.operation != null -> state = state.copy(
                operation = null
            )
            state.number1.isNotBlank() ->state = state.copy(
                number1 = state.number1.dropLast(1)
            )
            //If neither number2 nor operation had anything,
            // it removes the last digit from number1.
        }
    }

    private fun performCalculation() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        //These lines try to convert the string inputs ("42", "3.14", etc.) into actual numbers (Double).

        if(number1 != null && number2 != null)
        {
            val result = when(state.operation)
            {
                is CalcOps.Add -> number1 + number2
                is CalcOps.Subtract -> number1 - number2
                is CalcOps.Multiply -> number1 * number2
                is CalcOps.Divide -> number1 / number2
                null -> return
                //It checks the type of the operation (sealed class with subtypes).
                //Based on which one is used (Add, Subtract, etc.), it performs that operation.
                //null -> return means: if no operation is selected, exit this function.
            }
            state = state.copy(
                number1 = result.toString().take(15),
                number2 = "",
                operation = null
            )
            //The result is converted to a string and stored in number1.
            //.take(15) ensures it shows a max of 15 characters to avoid long decimals.
            //number2 and operation are cleared to prepare for the next calculation.
        }

    }

    private fun enterOperation(operation: CalcOps) {
        if(state.number1.isNotBlank()){
            state = state.copy(operation = operation)
        }
    }

    private fun enterDecimal() {
        if(state.operation == null && !state.number1.contains(".")
            && state.number1.isNotBlank())
        {
            state = state.copy(
                number1 = state.number1 + "."
            )
            return
        }
        if(!state.number2.contains(".")
            && state.number2.isNotBlank())
        {
            state = state.copy(
                number2 = state.number2 + "."
            )
            return
        }
    }

    private fun enterNumber(number: Int) {
        if(state.operation == null)
        {
            if(state.number1.length >= 8)
            {
                return
            }
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }
        if(state.number2.length >= 8)
        {
            return
        }
        state = state.copy(
            number2 = state.number2 + number
        )
    }
}

//state = state.copy(...)
// Creates a new version of the state with only specific values changed

