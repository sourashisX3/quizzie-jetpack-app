package com.android.quizapp.features.profile.presentation.screens.landscape

import android.net.Uri
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
import androidx.compose.ui.unit.dp
import com.android.quizapp.core.shared.components.AppButton
import com.android.quizapp.core.shared.components.UserAvatar
import com.android.quizapp.features.profile.presentation.components.EditProfileFormFields
import com.android.quizapp.ui.theme.AppColor

/**
 * Landscape layout for Edit Profile Screen
 * Displays avatar on left and form fields on right
 */
@Composable
fun EditProfileLandscapeContent(
    imageUri: Uri?,
    imageUrl: String?,
    name: String,
    onNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    phone: String,
    onPhoneChange: (String) -> Unit,
    bio: String,
    onBioChange: (String) -> Unit,
    onChangeImageClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val textTheme = MaterialTheme.typography

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.Top
    ) {
        // Left side - Avatar
        Column(
            modifier = Modifier.weight(0.35f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserAvatar(
                imageUrl = imageUri?.toString() ?: imageUrl,
                size = 140.dp,
                borderWidth = 2.dp,
                showEditIcon = true,
                onEditClick = onChangeImageClick,
                enableCache = false
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Tap to change photo",
                style = textTheme.bodySmall.copy(color = AppColor.brown.copy(alpha = 0.7f))
            )
        }

        // Right side - Form Fields
        Column(
            modifier = Modifier.weight(0.65f)
        ) {
            EditProfileFormFields(
                name = name,
                onNameChange = onNameChange,
                email = email,
                onEmailChange = onEmailChange,
                phone = phone,
                onPhoneChange = onPhoneChange,
                bio = bio,
                onBioChange = onBioChange
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Save Button
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onSaveClick,
                buttonName = "Save Changes"
            )
        }
    }
}

