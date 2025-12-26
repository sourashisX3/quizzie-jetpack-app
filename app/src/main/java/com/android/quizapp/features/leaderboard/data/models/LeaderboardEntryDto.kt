package com.android.quizapp.features.leaderboard.data.models

import com.google.gson.annotations.SerializedName

/**
 * Data Transfer Object for leaderboard entry from API
 * Matches the expected API response structure
 */
data class LeaderboardEntryDto(
    @SerializedName("user_id")
    val userId: String,

    @SerializedName("username")
    val username: String,

    @SerializedName("avatar_url")
    val avatarUrl: String?,

    @SerializedName("score")
    val score: Int,

    @SerializedName("rank")
    val rank: Int,

    @SerializedName("total_quizzes")
    val totalQuizzes: Int,

    @SerializedName("accuracy")
    val accuracy: Double
)


