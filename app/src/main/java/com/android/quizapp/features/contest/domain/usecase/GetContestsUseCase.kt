package com.android.quizapp.features.contest.domain.usecase

import com.android.quizapp.features.contest.domain.entity.Contest
import com.android.quizapp.features.contest.domain.entity.ContestType
import com.android.quizapp.features.contest.domain.repository.ContestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for getting contests
 */
class GetContestsUseCase @Inject constructor(
    private val repository: ContestRepository
) {
    suspend operator fun invoke(type: ContestType): Flow<Result<List<Contest>>> {
        return repository.getContests(type)
    }

    suspend operator fun invoke(): Flow<Result<List<Contest>>> {
        return repository.getAllContests()
    }
}
