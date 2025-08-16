package com.example.disastermanagmentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.disastermanagmentapp.core.theme.DisasterManagmentAppTheme
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.Interface.UserInterface
import com.example.disastermanagmentapp.feature_login.presentation.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val isDarkTheme = rememberSaveable { mutableStateOf(false) }

            DisasterManagmentAppTheme(darkTheme = isDarkTheme.value) {
                 //UserInterface(this, isDarkTheme)
                Navigation(context = this, isDark = isDarkTheme)
            }
        }
    }


}