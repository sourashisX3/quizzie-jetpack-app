package com.android.quizapp.core.shared.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.android.quizapp.ui.theme.AppColor

/**
 * Production-ready UserAvatar composable that displays user profile images.
 *
 * Features:
 * - Async image loading with Coil
 * - Loading, error, and success states
 * - Placeholder and fallback support
 * - Disk and memory caching for API images
 * - Customizable size, border, and colors
 * - Optional edit icon with click handler
 * - Support for both local resources and remote URLs
 *
 * @param modifier Modifier for the avatar container
 * @param imageUrl Remote image URL or null for fallback
 * @param imageResId Local drawable resource ID as fallback (null to use default)
 * @param size Size of the avatar in dp (default: 100)
 * @param borderWidth Width of the avatar border in dp (default: 1)
 * @param borderColor Color of the avatar border (default: AppColor.brown)
 * @param backgroundColor Background color for loading/error states (default: AppColor.brown.copy(0.1f))
 * @param contentDescription Accessibility description
 * @param showEditIcon Whether to show the edit icon (default: false)
 * @param editIconSize Size of the edit icon in dp (default: 28)
 * @param onEditClick Callback when edit icon is clicked
 * @param enableCache Enable disk and memory caching (default: true, useful for API images)
 * @param crossfadeEnabled Enable crossfade animation (default: true)
 */
@Composable
fun UserAvatar(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    @DrawableRes imageResId: Int? = null,
    size: Dp = 100.dp,
    borderWidth: Dp = 1.dp,
    borderColor: Color = AppColor.brown,
    backgroundColor: Color = AppColor.brown.copy(alpha = 0.1f),
    contentDescription: String? = "User Avatar",
    showEditIcon: Boolean = false,
    editIconSize: Dp = 28.dp,
    onEditClick: (() -> Unit)? = null,
    enableCache: Boolean = true,
    crossfadeEnabled: Boolean = true,
) {
    val context = LocalContext.current

    // Build image request with proper caching and configuration
    val imageRequest = remember(imageUrl, imageResId) {
        ImageRequest.Builder(context)
            .data(imageUrl ?: imageResId)
            .crossfade(crossfadeEnabled)
            .apply {
                if (enableCache) {
                    diskCachePolicy(CachePolicy.ENABLED)
                    memoryCachePolicy(CachePolicy.ENABLED)
                    networkCachePolicy(CachePolicy.ENABLED)
                } else {
                    diskCachePolicy(CachePolicy.DISABLED)
                    memoryCachePolicy(CachePolicy.DISABLED)
                }
            }
            .build()
    }

    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        // Avatar Image with SubcomposeAsyncImage for better state handling
        SubcomposeAsyncImage(
            model = imageRequest,
            contentDescription = contentDescription,
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(backgroundColor)
                .border(borderWidth, borderColor, CircleShape),
            contentScale = ContentScale.Crop,
        ) {
            val state = painter.state

            when (state) {
                is AsyncImagePainter.State.Loading -> {
                    // Loading State
                    Box(
                        modifier = Modifier
                            .size(size)
                            .background(backgroundColor),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size((size.value * 0.3f).dp),
                            strokeWidth = 2.dp,
                            color = borderColor
                        )
                    }
                }

                is AsyncImagePainter.State.Error -> {
                    // Error State - Show fallback
                    Box(
                        modifier = Modifier
                            .size(size)
                            .background(backgroundColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Default Avatar",
                            tint = borderColor.copy(alpha = 0.6f),
                            modifier = Modifier.size((size.value * 0.5f).dp)
                        )
                    }
                }

                is AsyncImagePainter.State.Success -> {
                    // Success State - Show image
                    SubcomposeAsyncImageContent()
                }

                else -> {
                    // Empty state
                    Box(
                        modifier = Modifier
                            .size(size)
                            .background(backgroundColor)
                    )
                }
            }
        }

        // Edit Icon Overlay
        if (showEditIcon && onEditClick != null) {
            Box(
                modifier = Modifier
                    .size(editIconSize)
                    .align(Alignment.BottomEnd)
                    .clip(CircleShape)
                    .background(borderColor)
                    .border(2.dp, AppColor.white, CircleShape)
                    .clickable { onEditClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Avatar",
                    tint = AppColor.white,
                    modifier = Modifier.size((editIconSize.value * 0.5f).dp)
                )
            }
        }
    }
}

