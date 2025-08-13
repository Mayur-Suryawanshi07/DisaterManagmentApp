package com.example.disastermanagmentapp.feature_disastermanagement


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.disastermanagmentapp.feature_disastermanagement.theme.DisasterManagmentAppTheme
import com.example.disastermanagmentapp.feature_disastermanagement.Interface.UserInterface
import com.example.disastermanagmentapp.feature_login.presentation.loginscreen.LogInScreen

open class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val isDarkTheme = rememberSaveable { mutableStateOf(false) }

            DisasterManagmentAppTheme(darkTheme = isDarkTheme.value) {
               // UserInterface(this, isDarkTheme)
                LogInScreen()
            }
        }
    }


}

