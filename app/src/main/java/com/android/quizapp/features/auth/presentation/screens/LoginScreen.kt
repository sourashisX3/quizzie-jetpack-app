package com.android.quizapp.features.auth.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.quizapp.core.navigation.Screen
import com.android.quizapp.features.auth.presentation.screens.landscape.LoginLandscapeContent
import com.android.quizapp.features.auth.presentation.screens.portrait.LoginPortraitContent
import com.android.quizapp.ui.theme.AppColor

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = AppColor.backgroundBrush)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp)
                .padding(
                    top = if (isLandscape) 16.dp else 24.dp,
                    bottom = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLandscape) {
                LoginLandscapeContent(
                    email = email,
                    onEmailChange = { email = it },
                    password = password,
                    onPasswordChange = { password = it },
                    passwordVisible = passwordVisible,
                    onPasswordVisibilityChange = { passwordVisible = it },
                    onLoginClick = {
                        navController.navigate(Screen.Main.route)
                    },
                    onSignupClick = {
                        navController.navigate(Screen.Signup.route)
                    },
                    onForgotPasswordClick = {
                        navController.navigate(Screen.ForgetPassword.route)
                    }
                )
            } else {
                LoginPortraitContent(
                    email = email,
                    onEmailChange = { email = it },
                    password = password,
                    onPasswordChange = { password = it },
                    passwordVisible = passwordVisible,
                    onPasswordVisibilityChange = { passwordVisible = it },
                    onLoginClick = {
                        navController.navigate(Screen.Main.route)
                    },
                    onSignupClick = {
                        navController.navigate(Screen.Signup.route)
                    },
                    onForgotPasswordClick = {
                        navController.navigate(Screen.ForgetPassword.route)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        navController = NavHostController(LocalContext.current),
    )
}