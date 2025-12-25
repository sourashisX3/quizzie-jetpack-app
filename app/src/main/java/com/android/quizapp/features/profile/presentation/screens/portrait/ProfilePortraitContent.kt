package com.android.quizapp.features.profile.presentation.screens.portrait

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.quizapp.core.constants.StringConstants
import com.android.quizapp.core.shared.components.UserAvatar
import com.android.quizapp.ui.theme.AppColor

/**
 * Portrait layout for Profile Screen
 * Displays avatar, user info in vertical layout
 */
@Composable
fun ProfilePortraitContent(
    imageUrl: String?,
    userName: String,
    userEmail: String,
    userPhone: String,
    userBio: String,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val textTheme = MaterialTheme.typography

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Avatar
        UserAvatar(
            imageUrl = imageUrl,
            size = 120.dp,
            borderWidth = 2.dp,
            contentDescription = StringConstants.PROFILE,
            enableCache = true,
            showEditIcon = true,
            onEditClick = onEditClick
        )

        // User Name
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = userName,
            style = textTheme.titleLarge.copy(color = AppColor.brown)
        )

        // User Email
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = userEmail,
            style = textTheme.bodyLarge.copy(color = AppColor.grey)
        )

        // Phone Number
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = userPhone,
            style = textTheme.bodyMedium.copy(color = AppColor.grey)
        )

        // Bio Section
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "About Me",
            style = textTheme.titleMedium.copy(
                color = AppColor.brown,
                fontStyle = FontStyle.Italic
            )
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = userBio,
            style = textTheme.bodyMedium,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}

