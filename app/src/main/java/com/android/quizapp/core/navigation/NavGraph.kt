package com.android.quizapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.quizapp.features.auth.presentation.screens.ForgetPasswordScreen
import com.android.quizapp.features.auth.presentation.screens.LoginScreen
import com.android.quizapp.features.auth.presentation.screens.SignupScreen
import com.android.quizapp.features.navbar.MainScreen
import com.android.quizapp.features.onboarding.screens.OnboardingScreen

@Composable
fun InitNavGraph(
    modifier: Modifier = Modifier,
    startDestination: String = Screen.Onboarding.route,
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Screen.Onboarding.route) { OnboardingScreen(navController = navController) }
        composable(route = Screen.Signup.route) { SignupScreen(navController = navController) }
        composable(route = Screen.ForgetPassword.route) { ForgetPasswordScreen(navController = navController) }
        composable(route = Screen.Login.route) { LoginScreen(navController = navController) }
        composable(route = Screen.Main.route) { MainScreen() }

    }
}