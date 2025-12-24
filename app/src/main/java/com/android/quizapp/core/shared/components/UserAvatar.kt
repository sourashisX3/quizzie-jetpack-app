package com.android.quizapp.core.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.android.quizapp.R
import com.android.quizapp.ui.theme.AppColor

@Composable
fun UserAvatar(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    size: Int = 100,
    contentDescription: String? = null,
    showEditIcon: Boolean = true,
    onEditClick: (() -> Unit)? = null,
) {
    val painter = rememberAsyncImagePainter(
        model = imageUrl,
        error = painterResource(R.drawable.avatar_1)
    )

    Box(
        modifier = modifier.size(size.dp),
        contentAlignment = Alignment.Center
    ) {

        // --- Avatar Image ---
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(size.dp)
                .clip(CircleShape)
                .border(1.dp, AppColor.brown, CircleShape)
        )

        // --- Loading State ---
        if (painter.state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                strokeWidth = 2.dp,
                color = AppColor.brown
            )
        }

        // --- Edit Icon ---
        if (showEditIcon && onEditClick != null) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.BottomEnd)
                    .clip(CircleShape)
                    .background(AppColor.brown)
                    .border(2.dp, AppColor.white, CircleShape)
                    .clickable { onEditClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Avatar",
                    tint = AppColor.white,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}

