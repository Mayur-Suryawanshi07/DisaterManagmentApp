package com.example.disastermanagmentapp.feature_login.presentation.loginscreen

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
import androidx.compose.material.icons.filled.Approval
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.disastermanagmentapp.R


@Composable
fun LogInScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel<LogInScreenViewModel>()
    val state by viewModel.state.collectAsState()

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

            Spacer(Modifier.height(24.dp))

            //username
            OutlinedTextField(
                value = viewModel.userName,
                onValueChange = {
                    viewModel.userName = it
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Approval, contentDescription = "")
                },
                label = {
                    Text(text = "Username or Email")
                }
            )

            Spacer(Modifier.height(6.dp))
            //password
            OutlinedTextField(
                value = viewModel.passWord,
                onValueChange = {
                    viewModel.passWord = it
                },
                label = {
                    Text(text = "Password")
                }
            )

            Spacer(Modifier.height(6.dp))

            Button(
                onClick = {

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Login")
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

                        }
                )
            }

            Spacer(Modifier.height(6.dp))

            OutlinedButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Continue as guest")
            }


        }

    }


}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginPre() {

    LogInScreen()
}