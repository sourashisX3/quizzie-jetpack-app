package com.android.quizapp.features.contest.domain.usecase

import com.android.quizapp.features.contest.domain.entity.ContestResult
import com.android.quizapp.features.contest.domain.repository.ContestRepository
import javax.inject.Inject

/**
 * Use case for completing a contest
 */
class CompleteContestUseCase @Inject constructor(
    private val repository: ContestRepository
) {
    suspend operator fun invoke(
        contestId: String,
        answers: Map<String, Int>,
        timeTaken: Long
    ): Result<ContestResult> {
        return repository.completeContest(contestId, answers, timeTaken)
    }
}

