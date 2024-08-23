package com.example.calculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.CalculatorTheme

val buttonlist = listOf(
    "C","(",")","/",
    "7","8","9","*",
    "4","5","6","-",
    "1","2","3","+",
    "AC","0",".","=",
)

@Composable
fun Calci(modifier: Modifier, viewModel: CalculatorViewModel){

    val equationText = viewModel.equationText.observeAsState()
    val resultText = viewModel.resultText.observeAsState()

    Box(modifier = modifier.padding(8.dp)){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.End
            ) {
                    Spacer(modifier = modifier.height(16.dp))
                    Text(
                        text = equationText.value?:"",
                        style = TextStyle(
                            fontSize = 30.sp,
                            textAlign = TextAlign.End
                        ),
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis
                    )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = resultText.value?:"",
                    style = TextStyle(
                        fontSize = 50.sp,
                        textAlign = TextAlign.End
                    ),
                    maxLines = 2
                    )
                Spacer(modifier = Modifier.height(10.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(4
                    )) {
                        items(buttonlist){
                            buttonDesign(btn = it, onClick = {
                                viewModel.onButtonClick(it)
                            })
                        }
                }
            }
    }
}

@Composable
fun buttonDesign(btn : String, onClick : () -> Unit){
   Box(modifier = Modifier.padding(8.dp)){
       FloatingActionButton(
           onClick = { onClick() },
           containerColor = getColor(btn),
           contentColor = Color.White,
           shape = CircleShape,
           modifier = Modifier.size(70.dp)
       ) {
           Text(
               text = btn,
               fontSize = 24.sp,
               fontWeight = FontWeight.Bold
               )
       }
   }
}

fun getColor(btn : String) : Color{
    if(btn == "C" || btn == "AC")
        return Color(0xfff44336)
    if(btn == "*" || btn == "/" || btn == "+" || btn == "-" || btn == "=")
        return Color(0xffff9800)
    return if(btn == "(" || btn == ")")
        Color.Gray
    else Color(0xff00c8c9)
}

@Preview(showSystemUi = true)
@Composable
fun Greet(){
    CalculatorTheme (darkTheme = true){
        Calci(modifier = Modifier, viewModel = CalculatorViewModel())
    }
}
