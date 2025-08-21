package com.example.disastermanagmentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.disastermanagmentapp.core.navigation.AppRootNav
import com.example.disastermanagmentapp.core.theme.DisasterManagmentAppTheme
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.screens.disaster_screen.DisasterScreen
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.screens.disaster_screen.DisasterScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val viewModel= viewModel<DisasterScreenViewModel>()

            DisasterManagmentAppTheme() {

                AppRootNav()



            }
        }
    }
}