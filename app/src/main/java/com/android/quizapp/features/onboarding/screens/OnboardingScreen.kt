package com.android.quizapp.features.onboarding.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.quizapp.core.navigation.Screen
import com.android.quizapp.ui.theme.AppColor
import com.android.quizapp.features.onboarding.screens.landscape.OnboardingLandscapeContent
import com.android.quizapp.features.onboarding.screens.portrait.OnboardingPortraitContent

/**
 * Onboarding Screen
 * First screen users see when opening the app
 * Supports both portrait and landscape orientations with proper scrollability
 */
@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = AppColor.backgroundBrush)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp)
                .padding(
                    top = if (isLandscape) 16.dp else 0.dp,
                    bottom = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLandscape) {
                OnboardingLandscapeContent(
                    onGetStartedClick = {
                        navController.navigate(Screen.Login.route)
                    }
                )
            } else {
                OnboardingPortraitContent(
                    onGetStartedClick = {
                        navController.navigate(Screen.Login.route)
                    }
                )
            }
        }
    }
}