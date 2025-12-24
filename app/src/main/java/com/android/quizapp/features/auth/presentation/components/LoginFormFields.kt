package com.android.quizapp.features.auth.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.android.quizapp.R
import com.android.quizapp.core.constants.StringConstants
import com.android.quizapp.core.shared.components.AppTextField

/**
 * Reusable form fields for Login Screen
 * Includes: Email and Password fields
 */
@Composable
fun LoginFormFields(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    // Email Field
    AppTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        label = StringConstants.EMAIL_LABEL,
        placeholder = StringConstants.EMAIL_LABEL,
        value = email,
        onValueChange = onEmailChange,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.email),
                contentDescription = StringConstants.EMAIL_LABEL
            )
        }
    )

    // Password Field
    AppTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        label = StringConstants.PASSWORD_LABEL,
        placeholder = StringConstants.PASSWORD_LABEL,
        value = password,
        onValueChange = onPasswordChange,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.lock),
                contentDescription = StringConstants.PASSWORD_LABEL
            )
        },
        trailingIcon = {
            IconButton(onClick = { onPasswordVisibilityChange(!passwordVisible) }) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = if (passwordVisible) painterResource(R.drawable.eye) else painterResource(
                        R.drawable.eye_crossed
                    ),
                    contentDescription = if (passwordVisible) "Hide Password" else "Show Password"
                )
            }
        }
    )
}

