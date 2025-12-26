package com.android.quizapp.features.leaderboard.domain.repository

import com.android.quizapp.features.leaderboard.domain.entity.Leaderboard
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for leaderboard data
 * Follows clean architecture principles
 */
interface LeaderboardRepository {
    /**
     * Get the global leaderboard
     * @return Flow of Leaderboard wrapped in Result
     */
    suspend fun getLeaderboard(): Flow<Result<Leaderboard>>

    /**
     * Get leaderboard with specific limit
     * @param limit Number of top entries to fetch
     */
    suspend fun getLeaderboard(limit: Int): Flow<Result<Leaderboard>>

    /**
     * Get paginated leaderboard
     * @param page Page number (1-based)
     * @param pageSize Number of entries per page
     */
    suspend fun getLeaderboard(page: Int, pageSize: Int): Flow<Result<Leaderboard>>

    /**
     * Refresh leaderboard data
     */
    suspend fun refreshLeaderboard(): Result<Leaderboard>
}

