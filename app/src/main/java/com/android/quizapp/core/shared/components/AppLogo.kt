package com.android.quizapp.core.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.android.quizapp.R
import com.android.quizapp.core.constants.StringConstants
import com.android.quizapp.ui.theme.AppColor

@Composable
fun AppLogo(modifier: Modifier = Modifier) {

    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val textTheme = androidx.compose.material3.MaterialTheme.typography
        val (appNameRef, logoRef) = createRefs()

        // --- app name ---
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.constrainAs(appNameRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            // --- quiz ---
            Text(text = StringConstants.QUIZ_TITLE, style = textTheme.displayMedium)
            // --- trophy icon ---
            Image(
                painter = painterResource(id = R.drawable.trophy),
                contentDescription = StringConstants.TROPHY_IC,
                modifier = Modifier
                    .size(48.dp)
                    .padding(start = 8.dp),
                colorFilter = ColorFilter.tint(AppColor.yellow, blendMode = BlendMode.SrcIn)
            )
        }
        // --- logo ---
        /*Image(
            modifier = Modifier
                .height(200.dp)
                .constrainAs(logoRef) {
                    top.linkTo(appNameRef.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            painter = painterResource(R.drawable.quiz_logo),
            contentDescription = StringConstants.QUIZ_TITLE
        )*/
    }
}