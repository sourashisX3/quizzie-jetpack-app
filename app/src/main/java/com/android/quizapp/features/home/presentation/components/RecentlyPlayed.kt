package com.android.quizapp.features.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.android.quizapp.R
import com.android.quizapp.core.constants.StringConstants
import com.android.quizapp.core.shared.components.AppButton
import com.android.quizapp.ui.theme.AppColor

@Composable
fun RecentlyPlayed(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    onPlayNowClick: () -> Unit,
) {
    Row {
        ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
        ) {
            val (titleRef, subtitleRef, iconRef, buttonRef) = createRefs()

            // --- Icon ---
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .constrainAs(iconRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                painter = painterResource(R.drawable.game),
                contentDescription = StringConstants.GAME_IC,
                colorFilter = ColorFilter.tint(color = AppColor.blue)
            )
            // --- Title & Subtitle ---
            Text(
                title, style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.constrainAs(titleRef) {
                    top.linkTo(iconRef.top)
                    start.linkTo(iconRef.start, margin = 32.dp)
                }
            )
            Text(
                subtitle,
                style = MaterialTheme.typography.bodyMedium.copy(color = AppColor.grey),
                modifier = Modifier.constrainAs(subtitleRef) {
                    top.linkTo(titleRef.bottom, margin = 4.dp)
                    start.linkTo(titleRef.start)
                }
            )
            // --- Play Now Button ---
            AppButton(
                modifier = Modifier
                    .height(30.dp)
                    .constrainAs(buttonRef) {
                        end.linkTo(parent.end)
                        top.linkTo(titleRef.top)
                        bottom.linkTo(subtitleRef.bottom)
                    },
                onClick = onPlayNowClick,
                colors = ButtonDefaults.buttonColors(containerColor = AppColor.orange),
                buttonName = StringConstants.PLAY_AGAIN_BUTTON,
                textStyle = MaterialTheme.typography.bodyMedium.copy(color = AppColor.white),
                leadingIcon = {
                    Image(
                        painter = painterResource(R.drawable.play_button),
                        colorFilter = ColorFilter.tint(color = AppColor.white),
                        contentDescription = StringConstants.PLAY_AGAIN_BUTTON,
                        modifier = Modifier.size(16.dp)
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecentlyPlayedPreview() {
    RecentlyPlayed(
        title = "Science Quiz",
        subtitle = "Last played: 2 days ago",
        onPlayNowClick = {}
    )
}