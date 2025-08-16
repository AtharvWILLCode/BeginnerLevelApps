/*
This is your operation list ➕➖✖️➗

Each operation like Add, Subtract, Multiply, and Divide is written as its own object.
It also includes the symbol "+", "-", etc. so you can show that on the screen.

✅ It keeps your code clean — instead of writing "+" everywhere, you use CalcOps.Add.
*/



package com.example.calculatorcompose

sealed class CalcOps(val symbol: String) {
    object Add : CalcOps("+")
    object Subtract : CalcOps("-")
    object Multiply : CalcOps("x")
    object Divide : CalcOps("/")
}