package com.android.quizapp.features.onboarding.screens.portrait

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.quizapp.R
import com.android.quizapp.core.constants.StringConstants
import com.android.quizapp.core.shared.components.AppButton
import com.android.quizapp.ui.theme.AppColor

/**
 * Portrait layout for Onboarding Screen
 * Displays app name, logo, welcome message, and get started button in vertical layout
 */
@Composable
fun OnboardingPortraitContent(
    onGetStartedClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val textTheme = MaterialTheme.typography

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(56.dp))

        // App Name with Trophy Icon
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = StringConstants.QUIZ_TITLE,
                style = textTheme.displayLarge
            )
            Image(
                painter = painterResource(id = R.drawable.trophy),
                contentDescription = StringConstants.TROPHY_IC,
                modifier = Modifier
                    .size(64.dp)
                    .padding(start = 8.dp),
                colorFilter = ColorFilter.tint(AppColor.yellow, blendMode = BlendMode.SrcIn)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Quiz Logo
        Image(
            painter = painterResource(R.drawable.quiz_logo),
            contentDescription = StringConstants.QUIZ_TITLE,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Welcome Title
        Text(
            text = StringConstants.ONBOARDING_WELCOME_TITLE_MESSAGE,
            style = textTheme.titleLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Description
        Text(
            text = StringConstants.ONBOARDING_WELCOME_SUBTITLE_MESSAGE,
            style = textTheme.bodyMedium.copy(color = AppColor.grey),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Get Started Button
        AppButton(
            modifier = Modifier
                .width(250.dp)
                .padding(bottom = 32.dp),
            onClick = onGetStartedClick,
            buttonName = StringConstants.GET_STARTED_BUTTON
        )
    }
}

