package com.example.disastermanagmentapp.feature_login.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.Interface.UserInterface
import com.example.disastermanagmentapp.feature_login.presentation.auth.loginscreen.LogInScreen
import com.example.disastermanagmentapp.feature_login.presentation.auth.signupscreen.SignUpScreen
import kotlinx.serialization.Serializable

@Composable
fun Navigation(modifier: Modifier = Modifier, context : Context, isDark: MutableState<Boolean>) {

    val navController= rememberNavController()

    NavHost(navController = navController , startDestination = Routes.login){
        composable<Routes.login> {
            LogInScreen(navigation = navController)
        }
        composable<Routes.signup> {
            SignUpScreen(navigation = navController)
        }
        composable<Routes.home> {
            UserInterface(context = context, isDarkTheme = isDark)
        }
    }



}

sealed class Routes{
    @Serializable
    object home: Routes()

    @Serializable
    object login:Routes()

    @Serializable
    object signup:Routes()
}