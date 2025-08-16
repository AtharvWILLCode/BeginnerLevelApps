package com.example.calculatorcompose

import android.R.attr.onClick
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorcompose.ui.theme.LightGray
import com.example.calculatorcompose.ui.theme.MediumGray
import com.example.calculatorcompose.ui.theme.Orange

@Composable
fun Calculator(
    modifier: Modifier = Modifier,
    state: CalcStates, //current calculator data (numbers, operation).
    buttonspacing: Dp,
    onAction: (CalcActions) -> Unit //callback when a button is pressed, which takes a CalcActions object.
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonspacing)
        ) {
            Text(
                text = state.number1 + (state.operation?.symbol ?: ""),
                //Displays the first number, and if an operation is selected, adds its symbol (+, -, etc.).
                //?: "" means if there's no operation, show an empty string.
                fontSize = 60.sp,
                fontWeight = FontWeight.Light,
                color = Color.White,
                maxLines = 1,
                softWrap = false, //don’t wrap to the next line
                overflow = TextOverflow.Ellipsis //if the number is too long, show ...
            )
            Text(
                text = state.number2,
                fontSize = 60.sp,
                fontWeight = FontWeight.Light,
                color = Color.White,
                maxLines = 1,
                softWrap = false,
                overflow = TextOverflow.Ellipsis
            )

            // Row 1
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonspacing)
            ) {
                CalcButton(
                    symbol = "AC",
                    modifier = Modifier
                        .weight(2f) //makes it double the size.
                        .aspectRatio(2f)
                        .background(Color.Black),
                    backgroundColor = LightGray,
                    onClick = { onAction(CalcActions.Clear) }
                )
                CalcButton(
                    symbol = "Del",
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .background(Color.Black),
                    backgroundColor = LightGray,
                    onClick = { onAction(CalcActions.Delete) }
                )
                CalcButton(
                    symbol = "/",
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .background(Color.Black),
                    backgroundColor = Orange,
                    onClick = { onAction(CalcActions.Operation(CalcOps.Divide)) }
                )
            }

            // Row 2
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonspacing)
            ) {
                listOf("7", "8", "9").forEach {
                    CalcButton(
                        symbol = it,
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .background(Color.Black),
                        onClick = { onAction(CalcActions.Number(it.toInt())) }
                    )
                }
                CalcButton(
                    symbol = "x",
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .background(Color.Black),
                    backgroundColor = Orange,
                    onClick = { onAction(CalcActions.Operation(CalcOps.Multiply)) }
                )
            }

            // Row 3
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonspacing)
            ) {
                listOf("4", "5", "6").forEach { //Loops over numbers "7", "8", "9" and makes a button for each.
                    CalcButton(
                        symbol = it,
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .background(Color.Black),
                        onClick = { onAction(CalcActions.Number(it.toInt())) }
                    )
                }
                CalcButton(
                    symbol = "-",
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .background(Color.Black),
                    backgroundColor = Orange,
                    onClick = { onAction(CalcActions.Operation(CalcOps.Subtract)) }
                )
            }

            // Row 4
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonspacing)
            ) {
                listOf("1", "2", "3").forEach {
                    CalcButton(
                        symbol = it,
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .background(Color.Black),
                        onClick = { onAction(CalcActions.Number(it.toInt())) }
                    )
                }
                CalcButton(
                    symbol = "+",
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .background(Color.Black),
                    backgroundColor = Orange,
                    onClick = { onAction(CalcActions.Operation(CalcOps.Add)) }
                )
            }

            // Row 5
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonspacing)
            ) {
                CalcButton(
                    symbol = "0",
                    modifier = Modifier
                        .weight(2f)
                        .aspectRatio(2f)
                        .background(Color.Black),
                    onClick = { onAction(CalcActions.Number(0)) }
                )
                CalcButton(
                    symbol = ".",
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .background(Color.Black),
                    onClick = { onAction(CalcActions.Decimal) }
                )
                CalcButton(
                    symbol = "=",
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .background(Color.Black),
                    backgroundColor = Orange,
                    onClick = { onAction(CalcActions.Calculate) }
                )
            }
        }
    }
}
