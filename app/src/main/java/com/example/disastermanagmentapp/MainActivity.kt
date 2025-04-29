package com.example.disastermanagmentapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.disastermanagmentapp.ui.theme.DisasterManagmentAppTheme
import com.example.disastermanagmentapp.Interface.UserInterface

open class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val isDarkTheme = rememberSaveable { mutableStateOf(false) }
            DisasterManagmentAppTheme(darkTheme = isDarkTheme.value) {
                UserInterface(this, isDarkTheme)
            }
        }
    }


}

