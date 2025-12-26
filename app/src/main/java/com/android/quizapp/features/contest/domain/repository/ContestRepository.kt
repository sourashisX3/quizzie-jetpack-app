package com.android.quizapp.features.contest.domain.repository

import com.android.quizapp.features.contest.domain.entity.*
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for contest data
 */
interface ContestRepository {
    /**
     * Get all contests by type
     */
    suspend fun getContests(type: ContestType): Flow<Result<List<Contest>>>

    /**
     * Get all contests
     */
    suspend fun getAllContests(): Flow<Result<List<Contest>>>

    /**
     * Get contest by ID
     */
    suspend fun getContestById(contestId: String): Flow<Result<Contest>>

    /**
     * Enroll in a contest
     */
    suspend fun enrollInContest(contestId: String): Result<Contest>

    /**
     * Start a contest session
     */
    suspend fun startContest(contestId: String): Result<ContestSession>

    /**
     * Submit answer for a question
     */
    suspend fun submitAnswer(
        contestId: String,
        questionId: String,
        answerIndex: Int
    ): Result<Boolean>

    /**
     * Complete contest and get result
     */
    suspend fun completeContest(
        contestId: String,
        answers: Map<String, Int>,
        timeTaken: Long
    ): Result<ContestResult>

    /**
     * Get previous contest stats
     */
    suspend fun getPreviousContests(): Flow<Result<List<PreviousContestStats>>>

    /**
     * Get contest statistics for graphs
     */
    suspend fun getContestStatistics(): Flow<Result<ContestStatistics>>
}

