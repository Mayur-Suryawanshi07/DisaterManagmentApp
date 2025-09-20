package com.example.disasterpreparedness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.disasterpreparedness.core.navigation.DisasterPreparednessApp
import com.example.disasterpreparedness.core.theme.DisasterManagmentAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DisasterManagmentAppTheme() {
                DisasterPreparednessApp()
            }
        }
    }
}