package com.example.disasterpreparedness.feature_login.presentation.auth.signupscreen

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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.disasterpreparedness.R
import com.example.disasterpreparedness.core.navigation.Graphs
import com.example.disasterpreparedness.core.navigation.Routes
import com.example.disasterpreparedness.feature_login.presentation.auth.loginscreen.LoginUiState

@Composable
fun SignUpScreen(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel= viewModel<SignUpViewModel>()
    val state = viewModel.state.collectAsState()

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var passWord by remember { mutableStateOf("") }



   LaunchedEffect(state.value) {
       when(state.value){
           is SignUpState.Authenticated -> {
               navController.navigate(Graphs.Auth){
                   popUpTo(Graphs.Auth){inclusive = true}
               }
           }
           is SignUpState.Error -> {
               Toast.makeText(context, (state.value as SignUpState.Error).message, Toast.LENGTH_SHORT).show()
           }
           is SignUpState.Loading -> {
               Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
           }
           is SignUpState.Unauthenticated -> {
               Toast.makeText(context, "Unauthenticated", Toast.LENGTH_SHORT).show()
           }


       }

   }


    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Image(
                painter = painterResource(id = R.drawable.disasterlogo),
                contentDescription = "My image",
                Modifier.size(150.dp)
            )


            Text(text = "Signup",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary)



            OutlinedTextField(
                value = username,
                onValueChange = {
                    username = it
                },
                label = {
                    Text(text = "Username")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = {
                    Text(text = "Email")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(6.dp))

            OutlinedTextField(
                value = passWord,
                onValueChange = {
                    passWord = it
                },
                label = {
                    Text(text = "Password")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(6.dp))

            Button(
                onClick = {
                    viewModel.signup(username, email, passWord)
                },
                modifier = Modifier.width(270.dp)
            ) {
                Text(text = "Signup")
            }


            Text(
                text = "forget password?",
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable {

                    },
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
                    text = "Already have Account? ",
                    fontSize = 16.sp,
                    modifier = Modifier,
                    color = Color.DarkGray
                )
                Text(
                    text = "Login",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                        }
                )
            }

        }
    }
}
