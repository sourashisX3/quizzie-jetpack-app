package com.android.quizapp.features.onboarding.screens.landscape

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
 * Landscape layout for Onboarding Screen
 * Displays logo on left side and welcome content on right side
 */
@Composable
fun OnboardingLandscapeContent(
    onGetStartedClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val textTheme = MaterialTheme.typography

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left side - Logo and App Name
        Column(
            modifier = Modifier.weight(0.45f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                        .size(48.dp)
                        .padding(start = 4.dp),
                    colorFilter = ColorFilter.tint(AppColor.yellow, blendMode = BlendMode.SrcIn)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Quiz Logo
            Image(
                painter = painterResource(R.drawable.quiz_logo),
                contentDescription = StringConstants.QUIZ_TITLE,
                modifier = Modifier.size(180.dp)
            )
        }

        // Right side - Welcome Content and Button
        Column(
            modifier = Modifier.weight(0.55f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Get Started Button
            AppButton(
                modifier = Modifier.width(220.dp),
                onClick = onGetStartedClick,
                buttonName = StringConstants.GET_STARTED_BUTTON
            )
        }
    }
}

