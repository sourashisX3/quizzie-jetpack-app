package com.android.quizapp.features.leaderboard.data.mappers

import com.android.quizapp.core.shared.network.ApiResponse
import com.android.quizapp.core.shared.network.Pagination
import com.android.quizapp.features.leaderboard.data.models.LeaderboardDataDto
import com.android.quizapp.features.leaderboard.data.models.LeaderboardEntryDto
import com.android.quizapp.features.leaderboard.domain.entity.BadgeColor
import com.android.quizapp.features.leaderboard.domain.entity.Leaderboard
import com.android.quizapp.features.leaderboard.domain.entity.LeaderboardEntry
import com.android.quizapp.features.leaderboard.domain.entity.PaginationInfo

/**
 * Extension function to map LeaderboardEntryDto to domain LeaderboardEntry
 */
fun LeaderboardEntryDto.toDomain(): LeaderboardEntry {
    return LeaderboardEntry(
        userId = userId,
        username = username,
        avatarUrl = avatarUrl,
        score = score,
        rank = rank,
        totalQuizzes = totalQuizzes,
        accuracy = accuracy,
        badgeColor = when (rank) {
            1 -> BadgeColor.GOLD
            2 -> BadgeColor.SILVER
            3 -> BadgeColor.BRONZE
            else -> BadgeColor.DEFAULT
        }
    )
}

/**
 * Extension function to map Pagination to domain PaginationInfo
 */
fun Pagination.toDomain(): PaginationInfo {
    return PaginationInfo(
        currentPage = currentPage,
        pageSize = pageSize,
        totalPages = totalPages,
        totalItems = totalItems,
        hasNext = hasNext,
        hasPrevious = hasPrevious
    )
}

/**
 * Extension function to map ApiResponse<LeaderboardDataDto> to domain Leaderboard
 */
fun ApiResponse<LeaderboardDataDto>.toDomain(): Leaderboard? {
    return if (success && data != null) {
        Leaderboard(
            entries = data.entries.map { it.toDomain() },
            currentUserEntry = data.currentUserEntry?.toDomain(),
            totalParticipants = data.totalParticipants,
            lastUpdated = data.lastUpdated,
            paginationInfo = pagination?.toDomain()
        )
    } else {
        null
    }
}

/**
 * Extension function to map domain LeaderboardEntry to DTO (for API requests if needed)
 */
fun LeaderboardEntry.toDto(): LeaderboardEntryDto {
    return LeaderboardEntryDto(
        userId = userId,
        username = username,
        avatarUrl = avatarUrl,
        score = score,
        rank = rank,
        totalQuizzes = totalQuizzes,
        accuracy = accuracy
    )
}

