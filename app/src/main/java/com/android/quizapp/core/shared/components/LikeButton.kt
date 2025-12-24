package com.android.quizapp.core.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.quizapp.R
import com.android.quizapp.core.constants.StringConstants
import com.android.quizapp.ui.theme.AppColor

@Composable
fun LikeButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .size(36.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(color = AppColor.white),
        contentAlignment = Alignment.Center,
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(36.dp)
        ) {
            Image(
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(color = AppColor.red, blendMode = BlendMode.SrcIn),
                painter = painterResource(R.drawable.heart),
                contentDescription = StringConstants.LIKE_BUTTON
            )
        }
    }
}