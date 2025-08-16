package com.example.calculatorcompose

import android.icu.text.SymbolTable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun CalcButton(
    symbol : String,
    modifier: Modifier,
    backgroundColor: Color = Color.DarkGray, //Button background color. Default is dark gray unless overridden
    onClick: () -> Unit
)
{
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(CircleShape) //Makes the button round, like a circle.
            .clickable{onClick()}
            .background(backgroundColor)
    ) {
        Text(
            text = symbol,
            fontSize = 36.sp,
            color = Color.White

        )
    }
}