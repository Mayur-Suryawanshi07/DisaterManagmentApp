package com.example.disastermanagmentapp.feature_login.presentation.auth.loginscreen

import android.graphics.fonts.FontStyle
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.disastermanagmentapp.R
import com.example.disastermanagmentapp.core.navigation.Graphs
import com.example.disastermanagmentapp.core.navigation.Routes


@Composable
fun LogInScreen(
    navController: NavHostController
) {
    val viewModel = viewModel<LogInScreenViewModel>()
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(state.value) {
        when (val s = state.value) {
            is LoginUiState.Authorized -> {
                navController.navigate(Graphs.Main) {
                    popUpTo(Graphs.Auth) { inclusive = true } // clear all auth screens
                    launchSingleTop = true
                    restoreState = true
                }
            }

            is LoginUiState.Error -> {
                Toast.makeText(context, s.message, Toast.LENGTH_SHORT).show()
            }

            else -> Unit
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {


        Column(
            modifier = Modifier
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            //logo
            Image(
                painter = painterResource(id = R.drawable.disasterlogo),
                contentDescription = "My image",
                Modifier.size(150.dp)
            )

            Text(
                text = "Login",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 32.sp
            )

            Spacer(Modifier.height(24.dp))

            //username
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "Email Icon")
                },
                label = {
                    Text(text = "Username or Email")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(6.dp))
            //password
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = {
                    Text(text = "Password")
                },
                trailingIcon = {
                    val image = if (passwordVisible) {
                        Icons.Filled.Visibility
                    } else Icons.Filled.VisibilityOff

                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description)
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    if (password.length < 6) {
                        Toast.makeText(
                            context,
                            "Password must be at least 6 characters long",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        viewModel.login(email, password)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Login")
            }


            Text(
                text = "forget password?",
                modifier = Modifier
                    .align(Alignment.End),
                color = Color.DarkGray

            )

            Spacer(Modifier.height(6.dp))
            Text(
                text = "or",
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(6.dp))

            Row {
                Text(
                    text = "Don't have Account? ",
                    fontSize = 16.sp,
                    modifier = Modifier,
                    color = Color.DarkGray
                )
                Text(
                    text = "Sign up",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Routes.Signup)
                        }
                )
            }

            Spacer(Modifier.height(6.dp))

//            OutlinedButton(
//                onClick = {
//                    navController.navigate(Routes.Home)
//                },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(text = "Continue as guest")
//            }

        }

    }
}
