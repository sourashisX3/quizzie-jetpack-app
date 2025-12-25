package com.android.quizapp.features.profile.presentation.screens

import android.content.res.Configuration
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.quizapp.R
import com.android.quizapp.core.shared.components.BackButtonAndTitle
import com.android.quizapp.features.profile.presentation.screens.landscape.EditProfileLandscapeContent
import com.android.quizapp.features.profile.presentation.screens.portrait.EditProfilePortraitContent
import com.android.quizapp.ui.theme.AppColor

@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    // Configuration
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val scrollState = rememberScrollState()

    // Form State - TODO: Replace with ViewModel
    var name by remember { mutableStateOf("Sourashis Das") }
    var email by remember { mutableStateOf("sourashisdas@gmail.com") }
    var phone by remember { mutableStateOf("+91 9876543210") }
    var bio by remember { mutableStateOf("Avid quiz enthusiast and lifelong learner. Love challenging myself with trivia!") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val currentImageUrl = "https://picsum.photos/id/237/200/300"

    // Image Picker Launcher
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
        }
    )

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
                    top = if (isLandscape) 8.dp else 16.dp,
                    bottom = 16.dp
                )
        ) {
            // Top Bar with Back Button and Title

            BackButtonAndTitle(
                title = "Edit Profile",
                onBackClick = { navController.navigateUp() }
            )

            Spacer(modifier = Modifier.padding(if (isLandscape) 12.dp else 16.dp))

            // Content - Portrait or Landscape
            if (isLandscape) {
                EditProfileLandscapeContent(
                    imageUri = selectedImageUri,
                    imageUrl = currentImageUrl,
                    name = name,
                    onNameChange = { name = it },
                    email = email,
                    onEmailChange = { email = it },
                    phone = phone,
                    onPhoneChange = { phone = it },
                    bio = bio,
                    onBioChange = { bio = it },
                    onChangeImageClick = {
                        // Launch photo picker
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    onSaveClick = {
                        // TODO: Save data via ViewModel/API
                        // For now, just navigate back
                        navController.navigateUp()
                    }
                )
            } else {
                EditProfilePortraitContent(
                    imageUri = selectedImageUri,
                    imageUrl = currentImageUrl,
                    name = name,
                    onNameChange = { name = it },
                    email = email,
                    onEmailChange = { email = it },
                    phone = phone,
                    onPhoneChange = { phone = it },
                    bio = bio,
                    onBioChange = { bio = it },
                    onChangeImageClick = {
                        // Launch photo picker
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    onSaveClick = {
                        // TODO: Save data via ViewModel/API
                        // For now, just navigate back
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen(navController = NavHostController(LocalContext.current))
}

