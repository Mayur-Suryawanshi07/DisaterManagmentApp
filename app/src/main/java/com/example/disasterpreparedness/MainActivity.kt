package com.example.disasterpreparedness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.disasterpreparedness.core.navigation.AppRootNav
import com.example.disasterpreparedness.core.theme.DisasterManagmentAppTheme
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.disaster_screen.DisasterScreenViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.database

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val viewModel = viewModel<DisasterScreenViewModel>()

            DisasterManagmentAppTheme() {
                Scaffold {

                    AppRootNav(Modifier.padding(it))

                }


            }
        }
    }
}