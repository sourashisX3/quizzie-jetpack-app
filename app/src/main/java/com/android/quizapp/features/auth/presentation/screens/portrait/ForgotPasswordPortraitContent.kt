package com.android.quizapp.features.auth.presentation.screens.portrait

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.android.quizapp.features.auth.presentation.components.ForgotPasswordEmailField

/**
 * Portrait layout for Forgot Password Screen
 * Displays logo, title, email field, and actions in vertical layout
 */
@Composable
fun ForgotPasswordPortraitContent(
    email: String,
    onEmailChange: (String) -> Unit,
    onResetPasswordClick: () -> Unit,
    onBackToLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val textTheme = MaterialTheme.typography

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppLogo()

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = StringConstants.RESET_PASSWORD_SCREEN_TITLE,
            style = textTheme.titleLarge,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = StringConstants.RESET_PASSWORD_SCREEN_SUBTITLE,
            style = textTheme.bodyLarge,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(32.dp))

        ForgotPasswordEmailField(
            email = email,
            onEmailChange = onEmailChange
        )

        Spacer(modifier = Modifier.height(32.dp))

        AppButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onResetPasswordClick,
            buttonName = StringConstants.RESET_PASSWORD_BUTTON
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextAndTextButton(
            text = "Remember your password?",
            textButton = StringConstants.LOGIN_BUTTON,
            onTextButtonClick = onBackToLoginClick,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}
