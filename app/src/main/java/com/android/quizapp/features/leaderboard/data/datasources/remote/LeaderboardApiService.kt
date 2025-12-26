package com.android.quizapp.features.leaderboard.data.datasources.remote

import com.android.quizapp.features.leaderboard.data.models.LeaderboardResponseDto

/**
 * API Service interface for Leaderboard endpoints
 * This interface defines the contract for leaderboard API calls
 *
 * TODO: Implement with Retrofit when backend is ready
 * Example:
 * @GET("leaderboard")
 * suspend fun getLeaderboard(
 *     @Query("page") page: Int = 1,
 *     @Query("page_size") pageSize: Int = 10
 * ): LeaderboardResponseDto
 */
interface LeaderboardApiService {

    /**
     * Get paginated leaderboard
     * @param page Page number (1-based)
     * @param pageSize Number of entries per page
     * @return LeaderboardResponseDto
     */
    suspend fun getLeaderboard(
        page: Int = 1,
        pageSize: Int = 10
    ): LeaderboardResponseDto

    /**
     * Get leaderboard with custom limit
     * @param limit Maximum number of entries to fetch
     * @return LeaderboardResponseDto
     */
    suspend fun getLeaderboardWithLimit(
        limit: Int
    ): LeaderboardResponseDto

    /**
     * Get user-specific leaderboard ranking
     * @param userId User ID to fetch ranking for
     * @return LeaderboardResponseDto
     */
    suspend fun getUserRanking(
        userId: String
    ): LeaderboardResponseDto
}

/**
 * Example Retrofit implementation (commented out until backend is ready)
 *
 * interface LeaderboardApiService {
 *     @GET("api/v1/leaderboard")
 *     suspend fun getLeaderboard(
 *         @Query("page") page: Int = 1,
 *         @Query("page_size") pageSize: Int = 10
 *     ): LeaderboardResponseDto
 *
 *     @GET("api/v1/leaderboard/top")
 *     suspend fun getLeaderboardWithLimit(
 *         @Query("limit") limit: Int
 *     ): LeaderboardResponseDto
 *
 *     @GET("api/v1/leaderboard/user/{userId}")
 *     suspend fun getUserRanking(
 *         @Path("userId") userId: String
 *     ): LeaderboardResponseDto
 * }
 */

