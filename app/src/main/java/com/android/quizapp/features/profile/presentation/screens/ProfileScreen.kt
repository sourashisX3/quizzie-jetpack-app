package com.android.quizapp.features.profile.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.quizapp.core.navigation.Screen
import com.android.quizapp.features.profile.presentation.screens.landscape.ProfileLandscapeContent
import com.android.quizapp.features.profile.presentation.screens.portrait.ProfilePortraitContent
import com.android.quizapp.ui.theme.AppColor

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val scrollState = rememberScrollState()

    // TODO: Replace with actual user data from ViewModel/API
    val userImageUrl = "https://picsum.photos/id/237/200/300"
    val userName = "Sourashis Das"
    val userEmail = "sourashisdas@gmail.com"
    val userPhone = "+91 9876543210"
    val userBio = "Avid quiz enthusiast and lifelong learner. Love challenging myself with trivia!"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = AppColor.backgroundBrush)
    ) {
        val textTheme = MaterialTheme.typography
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
            // Header
            Text(
                text = "Profile",
                style = textTheme.displaySmall.copy(color = AppColor.brown)
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (isLandscape) {
                ProfileLandscapeContent(
                    imageUrl = userImageUrl,
                    userName = userName,
                    userEmail = userEmail,
                    userPhone = userPhone,
                    userBio = userBio,
                    onEditClick = {
                        navController.navigate(Screen.EditProfile.route)
                    }
                )
            } else {
                ProfilePortraitContent(
                    imageUrl = userImageUrl,
                    userName = userName,
                    userEmail = userEmail,
                    userPhone = userPhone,
                    userBio = userBio,
                    onEditClick = {
                        navController.navigate(Screen.EditProfile.route)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = NavHostController(LocalContext.current))
}



