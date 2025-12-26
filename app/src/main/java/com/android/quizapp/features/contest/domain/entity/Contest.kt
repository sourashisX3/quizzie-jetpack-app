package com.android.quizapp.features.contest.domain.entity

/**
 * Domain entity representing a contest
 */
data class Contest(
    val id: String,
    val title: String,
    val description: String,
    val type: ContestType,
    val difficulty: ContestDifficulty,
    val status: ContestStatus,
    val totalQuestions: Int,
    val timePerQuestion: Int, // in seconds
    val totalParticipants: Int,
    val prizePool: String?,
    val startTime: Long,
    val endTime: Long,
    val imageUrl: String?,
    val isEnrolled: Boolean = false,
    val enrolledCount: Int = 0
)

/**
 * Contest types
 */
enum class ContestType {
    DAILY,
    WEEKLY,
    MONTHLY,
    SPECIAL
}

/**
 * Contest difficulty levels
 */
enum class ContestDifficulty {
    EASY,
    MEDIUM,
    HARD,
    EXPERT
}

/**
 * Contest status
 */
enum class ContestStatus {
    UPCOMING,
    ONGOING,
    COMPLETED
}

/**
 * Domain entity for contest question
 */
data class ContestQuestion(
    val id: String,
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val timeLimit: Int, // in seconds
    val points: Int
)

/**
 * Domain entity for contest session (active contest)
 */
data class ContestSession(
    val contestId: String,
    val contestTitle: String,
    val questions: List<ContestQuestion>,
    val currentQuestionIndex: Int = 0,
    val answers: Map<String, Int> = emptyMap(), // questionId to selectedOptionIndex
    val startTime: Long = System.currentTimeMillis(),
    val timePerQuestion: Int,
    val totalScore: Int = 0
)

/**
 * Domain entity for contest result
 */
data class ContestResult(
    val contestId: String,
    val contestTitle: String,
    val totalQuestions: Int,
    val correctAnswers: Int,
    val wrongAnswers: Int,
    val totalScore: Int,
    val maxScore: Int,
    val rank: Int,
    val totalParticipants: Int,
    val timeTaken: Long, // in seconds
    val accuracy: Double, // percentage
    val completedAt: Long
)

/**
 * Domain entity for previous contest stats
 */
data class PreviousContestStats(
    val contestId: String,
    val contestTitle: String,
    val contestType: ContestType,
    val score: Int,
    val maxScore: Int,
    val rank: Int,
    val totalParticipants: Int,
    val completedAt: Long,
    val accuracy: Double
)

/**
 * Domain entity for contest statistics (for graph)
 */
data class ContestStatistics(
    val totalContestsParticipated: Int,
    val averageScore: Double,
    val averageRank: Double,
    val bestRank: Int,
    val totalPoints: Int,
    val weeklyStats: List<WeeklyContestStat>,
    val monthlyStats: List<MonthlyContestStat>
)

data class WeeklyContestStat(
    val weekNumber: Int,
    val contestsParticipated: Int,
    val averageScore: Double,
    val totalPoints: Int
)

data class MonthlyContestStat(
    val month: String,
    val contestsParticipated: Int,
    val averageScore: Double,
    val totalPoints: Int
)


