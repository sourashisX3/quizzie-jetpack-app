package com.android.quizapp.features.profile.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.quizapp.R
import com.android.quizapp.core.shared.components.AppTextField

/**
 * Reusable form fields for editing profile information
 */
@Composable
fun EditProfileFormFields(
    name: String,
    onNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    phone: String,
    onPhoneChange: (String) -> Unit,
    bio: String,
    onBioChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Name Field
        AppTextField(
            value = name,
            onValueChange = onNameChange,
            label = "Full Name",
            placeholder = "Enter your full name",
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Name"
                )
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Email Field
        AppTextField(
            value = email,
            onValueChange = onEmailChange,
            label = "Email",
            placeholder = "Enter your email",
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.email),
                    contentDescription = "Email"
                )
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Phone Field
        AppTextField(
            value = phone,
            onValueChange = onPhoneChange,
            label = "Phone Number",
            placeholder = "Enter your phone number",
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.phone),
                    contentDescription = "Phone"
                )
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Bio Field
        AppTextField(
            value = bio,
            onValueChange = onBioChange,
            label = "Bio",
            placeholder = "Tell us about yourself",
            singleLine = false,
            maxLines = 4
        )
    }
}

