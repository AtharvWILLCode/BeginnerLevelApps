/*

This file lists all the different things a user can do on the calculator.
Imagine it like a menu of actions — "What can I press?"

✅ This file defines each of those actions in a structured way, like giving your app instructions on what buttons do what.

📦 It uses something called sealed class, which is like a special box where only certain types of actions are allowed. Great for safety and control.



*/



package com.example.calculatorcompose

sealed class CalcActions
// `sealed class` means: we’re creating a special type of
// class that has a fixed list of possible types inside it.
{
    data class Number(val number:Int) : CalcActions()
    // `data class` means: a class used to hold data only.
    // `Number` is a type of `CalcActions`, and it
    // stores an integer (like 5, 9, etc.)

    object Clear : CalcActions()
    // `object` means: a single instance of something (like a button tap).
    // This is another type of `CalcActions`.

    object Delete : CalcActions()
    object Decimal : CalcActions()
    object Calculate : CalcActions()
    data class Operation(val operation: CalcOps) : CalcActions()
    // Another `data class`, this one stores a value of type `CalcOps`
    // `CalcOps` is another custom class that stores operations like +, -, etc.
}