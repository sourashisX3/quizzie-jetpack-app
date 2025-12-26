package com.android.quizapp.features.leaderboard.data.datasources

import com.android.quizapp.core.shared.network.ApiResponse
import com.android.quizapp.core.shared.network.Pagination
import com.android.quizapp.features.leaderboard.data.models.LeaderboardDataDto
import com.android.quizapp.features.leaderboard.data.models.LeaderboardEntryDto
import com.android.quizapp.features.leaderboard.data.models.LeaderboardResponseDto

/**
 * Demo data source providing mock leaderboard data
 * This will be replaced with actual API calls in the future
 */
object LeaderboardDemoData {

    /**
     * Generate demo leaderboard response
     */
    fun getDemoLeaderboardResponse(page: Int = 1, pageSize: Int = 10): LeaderboardResponseDto {
        val allEntries = getDemoLeaderboardEntries()
        val totalItems = allEntries.size
        val totalPages = (totalItems + pageSize - 1) / pageSize
        val startIndex = (page - 1) * pageSize
        val endIndex = minOf(startIndex + pageSize, totalItems)

        val paginatedEntries = if (startIndex < totalItems) {
            allEntries.subList(startIndex, endIndex)
        } else {
            emptyList()
        }

        return ApiResponse(
            success = true,
            statusCode = 200,
            message = "Leaderboard fetched successfully",
            data = LeaderboardDataDto(
                entries = paginatedEntries,
                currentUserEntry = getCurrentUserEntry(),
                totalParticipants = 1250,
                lastUpdated = System.currentTimeMillis()
            ),
            timestamp = System.currentTimeMillis(),
            pagination = Pagination(
                currentPage = page,
                pageSize = pageSize,
                totalPages = totalPages,
                totalItems = totalItems,
                hasNext = page < totalPages,
                hasPrevious = page > 1
            )
        )
    }

    /**
     * Demo leaderboard entries based on the UI mockup
     */
    private fun getDemoLeaderboardEntries(): List<LeaderboardEntryDto> {
        return listOf(
            LeaderboardEntryDto(
                userId = "user_001",
                username = "Sophia Cho",
                avatarUrl = "https://i.pravatar.cc/150?img=1",
                score = 960,
                rank = 1,
                totalQuizzes = 32,
                accuracy = 96.0
            ),
            LeaderboardEntryDto(
                userId = "user_002",
                username = "Emma Ema",
                avatarUrl = "https://i.pravatar.cc/150?img=5",
                score = 895,
                rank = 2,
                totalQuizzes = 30,
                accuracy = 92.3
            ),
            LeaderboardEntryDto(
                userId = "user_003",
                username = "Andrew W",
                avatarUrl = "https://i.pravatar.cc/150?img=12",
                score = 880,
                rank = 3,
                totalQuizzes = 28,
                accuracy = 91.5
            ),
            LeaderboardEntryDto(
                userId = "user_004",
                username = "Bayu aji sadewa",
                avatarUrl = "https://i.pravatar.cc/150?img=8",
                score = 820,
                rank = 4,
                totalQuizzes = 25,
                accuracy = 89.2
            ),
            LeaderboardEntryDto(
                userId = "user_005",
                username = "Olivia Ava",
                avatarUrl = "https://i.pravatar.cc/150?img=9",
                score = 805,
                rank = 5,
                totalQuizzes = 24,
                accuracy = 87.8
            ),
            LeaderboardEntryDto(
                userId = "user_006",
                username = "David Joshua",
                avatarUrl = "https://i.pravatar.cc/150?img=13",
                score = 792,
                rank = 6,
                totalQuizzes = 23,
                accuracy = 86.5
            ),
            LeaderboardEntryDto(
                userId = "user_007",
                username = "Charlotte Harper",
                avatarUrl = "https://i.pravatar.cc/150?img=10",
                score = 775,
                rank = 7,
                totalQuizzes = 22,
                accuracy = 85.1
            ),
            LeaderboardEntryDto(
                userId = "user_008",
                username = "Mia Evelyn",
                avatarUrl = "https://i.pravatar.cc/150?img=20",
                score = 740,
                rank = 8,
                totalQuizzes = 21,
                accuracy = 83.7
            ),
            LeaderboardEntryDto(
                userId = "user_009",
                username = "James Smith",
                avatarUrl = "https://i.pravatar.cc/150?img=15",
                score = 720,
                rank = 9,
                totalQuizzes = 20,
                accuracy = 82.4
            ),
            LeaderboardEntryDto(
                userId = "user_010",
                username = "Isabella Brown",
                avatarUrl = "https://i.pravatar.cc/150?img=16",
                score = 695,
                rank = 10,
                totalQuizzes = 19,
                accuracy = 81.0
            ),
            // Additional demo entries for pagination testing
            LeaderboardEntryDto(
                userId = "user_011",
                username = "Liam Wilson",
                avatarUrl = "https://i.pravatar.cc/150?img=17",
                score = 680,
                rank = 11,
                totalQuizzes = 18,
                accuracy = 79.5
            ),
            LeaderboardEntryDto(
                userId = "user_012",
                username = "Ava Martinez",
                avatarUrl = "https://i.pravatar.cc/150?img=18",
                score = 665,
                rank = 12,
                totalQuizzes = 17,
                accuracy = 78.2
            ),
            LeaderboardEntryDto(
                userId = "user_013",
                username = "Noah Garcia",
                avatarUrl = "https://i.pravatar.cc/150?img=19",
                score = 650,
                rank = 13,
                totalQuizzes = 16,
                accuracy = 77.0
            ),
            LeaderboardEntryDto(
                userId = "user_014",
                username = "Sophia Rodriguez",
                avatarUrl = "https://i.pravatar.cc/150?img=21",
                score = 635,
                rank = 14,
                totalQuizzes = 15,
                accuracy = 75.8
            ),
            LeaderboardEntryDto(
                userId = "user_015",
                username = "Mason Lopez",
                avatarUrl = "https://i.pravatar.cc/150?img=22",
                score = 620,
                rank = 15,
                totalQuizzes = 14,
                accuracy = 74.5
            )
        )
    }

    /**
     * Current user entry (if logged in)
     */
    private fun getCurrentUserEntry(): LeaderboardEntryDto {
        return LeaderboardEntryDto(
            userId = "current_user",
            username = "You",
            avatarUrl = "https://i.pravatar.cc/150?img=25",
            score = 650,
            rank = 13,
            totalQuizzes = 16,
            accuracy = 77.0
        )
    }

    /**
     * Generate demo data with custom size (backwards compatibility)
     */
    fun getDemoLeaderboardWithSize(size: Int): LeaderboardResponseDto {
        val entries = getDemoLeaderboardEntries().take(size)
        return ApiResponse(
            success = true,
            statusCode = 200,
            message = "Leaderboard fetched successfully",
            data = LeaderboardDataDto(
                entries = entries,
                currentUserEntry = getCurrentUserEntry(),
                totalParticipants = 1250,
                lastUpdated = System.currentTimeMillis()
            ),
            timestamp = System.currentTimeMillis(),
            pagination = null
        )
    }

    /**
     * Simulate API error response
     */
    fun getErrorResponse(): LeaderboardResponseDto {
        return ApiResponse(
            success = false,
            statusCode = 500,
            message = "Failed to fetch leaderboard",
            data = null,
            timestamp = System.currentTimeMillis(),
            pagination = null
        )
    }
}

