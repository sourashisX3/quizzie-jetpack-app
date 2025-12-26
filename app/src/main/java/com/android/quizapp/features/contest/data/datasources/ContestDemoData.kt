package com.android.quizapp.features.contest.data.datasources

import com.android.quizapp.core.shared.network.ApiResponse
import com.android.quizapp.features.contest.data.models.*

/**
 * Demo data source for contest feature
 */
object ContestDemoData {

    /**
     * Get all contests
     */
    fun getAllContests(): ContestsResponseDto {
        return ApiResponse(
            success = true,
            statusCode = 200,
            message = "Contests fetched successfully",
            data = ContestsDataDto(contests = getDemoContests()),
            timestamp = System.currentTimeMillis()
        )
    }

    /**
     * Get contests by type
     */
    fun getContestsByType(type: String): ContestsResponseDto {
        val filtered = getDemoContests().filter { it.type.equals(type, ignoreCase = true) }
        return ApiResponse(
            success = true,
            statusCode = 200,
            message = "Contests fetched successfully",
            data = ContestsDataDto(contests = filtered),
            timestamp = System.currentTimeMillis()
        )
    }

    /**
     * Get contest by ID
     */
    fun getContestById(contestId: String): ContestResponseDto {
        val contest = getDemoContests().find { it.id == contestId }
        return if (contest != null) {
            ApiResponse(
                success = true,
                statusCode = 200,
                message = "Contest fetched successfully",
                data = ContestDataDto(contest = contest),
                timestamp = System.currentTimeMillis()
            )
        } else {
            ApiResponse(
                success = false,
                statusCode = 404,
                message = "Contest not found",
                data = null,
                timestamp = System.currentTimeMillis()
            )
        }
    }

    /**
     * Start contest session
     */
    fun startContest(contestId: String): ContestSessionResponseDto {
        return ApiResponse(
            success = true,
            statusCode = 200,
            message = "Contest started successfully",
            data = ContestSessionDataDto(
                contestId = contestId,
                contestTitle = "Daily Quiz Challenge",
                questions = getDemoQuestions(),
                timePerQuestion = 30
            ),
            timestamp = System.currentTimeMillis()
        )
    }

    /**
     * Complete contest and get result
     */
    fun completeContest(
        contestId: String,
        answers: Map<String, Int>
    ): ContestResultResponseDto {
        val questions = getDemoQuestions()
        val correctAnswers = answers.count { (questionId, answerIndex) ->
            questions.find { it.id == questionId }?.correctAnswerIndex == answerIndex
        }
        val totalQuestions = questions.size
        val wrongAnswers = totalQuestions - correctAnswers
        val accuracy = (correctAnswers.toDouble() / totalQuestions) * 100
        val totalScore = correctAnswers * 10

        return ApiResponse(
            success = true,
            statusCode = 200,
            message = "Contest completed successfully",
            data = ContestResultDto(
                contestId = contestId,
                contestTitle = "Daily Quiz Challenge",
                totalQuestions = totalQuestions,
                correctAnswers = correctAnswers,
                wrongAnswers = wrongAnswers,
                totalScore = totalScore,
                maxScore = totalQuestions * 10,
                rank = (1..100).random(),
                totalParticipants = 500,
                timeTaken = 120,
                accuracy = accuracy,
                completedAt = System.currentTimeMillis()
            ),
            timestamp = System.currentTimeMillis()
        )
    }

    /**
     * Get previous contests
     */
    fun getPreviousContests(): PreviousContestsResponseDto {
        return ApiResponse(
            success = true,
            statusCode = 200,
            message = "Previous contests fetched successfully",
            data = PreviousContestsDataDto(contests = getDemoPreviousContests()),
            timestamp = System.currentTimeMillis()
        )
    }

    /**
     * Get contest statistics
     */
    fun getContestStatistics(): ContestStatisticsResponseDto {
        return ApiResponse(
            success = true,
            statusCode = 200,
            message = "Statistics fetched successfully",
            data = getDemoStatistics(),
            timestamp = System.currentTimeMillis()
        )
    }

    /**
     * Demo contests list
     */
    private fun getDemoContests(): List<ContestDto> {
        val now = System.currentTimeMillis()
        val oneDay = 24 * 60 * 60 * 1000L

        return listOf(
            ContestDto(
                id = "daily_001",
                title = "Daily Quiz Challenge",
                description = "Test your knowledge daily and compete with others!",
                type = "daily",
                difficulty = "medium",
                status = "ongoing",
                totalQuestions = 10,
                timePerQuestion = 30,
                totalParticipants = 1250,
                prizePool = "100 Coins",
                startTime = now - oneDay,
                endTime = now + oneDay,
                imageUrl = "https://i.pravatar.cc/300?img=1",
                isEnrolled = false,
                enrolledCount = 450
            ),
            ContestDto(
                id = "daily_002",
                title = "Science Trivia Daily",
                description = "Daily science questions to challenge your mind!",
                type = "daily",
                difficulty = "easy",
                status = "ongoing",
                totalQuestions = 15,
                timePerQuestion = 25,
                totalParticipants = 890,
                prizePool = "50 Coins",
                startTime = now - oneDay,
                endTime = now + oneDay,
                imageUrl = "https://i.pravatar.cc/300?img=2",
                isEnrolled = true,
                enrolledCount = 320
            ),
            ContestDto(
                id = "weekly_001",
                title = "Weekly Grand Challenge",
                description = "Weekly mega contest with bigger prizes!",
                type = "weekly",
                difficulty = "hard",
                status = "upcoming",
                totalQuestions = 25,
                timePerQuestion = 45,
                totalParticipants = 5000,
                prizePool = "500 Coins + Badge",
                startTime = now + 2 * oneDay,
                endTime = now + 9 * oneDay,
                imageUrl = "https://i.pravatar.cc/300?img=3",
                isEnrolled = false,
                enrolledCount = 1200
            ),
            ContestDto(
                id = "weekly_002",
                title = "Programming Weekly Contest",
                description = "Test your coding knowledge every week!",
                type = "weekly",
                difficulty = "expert",
                status = "upcoming",
                totalQuestions = 20,
                timePerQuestion = 60,
                totalParticipants = 2500,
                prizePool = "1000 Coins",
                startTime = now + 3 * oneDay,
                endTime = now + 10 * oneDay,
                imageUrl = "https://i.pravatar.cc/300?img=4",
                isEnrolled = true,
                enrolledCount = 850
            ),
            ContestDto(
                id = "monthly_001",
                title = "Monthly Championship",
                description = "The ultimate monthly challenge with premium rewards!",
                type = "monthly",
                difficulty = "expert",
                status = "upcoming",
                totalQuestions = 50,
                timePerQuestion = 60,
                totalParticipants = 10000,
                prizePool = "5000 Coins + Premium Badge",
                startTime = now + 7 * oneDay,
                endTime = now + 37 * oneDay,
                imageUrl = "https://i.pravatar.cc/300?img=5",
                isEnrolled = false,
                enrolledCount = 3500
            ),
            ContestDto(
                id = "monthly_002",
                title = "General Knowledge Marathon",
                description = "Monthly general knowledge mega contest!",
                type = "monthly",
                difficulty = "hard",
                status = "upcoming",
                totalQuestions = 40,
                timePerQuestion = 50,
                totalParticipants = 8000,
                prizePool = "3000 Coins",
                startTime = now + 10 * oneDay,
                endTime = now + 40 * oneDay,
                imageUrl = "https://i.pravatar.cc/300?img=6",
                isEnrolled = false,
                enrolledCount = 2100
            )
        )
    }

    /**
     * Demo questions
     */
    private fun getDemoQuestions(): List<ContestQuestionDto> {
        return listOf(
            ContestQuestionDto(
                id = "q1",
                questionText = "What is the capital of France?",
                options = listOf("London", "Berlin", "Paris", "Madrid"),
                correctAnswerIndex = 2,
                timeLimit = 30,
                points = 10
            ),
            ContestQuestionDto(
                id = "q2",
                questionText = "Which planet is known as the Red Planet?",
                options = listOf("Venus", "Mars", "Jupiter", "Saturn"),
                correctAnswerIndex = 1,
                timeLimit = 30,
                points = 10
            ),
            ContestQuestionDto(
                id = "q3",
                questionText = "What is 15 × 7?",
                options = listOf("95", "105", "115", "125"),
                correctAnswerIndex = 1,
                timeLimit = 30,
                points = 10
            ),
            ContestQuestionDto(
                id = "q4",
                questionText = "Who painted the Mona Lisa?",
                options = listOf("Van Gogh", "Picasso", "Leonardo da Vinci", "Michelangelo"),
                correctAnswerIndex = 2,
                timeLimit = 30,
                points = 10
            ),
            ContestQuestionDto(
                id = "q5",
                questionText = "What is the largest ocean on Earth?",
                options = listOf("Atlantic", "Indian", "Arctic", "Pacific"),
                correctAnswerIndex = 3,
                timeLimit = 30,
                points = 10
            )
        )
    }

    /**
     * Demo previous contests
     */
    private fun getDemoPreviousContests(): List<PreviousContestStatsDto> {
        val now = System.currentTimeMillis()
        val oneDay = 24 * 60 * 60 * 1000L

        return listOf(
            PreviousContestStatsDto(
                contestId = "prev_001",
                contestTitle = "Daily Quiz - Dec 20",
                contestType = "daily",
                score = 85,
                maxScore = 100,
                rank = 15,
                totalParticipants = 500,
                completedAt = now - 7 * oneDay,
                accuracy = 85.0
            ),
            PreviousContestStatsDto(
                contestId = "prev_002",
                contestTitle = "Weekly Challenge - Week 50",
                contestType = "weekly",
                score = 180,
                maxScore = 250,
                rank = 42,
                totalParticipants = 2500,
                completedAt = now - 14 * oneDay,
                accuracy = 72.0
            ),
            PreviousContestStatsDto(
                contestId = "prev_003",
                contestTitle = "Daily Quiz - Dec 18",
                contestType = "daily",
                score = 90,
                maxScore = 100,
                rank = 8,
                totalParticipants = 450,
                completedAt = now - 9 * oneDay,
                accuracy = 90.0
            ),
            PreviousContestStatsDto(
                contestId = "prev_004",
                contestTitle = "Daily Quiz - Dec 15",
                contestType = "daily",
                score = 75,
                maxScore = 100,
                rank = 32,
                totalParticipants = 520,
                completedAt = now - 12 * oneDay,
                accuracy = 75.0
            ),
            PreviousContestStatsDto(
                contestId = "prev_005",
                contestTitle = "Weekly Challenge - Week 49",
                contestType = "weekly",
                score = 200,
                maxScore = 250,
                rank = 28,
                totalParticipants = 2800,
                completedAt = now - 21 * oneDay,
                accuracy = 80.0
            )
        )
    }

    /**
     * Demo statistics
     */
    private fun getDemoStatistics(): ContestStatisticsDto {
        return ContestStatisticsDto(
            totalContestsParticipated = 25,
            averageScore = 82.5,
            averageRank = 25.5,
            bestRank = 3,
            totalPoints = 2062,
            weeklyStats = listOf(
                WeeklyContestStatDto(
                    weekNumber = 48,
                    contestsParticipated = 5,
                    averageScore = 78.0,
                    totalPoints = 390
                ),
                WeeklyContestStatDto(
                    weekNumber = 49,
                    contestsParticipated = 6,
                    averageScore = 82.0,
                    totalPoints = 492
                ),
                WeeklyContestStatDto(
                    weekNumber = 50,
                    contestsParticipated = 7,
                    averageScore = 85.0,
                    totalPoints = 595
                ),
                WeeklyContestStatDto(
                    weekNumber = 51,
                    contestsParticipated = 7,
                    averageScore = 83.0,
                    totalPoints = 581
                )
            ),
            monthlyStats = listOf(
                MonthlyContestStatDto(
                    month = "October",
                    contestsParticipated = 8,
                    averageScore = 75.0,
                    totalPoints = 600
                ),
                MonthlyContestStatDto(
                    month = "November",
                    contestsParticipated = 10,
                    averageScore = 80.0,
                    totalPoints = 800
                ),
                MonthlyContestStatDto(
                    month = "December",
                    contestsParticipated = 7,
                    averageScore = 88.0,
                    totalPoints = 616
                )
            )
        )
    }
}

