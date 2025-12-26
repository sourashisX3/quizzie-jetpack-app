package com.android.quizapp.features.contest.data.repository

import com.android.quizapp.features.contest.data.datasources.ContestDemoData
import com.android.quizapp.features.contest.data.mappers.*
import com.android.quizapp.features.contest.domain.entity.*
import com.android.quizapp.features.contest.domain.repository.ContestRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation of ContestRepository
 */
class ContestRepositoryImpl @Inject constructor(
    // TODO: Inject API service when ready
    // private val contestApiService: ContestApiService
) : ContestRepository {

    override suspend fun getContests(type: ContestType): Flow<Result<List<Contest>>> = flow {
        try {
            delay(500) // Simulate network delay

            val response = ContestDemoData.getContestsByType(type.name.lowercase())
            val contests = response.toContestList()

            if (contests != null) {
                emit(Result.success(contests))
            } else {
                emit(Result.failure(Exception(response.message ?: "Failed to fetch contests")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun getAllContests(): Flow<Result<List<Contest>>> = flow {
        try {
            delay(500)

            val response = ContestDemoData.getAllContests()
            val contests = response.toContestList()

            if (contests != null) {
                emit(Result.success(contests))
            } else {
                emit(Result.failure(Exception(response.message ?: "Failed to fetch contests")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun getContestById(contestId: String): Flow<Result<Contest>> = flow {
        try {
            delay(300)

            val response = ContestDemoData.getContestById(contestId)
            val contest = response.toContest()

            if (contest != null) {
                emit(Result.success(contest))
            } else {
                emit(Result.failure(Exception(response.message ?: "Contest not found")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun enrollInContest(contestId: String): Result<Contest> {
        return try {
            delay(300)

            val response = ContestDemoData.getContestById(contestId)
            val contest = response.toContest()

            if (contest != null) {
                // Update enrollment status
                val updatedContest = contest.copy(isEnrolled = true, enrolledCount = contest.enrolledCount + 1)
                Result.success(updatedContest)
            } else {
                Result.failure(Exception(response.message ?: "Failed to enroll"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun startContest(contestId: String): Result<ContestSession> {
        return try {
            delay(500)

            val response = ContestDemoData.startContest(contestId)
            val session = response.toContestSession()

            if (session != null) {
                Result.success(session)
            } else {
                Result.failure(Exception(response.message ?: "Failed to start contest"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun submitAnswer(
        contestId: String,
        questionId: String,
        answerIndex: Int
    ): Result<Boolean> {
        return try {
            delay(100)
            // In real implementation, this would send answer to server
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun completeContest(
        contestId: String,
        answers: Map<String, Int>,
        timeTaken: Long
    ): Result<ContestResult> {
        return try {
            delay(500)

            val response = ContestDemoData.completeContest(contestId, answers)
            val result = response.toContestResult()

            if (result != null) {
                Result.success(result)
            } else {
                Result.failure(Exception(response.message ?: "Failed to complete contest"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPreviousContests(): Flow<Result<List<PreviousContestStats>>> = flow {
        try {
            delay(500)

            val response = ContestDemoData.getPreviousContests()
            val contests = response.toPreviousContestsList()

            if (contests != null) {
                emit(Result.success(contests))
            } else {
                emit(Result.failure(Exception(response.message ?: "Failed to fetch previous contests")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun getContestStatistics(): Flow<Result<ContestStatistics>> = flow {
        try {
            delay(500)

            val response = ContestDemoData.getContestStatistics()
            val statistics = response.toContestStatistics()

            if (statistics != null) {
                emit(Result.success(statistics))
            } else {
                emit(Result.failure(Exception(response.message ?: "Failed to fetch statistics")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}

