package com.android.quizapp.features.leaderboard.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.android.quizapp.features.leaderboard.domain.entity.BadgeColor
import com.android.quizapp.features.leaderboard.domain.entity.LeaderboardEntry

/**
 * Composable for displaying a single leaderboard entry
 */
@Composable
fun LeaderboardEntryItem(
    entry: LeaderboardEntry,
    modifier: Modifier = Modifier,
    isCurrentUser: Boolean = false
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isCurrentUser) Color(0xFFFFF8E1) else Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Rank Badge
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(getRankBadgeColor(entry.badgeColor)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${entry.rank}",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // User Avatar
            AsyncImage(
                model = entry.avatarUrl ?: "https://i.pravatar.cc/150",
                contentDescription = "Avatar of ${entry.username}",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // User Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = entry.username,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    maxLines = 1
                )
                Text(
                    text = "${entry.totalQuizzes} Quizzes",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                )
            }

            // Score Badge
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(getScoreBadgeColor(entry.rank))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "${entry.score}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }
        }
    }
}

/**
 * Get color for rank badge based on position
 */
private fun getRankBadgeColor(badgeColor: BadgeColor): Color {
    return when (badgeColor) {
        BadgeColor.GOLD -> Color(0xFFFFD700)
        BadgeColor.SILVER -> Color(0xFFC0C0C0)
        BadgeColor.BRONZE -> Color(0xFFCD7F32)
        BadgeColor.DEFAULT -> Color(0xFF9E9E9E)
    }
}

/**
 * Get color for score badge based on rank
 */
private fun getScoreBadgeColor(rank: Int): Color {
    return when (rank) {
        1 -> Color(0xFFFFD700)  // Gold
        2 -> Color(0xFF87CEEB)  // Silver/Blue
        3 -> Color(0xFFFF8C42)  // Bronze/Orange
        in 4..5 -> Color(0xFF4CAF50)  // Green
        in 6..7 -> Color(0xFFFFB74D)  // Yellow
        else -> Color(0xFF9C27B0)  // Purple
    }
}

