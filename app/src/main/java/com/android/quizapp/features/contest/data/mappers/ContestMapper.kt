package com.android.quizapp.features.contest.data.mappers

import com.android.quizapp.core.shared.network.ApiResponse
import com.android.quizapp.features.contest.data.models.*
import com.android.quizapp.features.contest.domain.entity.*

/**
 * Map ContestDto to Contest domain entity
 */
fun ContestDto.toDomain(): Contest {
    return Contest(
        id = id,
        title = title,
        description = description,
        type = type.toContestType(),
        difficulty = difficulty.toContestDifficulty(),
        status = status.toContestStatus(),
        totalQuestions = totalQuestions,
        timePerQuestion = timePerQuestion,
        totalParticipants = totalParticipants,
        prizePool = prizePool,
        startTime = startTime,
        endTime = endTime,
        imageUrl = imageUrl,
        isEnrolled = isEnrolled,
        enrolledCount = enrolledCount
    )
}

/**
 * Map ApiResponse<ContestsDataDto> to List<Contest>
 */
fun ApiResponse<ContestsDataDto>.toContestList(): List<Contest>? {
    return if (success && data != null) {
        data.contests.map { it.toDomain() }
    } else {
        null
    }
}

/**
 * Map ApiResponse<ContestDataDto> to Contest
 */
fun ApiResponse<ContestDataDto>.toContest(): Contest? {
    return if (success && data != null) {
        data.contest.toDomain()
    } else {
        null
    }
}

/**
 * Map ContestQuestionDto to ContestQuestion domain entity
 */
fun ContestQuestionDto.toDomain(): ContestQuestion {
    return ContestQuestion(
        id = id,
        questionText = questionText,
        options = options,
        correctAnswerIndex = correctAnswerIndex,
        timeLimit = timeLimit,
        points = points
    )
}

/**
 * Map ApiResponse<ContestSessionDataDto> to ContestSession
 */
fun ApiResponse<ContestSessionDataDto>.toContestSession(): ContestSession? {
    return if (success && data != null) {
        ContestSession(
            contestId = data.contestId,
            contestTitle = data.contestTitle,
            questions = data.questions.map { it.toDomain() },
            timePerQuestion = data.timePerQuestion
        )
    } else {
        null
    }
}

/**
 * Map ApiResponse<ContestResultDto> to ContestResult
 */
fun ApiResponse<ContestResultDto>.toContestResult(): ContestResult? {
    return if (success && data != null) {
        ContestResult(
            contestId = data.contestId,
            contestTitle = data.contestTitle,
            totalQuestions = data.totalQuestions,
            correctAnswers = data.correctAnswers,
            wrongAnswers = data.wrongAnswers,
            totalScore = data.totalScore,
            maxScore = data.maxScore,
            rank = data.rank,
            totalParticipants = data.totalParticipants,
            timeTaken = data.timeTaken,
            accuracy = data.accuracy,
            completedAt = data.completedAt
        )
    } else {
        null
    }
}

/**
 * Map PreviousContestStatsDto to PreviousContestStats
 */
fun PreviousContestStatsDto.toDomain(): PreviousContestStats {
    return PreviousContestStats(
        contestId = contestId,
        contestTitle = contestTitle,
        contestType = contestType.toContestType(),
        score = score,
        maxScore = maxScore,
        rank = rank,
        totalParticipants = totalParticipants,
        completedAt = completedAt,
        accuracy = accuracy
    )
}

/**
 * Map ApiResponse<PreviousContestsDataDto> to List<PreviousContestStats>
 */
fun ApiResponse<PreviousContestsDataDto>.toPreviousContestsList(): List<PreviousContestStats>? {
    return if (success && data != null) {
        data.contests.map { it.toDomain() }
    } else {
        null
    }
}

/**
 * Map ApiResponse<ContestStatisticsDto> to ContestStatistics
 */
fun ApiResponse<ContestStatisticsDto>.toContestStatistics(): ContestStatistics? {
    return if (success && data != null) {
        ContestStatistics(
            totalContestsParticipated = data.totalContestsParticipated,
            averageScore = data.averageScore,
            averageRank = data.averageRank,
            bestRank = data.bestRank,
            totalPoints = data.totalPoints,
            weeklyStats = data.weeklyStats.map { it.toDomain() },
            monthlyStats = data.monthlyStats.map { it.toDomain() }
        )
    } else {
        null
    }
}

fun WeeklyContestStatDto.toDomain(): WeeklyContestStat {
    return WeeklyContestStat(
        weekNumber = weekNumber,
        contestsParticipated = contestsParticipated,
        averageScore = averageScore,
        totalPoints = totalPoints
    )
}

fun MonthlyContestStatDto.toDomain(): MonthlyContestStat {
    return MonthlyContestStat(
        month = month,
        contestsParticipated = contestsParticipated,
        averageScore = averageScore,
        totalPoints = totalPoints
    )
}

/**
 * String extensions to convert to enums
 */
fun String.toContestType(): ContestType {
    return when (this.lowercase()) {
        "daily" -> ContestType.DAILY
        "weekly" -> ContestType.WEEKLY
        "monthly" -> ContestType.MONTHLY
        "special" -> ContestType.SPECIAL
        else -> ContestType.DAILY
    }
}

fun String.toContestDifficulty(): ContestDifficulty {
    return when (this.lowercase()) {
        "easy" -> ContestDifficulty.EASY
        "medium" -> ContestDifficulty.MEDIUM
        "hard" -> ContestDifficulty.HARD
        "expert" -> ContestDifficulty.EXPERT
        else -> ContestDifficulty.MEDIUM
    }
}

fun String.toContestStatus(): ContestStatus {
    return when (this.lowercase()) {
        "upcoming" -> ContestStatus.UPCOMING
        "ongoing" -> ContestStatus.ONGOING
        "completed" -> ContestStatus.COMPLETED
        else -> ContestStatus.UPCOMING
    }
}
