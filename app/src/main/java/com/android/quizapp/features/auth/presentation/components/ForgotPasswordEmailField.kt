package com.android.quizapp.features.auth.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.quizapp.R
import com.android.quizapp.core.constants.StringConstants
import com.android.quizapp.core.shared.components.AppTextField

/**
 * Reusable email field component for Forgot Password Screen
 */
@Composable
fun ForgotPasswordEmailField(
    email: String,
    onEmailChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
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
}

