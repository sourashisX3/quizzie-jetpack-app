package com.android.quizapp.features.profile.presentation.screens.landscape

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
 * Landscape layout for Profile Screen
 * Displays avatar on left and user info on right
 */
@Composable
fun ProfileLandscapeContent(
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Left side - Avatar
            Column(
                modifier = Modifier.weight(0.4f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UserAvatar(
                    imageUrl = imageUrl,
                    size = 140.dp,
                    borderWidth = 2.dp,
                    contentDescription = StringConstants.PROFILE,
                    enableCache = true,
                    showEditIcon = true,
                    onEditClick = onEditClick
                )
            }

            // Right side - User Info
            Column(
                modifier = Modifier.weight(0.6f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = userName,
                    style = textTheme.titleLarge.copy(color = AppColor.brown)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = userEmail,
                    style = textTheme.bodyLarge.copy(color = AppColor.grey)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = userPhone,
                    style = textTheme.bodyMedium.copy(color = AppColor.grey)
                )

                Spacer(modifier = Modifier.height(16.dp))

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
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

