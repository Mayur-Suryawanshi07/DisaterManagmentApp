package com.example.disastermanagmentapp.feature_login.presentation.auth.signupscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.disastermanagmentapp.R

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var passWord by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Image(
                painter = painterResource(id = R.drawable.disasterlogo),
                contentDescription = "My image",
                Modifier.size(150.dp)
            )
            Spacer(Modifier.height(24.dp))

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text(text = "Full Name")
                }
            )

            Spacer(Modifier.height(6.dp))

            OutlinedTextField(
                value = userName,
                onValueChange = {
                    userName = it
                },
                label = {
                    Text(text = "Username or Email")
                }
            )

            Spacer(Modifier.height(6.dp))

            OutlinedTextField(
                value = passWord,
                onValueChange = {
                    passWord = it
                },
                label = {
                    Text(text = "Password")
                }
            )

            Spacer(Modifier.height(6.dp))

            Button(
                onClick = {

                },
                modifier = Modifier.width(270.dp)
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

                        }
                )
            }

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SignUpPre() {
    SignUpScreen()
}