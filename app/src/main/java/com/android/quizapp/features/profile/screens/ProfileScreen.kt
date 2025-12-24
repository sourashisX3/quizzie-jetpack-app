package com.android.quizapp.features.profile.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.quizapp.core.constants.StringConstants
import com.android.quizapp.core.shared.components.UserAvatar

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val textTheme = MaterialTheme.typography;

        // --- Header ---
        Text(text = "Profile Screen", style = textTheme.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        // --- Profile Info ---
        UserAvatar(
            imageUrl = "https://avatar.iran.liara.run/public/27",
            contentDescription = StringConstants.PROFILE,
            showEditIcon = true,
            onEditClick = {/*TODO*/}
        )

        // --- User Name ---
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "John Doe", style = textTheme.titleMedium)

        // --- User Email ---
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "sourashisdas@gmail.com", style = textTheme.bodyMedium)

        // --- phone number ---
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "+91 9876543210", style = textTheme.bodyMedium)

        // --- bio ---
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Avid quiz enthusiast and lifelong learner.", style = textTheme.bodyMedium)
    }
}

