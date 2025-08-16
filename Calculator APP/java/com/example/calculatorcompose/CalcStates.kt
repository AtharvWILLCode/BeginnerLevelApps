/*

This file keeps track of what’s happening in your calculator right now.
🧠 Like:

What's the first number typed? (number1)

What's the second number typed? (number2)

Which operation is chosen? (operation)

📦 This file is like a notebook where your calculator writes what you're doing.
For example:

This whole class is just for storing data, so we use `data class`
*/




package com.example.calculatorcompose

data class CalcStates(
    val number1:String = "",
    val number2: String = "",
    val operation: CalcOps ?= null
    // This property is of type `CalcOps` or null
    // The `?` means it's optional (nullable)
)
