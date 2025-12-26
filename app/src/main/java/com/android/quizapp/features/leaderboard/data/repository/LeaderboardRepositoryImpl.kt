package com.android.quizapp.features.leaderboard.data.repository

import com.android.quizapp.features.leaderboard.data.datasources.LeaderboardDemoData
import com.android.quizapp.features.leaderboard.data.mappers.toDomain
import com.android.quizapp.features.leaderboard.domain.entity.Leaderboard
import com.android.quizapp.features.leaderboard.domain.repository.LeaderboardRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation of LeaderboardRepository
 * Currently uses demo data, but structured to easily switch to API calls
 */
class LeaderboardRepositoryImpl @Inject constructor(
    // TODO: Inject API service when ready
    // private val leaderboardApiService: LeaderboardApiService
) : LeaderboardRepository {

    override suspend fun getLeaderboard(): Flow<Result<Leaderboard>> = flow {
        try {
            // Simulate network delay
            delay(500)

            // TODO: Replace with actual API call
            // val response = leaderboardApiService.getLeaderboard()
            val response = LeaderboardDemoData.getDemoLeaderboardResponse(page = 1, pageSize = 10)

            val leaderboard = response.toDomain()
            if (leaderboard != null) {
                emit(Result.success(leaderboard))
            } else {
                emit(Result.failure(Exception(response.message ?: "Failed to fetch leaderboard")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun getLeaderboard(limit: Int): Flow<Result<Leaderboard>> = flow {
        try {
            // Simulate network delay
            delay(500)

            // TODO: Replace with actual API call
            // val response = leaderboardApiService.getLeaderboard(limit)
            val response = LeaderboardDemoData.getDemoLeaderboardWithSize(limit)

            val leaderboard = response.toDomain()
            if (leaderboard != null) {
                emit(Result.success(leaderboard))
            } else {
                emit(Result.failure(Exception(response.message ?: "Failed to fetch leaderboard")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun getLeaderboard(page: Int, pageSize: Int): Flow<Result<Leaderboard>> = flow {
        try {
            // Simulate network delay
            delay(500)

            // TODO: Replace with actual API call
            // val response = leaderboardApiService.getLeaderboard(page, pageSize)
            val response = LeaderboardDemoData.getDemoLeaderboardResponse(page, pageSize)

            val leaderboard = response.toDomain()
            if (leaderboard != null) {
                emit(Result.success(leaderboard))
            } else {
                emit(Result.failure(Exception(response.message ?: "Failed to fetch leaderboard")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun refreshLeaderboard(): Result<Leaderboard> {
        return try {
            // Simulate network delay
            delay(300)

            // TODO: Replace with actual API call
            // val response = leaderboardApiService.getLeaderboard()
            val response = LeaderboardDemoData.getDemoLeaderboardResponse(page = 1, pageSize = 10)

            val leaderboard = response.toDomain()
            if (leaderboard != null) {
                Result.success(leaderboard)
            } else {
                Result.failure(Exception(response.message ?: "Failed to refresh leaderboard"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

