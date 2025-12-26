package com.android.quizapp.features.leaderboard.domain.entity

/**
 * Domain entity representing the complete leaderboard
 */
data class Leaderboard(
    val entries: List<LeaderboardEntry>,
    val currentUserEntry: LeaderboardEntry?,
    val totalParticipants: Int,
    val lastUpdated: Long, // Timestamp in milliseconds
    val paginationInfo: PaginationInfo? = null
)

/**
 * Domain entity for pagination information
 */
data class PaginationInfo(
    val currentPage: Int,
    val pageSize: Int,
    val totalPages: Int,
    val totalItems: Int,
    val hasNext: Boolean,
    val hasPrevious: Boolean
)

