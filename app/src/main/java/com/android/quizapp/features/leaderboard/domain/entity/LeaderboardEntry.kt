package com.android.quizapp.features.leaderboard.domain.entity

/**
 * Domain entity representing a single leaderboard entry
 * This is the clean, business logic model used throughout the app
 */
data class LeaderboardEntry(
    val userId: String,
    val username: String,
    val avatarUrl: String?,
    val score: Int,
    val rank: Int,
    val totalQuizzes: Int,
    val accuracy: Double, // Percentage (0-100)
    val badgeColor: BadgeColor = BadgeColor.DEFAULT
)

/**
 * Badge colors for top performers
 */
enum class BadgeColor {
    GOLD,      // 1st place
    SILVER,    // 2nd place
    BRONZE,    // 3rd place
    DEFAULT    // Other ranks
}


