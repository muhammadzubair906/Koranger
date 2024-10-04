package com.muhammadzubair.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.muhammadzubair.koranger.BarOrientation
import com.muhammadzubair.koranger.ComposerRangeBar
import com.muhammadzubair.example.ui.theme.KorangerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val currentValue = remember {
                mutableStateOf<Int>(0)
            }
            KorangerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    Column(modifier = Modifier
                        .padding(padding)
                        .width(500.dp)) {
                        ComposerRangeBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp),
                            minValue = 0,
                            maxValue = 100,
                            activeColor = Color.Blue,
                            inactiveColor = Color.Red,
                            thumbRadius = 20f,
                            orientation = BarOrientation.Horizontal,
                            onValueChange = { value ->
                                currentValue.value = value
                            }
                        )
                        Row (modifier = Modifier.fillMaxSize()){
                            ValueCounter(text = currentValue.value.toString())
                        }
                    }

                }
            }
        }
    }
    @Composable
    fun ValueCounter(text: String){
        Text(text = text)
    }
}


