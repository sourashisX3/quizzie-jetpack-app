package com.android.quizapp.features.contest.domain.usecase

import com.android.quizapp.features.contest.domain.entity.ContestSession
import com.android.quizapp.features.contest.domain.repository.ContestRepository
import javax.inject.Inject

/**
 * Use case for starting a contest
 */
class StartContestUseCase @Inject constructor(
    private val repository: ContestRepository
) {
    suspend operator fun invoke(contestId: String): Result<ContestSession> {
        return repository.startContest(contestId)
    }
}

