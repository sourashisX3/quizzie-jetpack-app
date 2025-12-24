package com.android.quizapp.core.navigation

sealed class Screen(val route: String) {
    object Login : Screen(LOGIN)
    object Signup : Screen(SIGNUP)
    object ForgetPassword : Screen(FORGET_PASSWORD)
    object Onboarding : Screen(ONBOARDING)
    object Main : Screen(MAIN)
}