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
 * Reusable form fields for Signup Screen
 * Includes: Name, Email, Password, and Confirm Password fields
 */
@Composable
fun SignupFormFields(
    name: String,
    onNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    confirmPasswordVisible: Boolean,
    onConfirmPasswordVisibilityChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    // Name Field
    AppTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        label = StringConstants.NAME_LABEL,
        placeholder = StringConstants.NAME_LABEL,
        value = name,
        onValueChange = onNameChange,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.profile),
                contentDescription = StringConstants.NAME_LABEL
            )
        }
    )

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

    // Confirm Password Field
    AppTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        label = StringConstants.CONFIRM_PASSWORD_LABEL,
        placeholder = StringConstants.CONFIRM_PASSWORD_LABEL,
        value = confirmPassword,
        onValueChange = onConfirmPasswordChange,
        visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.lock),
                contentDescription = StringConstants.PASSWORD_LABEL
            )
        },
        trailingIcon = {
            IconButton(onClick = { onConfirmPasswordVisibilityChange(!confirmPasswordVisible) }) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = if (confirmPasswordVisible) painterResource(R.drawable.eye) else painterResource(
                        R.drawable.eye_crossed
                    ),
                    contentDescription = if (confirmPasswordVisible) "Hide Password" else "Show Password"
                )
            }
        }
    )
}
