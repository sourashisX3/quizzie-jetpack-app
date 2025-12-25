package com.android.quizapp.core.shared.components.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.android.quizapp.R
import com.android.quizapp.core.shared.components.UserAvatar
import com.android.quizapp.ui.theme.AppColor

/**
 * Examples of UserAvatar usage for different scenarios.
 * This file demonstrates how to use the UserAvatar component across the app.
 *
 * Usage Scenarios:
 * 1. Static/Local Images (Current)
 * 2. API Images (Future)
 * 3. Profile Screen with Edit
 * 4. Leaderboard Items
 * 5. Comments/Posts
 */

/**
 * Example 1: Basic Avatar with Local Resource
 * Use this for static data or when you have a local drawable
 */
@Composable
fun BasicAvatarExample() {
    UserAvatar(
        imageResId = R.drawable.avatar_1,
        size = 80.dp,
        contentDescription = "User Profile Picture"
    )
}

/**
 * Example 2: Avatar with Remote URL (API Ready)
 * Use this when fetching user data from API
 * The component automatically handles loading, error, and success states
 */
@Composable
fun RemoteAvatarExample(userAvatarUrl: String?) {
    UserAvatar(
        imageUrl = userAvatarUrl, // Can be null, will fallback to default
        size = 100.dp,
        contentDescription = "User Avatar",
        enableCache = true, // Enable caching for API images
        crossfadeEnabled = true // Smooth transition
    )
}

/**
 * Example 3: Profile Screen Avatar with Edit Button
 * Use this on user profile screens where users can change their avatar
 */
@Composable
fun ProfileAvatarExample(
    userAvatarUrl: String?,
    onEditClick: () -> Unit
) {
    UserAvatar(
        imageUrl = userAvatarUrl,
        imageResId = R.drawable.avatar_1, // Fallback
        size = 120.dp,
        borderWidth = 2.dp,
        borderColor = AppColor.brown,
        showEditIcon = true,
        editIconSize = 36.dp,
        onEditClick = onEditClick,
        contentDescription = "User Profile Picture - Tap to edit"
    )
}

/**
 * Example 4: Small Avatar for List Items (Leaderboard, Comments, etc.)
 * Use this in lists, cards, or anywhere you need a compact avatar
 */
@Composable
fun SmallAvatarExample(
    userAvatarUrl: String?,
    userName: String
) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserAvatar(
            imageUrl = userAvatarUrl,
            imageResId = R.drawable.avatar_2, // Different fallback
            size = 40.dp,
            borderWidth = 1.dp,
            contentDescription = "$userName's avatar"
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = userName)
    }
}

/**
 * Example 5: Custom Styled Avatar
 * Use this when you need specific colors or styling for different user types
 */
@Composable
fun CustomStyledAvatarExample(
    userAvatarUrl: String?,
    isPremium: Boolean = false
) {
    UserAvatar(
        imageUrl = userAvatarUrl,
        size = 90.dp,
        borderWidth = if (isPremium) 3.dp else 1.dp,
        borderColor = if (isPremium) Color(0xFFFFD700) else AppColor.brown,
        backgroundColor = if (isPremium) Color(0xFFFFD700).copy(alpha = 0.1f) else AppColor.brown.copy(
            alpha = 0.1f
        ),
        contentDescription = if (isPremium) "Premium User Avatar" else "User Avatar"
    )
}

/**
 * Example 6: Multiple Avatar Sizes for Different Screens
 * Demonstrates responsive sizing
 */
@Composable
fun MultiSizeAvatarExample(userAvatarUrl: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Profile Screen")
        UserAvatar(imageUrl = userAvatarUrl, size = 120.dp)

        Spacer(modifier = Modifier.height(8.dp))

        Text("Settings Screen")
        UserAvatar(imageUrl = userAvatarUrl, size = 80.dp)

        Spacer(modifier = Modifier.height(8.dp))

        Text("List Item")
        UserAvatar(imageUrl = userAvatarUrl, size = 48.dp)

        Spacer(modifier = Modifier.height(8.dp))

        Text("Mini Badge")
        UserAvatar(imageUrl = userAvatarUrl, size = 32.dp, borderWidth = 0.5.dp)
    }
}

/**
 * Example 7: API Data Model Integration
 * This shows how to integrate with your API response models
 */
data class User(
    val id: String,
    val name: String,
    val email: String,
    val avatarUrl: String?, // This comes from your API
    val isPremium: Boolean = false
)

@Composable
fun UserAvatarFromApiModel(user: User) {
    UserAvatar(
        imageUrl = user.avatarUrl, // Direct from API
        size = 80.dp,
        borderColor = if (user.isPremium) Color(0xFFFFD700) else AppColor.brown,
        contentDescription = "${user.name}'s profile picture",
        enableCache = true // Important for API images
    )
}

/**
 * Example 8: Leaderboard Item with Avatar
 * Complete example for leaderboard usage
 */
@Composable
fun LeaderboardItemExample(
    rank: Int,
    userName: String,
    userAvatarUrl: String?,
    score: Int
) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Rank
        Text(
            text = "#$rank",
            modifier = Modifier.width(40.dp)
        )

        // Avatar
        UserAvatar(
            imageUrl = userAvatarUrl,
            size = 50.dp,
            borderWidth = 1.5.dp,
            contentDescription = "$userName's avatar"
        )

        Spacer(modifier = Modifier.width(12.dp))

        // User Info
        Column(modifier = Modifier.weight(1f)) {
            Text(text = userName)
            Text(text = "$score points")
        }
    }
}

