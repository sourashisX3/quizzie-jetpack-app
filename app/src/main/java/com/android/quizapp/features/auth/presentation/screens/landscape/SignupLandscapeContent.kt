package com.android.quizapp.features.auth.presentation.screens.landscape

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.quizapp.core.constants.StringConstants
import com.android.quizapp.core.shared.components.AppButton
import com.android.quizapp.core.shared.components.AppLogo
import com.android.quizapp.core.shared.components.TextAndTextButton
import com.android.quizapp.features.auth.presentation.components.SignupFormFields

/**
 * Landscape layout for Signup Screen
 * Displays logo/title on left side and form fields on right side
 */
@Composable
fun SignupLandscapeContent(
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
    onSignupClick: () -> Unit,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val textTheme = MaterialTheme.typography

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.Top
    ) {
        // Left side - Logo and Header
        Column(
            modifier = Modifier
                .weight(0.4f)
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            AppLogo()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = StringConstants.SIGNUP_SCREEN_TITLE,
                style = textTheme.titleLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = StringConstants.SIGNUP_SCREEN_SUBTITLE,
                style = textTheme.bodyLarge
            )
        }

        // Right side - Form
        Column(
            modifier = Modifier.weight(0.6f)
        ) {
            SignupFormFields(
                name = name,
                onNameChange = onNameChange,
                email = email,
                onEmailChange = onEmailChange,
                password = password,
                onPasswordChange = onPasswordChange,
                passwordVisible = passwordVisible,
                onPasswordVisibilityChange = onPasswordVisibilityChange,
                confirmPassword = confirmPassword,
                onConfirmPasswordChange = onConfirmPasswordChange,
                confirmPasswordVisible = confirmPasswordVisible,
                onConfirmPasswordVisibilityChange = onConfirmPasswordVisibilityChange
            )

            Spacer(modifier = Modifier.height(16.dp))

            AppButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onSignupClick,
                buttonName = StringConstants.SIGNUP_BUTTON
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextAndTextButton(
                text = StringConstants.ALREADY_HAVE_ACCOUNT_TEXT,
                textButton = StringConstants.LOGIN_BUTTON,
                onTextButtonClick = onLoginClick,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
