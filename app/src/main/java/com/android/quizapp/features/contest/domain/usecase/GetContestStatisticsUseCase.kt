package com.android.quizapp.features.contest.domain.usecase

import com.android.quizapp.features.contest.domain.entity.ContestStatistics
import com.android.quizapp.features.contest.domain.repository.ContestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for getting contest statistics
 */
class GetContestStatisticsUseCase @Inject constructor(
    private val repository: ContestRepository
) {
    suspend operator fun invoke(): Flow<Result<ContestStatistics>> {
        return repository.getContestStatistics()
    }
}

