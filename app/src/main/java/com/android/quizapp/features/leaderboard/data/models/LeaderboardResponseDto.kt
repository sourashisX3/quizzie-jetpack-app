package com.android.quizapp.features.leaderboard.data.models

import com.android.quizapp.core.shared.network.ApiResponse
import com.google.gson.annotations.SerializedName

/**
 * Type alias for Leaderboard API Response
 * Uses the common ApiResponse wrapper with LeaderboardDataDto as the data payload
 */
typealias LeaderboardResponseDto = ApiResponse<LeaderboardDataDto>

/**
 * Data Transfer Object for leaderboard data payload
 * This is the actual data structure returned by the API
 */
data class LeaderboardDataDto(
    @SerializedName("entries")
    val entries: List<LeaderboardEntryDto>,

    @SerializedName("current_user")
    val currentUserEntry: LeaderboardEntryDto?,

    @SerializedName("total_participants")
    val totalParticipants: Int,

    @SerializedName("last_updated")
    val lastUpdated: Long
)

