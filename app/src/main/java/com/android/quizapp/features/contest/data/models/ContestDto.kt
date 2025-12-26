package com.android.quizapp.features.contest.data.models

import com.android.quizapp.core.shared.network.ApiResponse
import com.google.gson.annotations.SerializedName

/**
 * Type alias for Contest API Response
 */
typealias ContestResponseDto = ApiResponse<ContestDataDto>

/**
 * Type alias for Contest List API Response
 */
typealias ContestsResponseDto = ApiResponse<ContestsDataDto>

/**
 * Contests data payload
 */
data class ContestsDataDto(
    @SerializedName("contests")
    val contests: List<ContestDto>
)

/**
 * Single contest data payload
 */
data class ContestDataDto(
    @SerializedName("contest")
    val contest: ContestDto
)

/**
 * Contest DTO
 */
data class ContestDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("type")
    val type: String, // "daily", "weekly", "monthly", "special"

    @SerializedName("difficulty")
    val difficulty: String, // "easy", "medium", "hard", "expert"

    @SerializedName("status")
    val status: String, // "upcoming", "ongoing", "completed"

    @SerializedName("total_questions")
    val totalQuestions: Int,

    @SerializedName("time_per_question")
    val timePerQuestion: Int,

    @SerializedName("total_participants")
    val totalParticipants: Int,

    @SerializedName("prize_pool")
    val prizePool: String?,

    @SerializedName("start_time")
    val startTime: Long,

    @SerializedName("end_time")
    val endTime: Long,

    @SerializedName("image_url")
    val imageUrl: String?,

    @SerializedName("is_enrolled")
    val isEnrolled: Boolean = false,

    @SerializedName("enrolled_count")
    val enrolledCount: Int = 0
)

/**
 * Contest question DTO
 */
data class ContestQuestionDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("question_text")
    val questionText: String,

    @SerializedName("options")
    val options: List<String>,

    @SerializedName("correct_answer_index")
    val correctAnswerIndex: Int,

    @SerializedName("time_limit")
    val timeLimit: Int,

    @SerializedName("points")
    val points: Int
)

/**
 * Contest session DTO
 */
typealias ContestSessionResponseDto = ApiResponse<ContestSessionDataDto>

data class ContestSessionDataDto(
    @SerializedName("contest_id")
    val contestId: String,

    @SerializedName("contest_title")
    val contestTitle: String,

    @SerializedName("questions")
    val questions: List<ContestQuestionDto>,

    @SerializedName("time_per_question")
    val timePerQuestion: Int
)

/**
 * Contest result DTO
 */
typealias ContestResultResponseDto = ApiResponse<ContestResultDto>

data class ContestResultDto(
    @SerializedName("contest_id")
    val contestId: String,

    @SerializedName("contest_title")
    val contestTitle: String,

    @SerializedName("total_questions")
    val totalQuestions: Int,

    @SerializedName("correct_answers")
    val correctAnswers: Int,

    @SerializedName("wrong_answers")
    val wrongAnswers: Int,

    @SerializedName("total_score")
    val totalScore: Int,

    @SerializedName("max_score")
    val maxScore: Int,

    @SerializedName("rank")
    val rank: Int,

    @SerializedName("total_participants")
    val totalParticipants: Int,

    @SerializedName("time_taken")
    val timeTaken: Long,

    @SerializedName("accuracy")
    val accuracy: Double,

    @SerializedName("completed_at")
    val completedAt: Long
)

/**
 * Previous contest stats DTO
 */
typealias PreviousContestsResponseDto = ApiResponse<PreviousContestsDataDto>

data class PreviousContestsDataDto(
    @SerializedName("contests")
    val contests: List<PreviousContestStatsDto>
)

data class PreviousContestStatsDto(
    @SerializedName("contest_id")
    val contestId: String,

    @SerializedName("contest_title")
    val contestTitle: String,

    @SerializedName("contest_type")
    val contestType: String,

    @SerializedName("score")
    val score: Int,

    @SerializedName("max_score")
    val maxScore: Int,

    @SerializedName("rank")
    val rank: Int,

    @SerializedName("total_participants")
    val totalParticipants: Int,

    @SerializedName("completed_at")
    val completedAt: Long,

    @SerializedName("accuracy")
    val accuracy: Double
)

/**
 * Contest statistics DTO
 */
typealias ContestStatisticsResponseDto = ApiResponse<ContestStatisticsDto>

data class ContestStatisticsDto(
    @SerializedName("total_contests_participated")
    val totalContestsParticipated: Int,

    @SerializedName("average_score")
    val averageScore: Double,

    @SerializedName("average_rank")
    val averageRank: Double,

    @SerializedName("best_rank")
    val bestRank: Int,

    @SerializedName("total_points")
    val totalPoints: Int,

    @SerializedName("weekly_stats")
    val weeklyStats: List<WeeklyContestStatDto>,

    @SerializedName("monthly_stats")
    val monthlyStats: List<MonthlyContestStatDto>
)

data class WeeklyContestStatDto(
    @SerializedName("week_number")
    val weekNumber: Int,

    @SerializedName("contests_participated")
    val contestsParticipated: Int,

    @SerializedName("average_score")
    val averageScore: Double,

    @SerializedName("total_points")
    val totalPoints: Int
)

data class MonthlyContestStatDto(
    @SerializedName("month")
    val month: String,

    @SerializedName("contests_participated")
    val contestsParticipated: Int,

    @SerializedName("average_score")
    val averageScore: Double,

    @SerializedName("total_points")
    val totalPoints: Int
)

