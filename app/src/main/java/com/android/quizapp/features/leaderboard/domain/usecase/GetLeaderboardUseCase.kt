package com.android.quizapp.features.leaderboard.domain.usecase

import com.android.quizapp.features.leaderboard.domain.entity.Leaderboard
import com.android.quizapp.features.leaderboard.domain.repository.LeaderboardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for getting leaderboard data
 * Encapsulates the business logic for fetching leaderboard
 */
class GetLeaderboardUseCase @Inject constructor(
    private val repository: LeaderboardRepository
) {
    suspend operator fun invoke(): Flow<Result<Leaderboard>> {
        return repository.getLeaderboard()
    }

    suspend operator fun invoke(limit: Int): Flow<Result<Leaderboard>> {
        return repository.getLeaderboard(limit)
    }

    suspend operator fun invoke(page: Int, pageSize: Int): Flow<Result<Leaderboard>> {
        return repository.getLeaderboard(page, pageSize)
    }
}


