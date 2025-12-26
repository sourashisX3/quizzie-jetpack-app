package com.android.quizapp.features.leaderboard.domain.usecase

import com.android.quizapp.features.leaderboard.domain.entity.Leaderboard
import com.android.quizapp.features.leaderboard.domain.repository.LeaderboardRepository
import javax.inject.Inject

/**
 * Use case for refreshing leaderboard data
 */
class RefreshLeaderboardUseCase @Inject constructor(
    private val repository: LeaderboardRepository
) {
    suspend operator fun invoke(): Result<Leaderboard> {
        return repository.refreshLeaderboard()
    }
}

