package com.android.quizapp.features.example.data.models

import com.android.quizapp.core.shared.network.ApiResponse
import com.google.gson.annotations.SerializedName

/**
 * EXAMPLE: User Profile Feature Implementation
 * This demonstrates how to use the common ApiResponse architecture
 * for a new feature following the same pattern as Leaderboard
 */

// ============================================
// 1. Define your DTO (Data Transfer Object)
// ============================================

/**
 * User Profile DTO - matches API response structure
 */
data class UserProfileDto(
    @SerializedName("user_id")
    val userId: String,

    @SerializedName("username")
    val username: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("avatar_url")
    val avatarUrl: String?,

    @SerializedName("total_score")
    val totalScore: Int,

    @SerializedName("total_quizzes")
    val totalQuizzes: Int,

    @SerializedName("rank")
    val rank: Int,

    @SerializedName("joined_date")
    val joinedDate: Long,

    @SerializedName("achievements")
    val achievements: List<AchievementDto>
)

data class AchievementDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("icon_url")
    val iconUrl: String?,

    @SerializedName("earned_date")
    val earnedDate: Long
)

// ============================================
// 2. Create type alias using ApiResponse
// ============================================

/**
 * Type alias for User Profile API Response
 * This automatically includes: success, statusCode, message, data, timestamp, pagination
 */
typealias UserProfileResponseDto = ApiResponse<UserProfileDto>

/**
 * For list responses (e.g., user's quiz history)
 */
data class QuizHistoryDataDto(
    @SerializedName("quizzes")
    val quizzes: List<QuizHistoryItemDto>,

    @SerializedName("total_count")
    val totalCount: Int
)

data class QuizHistoryItemDto(
    @SerializedName("quiz_id")
    val quizId: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("score")
    val score: Int,

    @SerializedName("max_score")
    val maxScore: Int,

    @SerializedName("completed_date")
    val completedDate: Long
)

typealias QuizHistoryResponseDto = ApiResponse<QuizHistoryDataDto>

// ============================================
// 3. Example API Response JSON
// ============================================

/**
 * Expected API Response from backend:
 *
 * {
 *   "success": true,
 *   "status_code": 200,
 *   "message": "Profile fetched successfully",
 *   "data": {
 *     "user_id": "user_123",
 *     "username": "john_doe",
 *     "email": "john@example.com",
 *     "avatar_url": "https://...",
 *     "total_score": 850,
 *     "total_quizzes": 25,
 *     "rank": 15,
 *     "joined_date": 1703721600000,
 *     "achievements": [
 *       {
 *         "id": "ach_1",
 *         "title": "First Quiz",
 *         "description": "Complete your first quiz",
 *         "icon_url": "https://...",
 *         "earned_date": 1703721600000
 *       }
 *     ]
 *   },
 *   "timestamp": 1703721600000,
 *   "pagination": null
 * }
 *
 * For paginated response (Quiz History):
 * {
 *   "success": true,
 *   "status_code": 200,
 *   "message": "Quiz history fetched",
 *   "data": {
 *     "quizzes": [...],
 *     "total_count": 25
 *   },
 *   "timestamp": 1703721600000,
 *   "pagination": {
 *     "current_page": 1,
 *     "page_size": 10,
 *     "total_pages": 3,
 *     "total_items": 25,
 *     "has_next": true,
 *     "has_previous": false
 *   }
 * }
 */

// ============================================
// 4. Domain Models (Business Logic Layer)
// ============================================

/**
 * Domain entity - used throughout the app
 * This is independent of API structure
 */
data class UserProfile(
    val userId: String,
    val username: String,
    val email: String,
    val avatarUrl: String?,
    val totalScore: Int,
    val totalQuizzes: Int,
    val rank: Int,
    val joinedDate: Long,
    val achievements: List<Achievement>
)

data class Achievement(
    val id: String,
    val title: String,
    val description: String,
    val iconUrl: String?,
    val earnedDate: Long
)

// ============================================
// 5. Mapper Functions
// ============================================

/**
 * Extension function to map DTO to Domain
 */
fun UserProfileDto.toDomain(): UserProfile {
    return UserProfile(
        userId = userId,
        username = username,
        email = email,
        avatarUrl = avatarUrl,
        totalScore = totalScore,
        totalQuizzes = totalQuizzes,
        rank = rank,
        joinedDate = joinedDate,
        achievements = achievements.map { it.toDomain() }
    )
}

fun AchievementDto.toDomain(): Achievement {
    return Achievement(
        id = id,
        title = title,
        description = description,
        iconUrl = iconUrl,
        earnedDate = earnedDate
    )
}

/**
 * Map ApiResponse to Domain (handles success/failure)
 */
fun ApiResponse<UserProfileDto>.toDomain(): UserProfile? {
    return if (success && data != null) {
        data.toDomain()
    } else {
        null
    }
}

// ============================================
// 6. API Service Interface
// ============================================

/**
 * API Service - ready for Retrofit implementation
 */
interface UserProfileApiService {
    /**
     * Get user profile by ID
     * @GET("api/v1/users/{userId}/profile")
     */
    suspend fun getUserProfile(userId: String): UserProfileResponseDto

    /**
     * Update user profile
     * @PUT("api/v1/users/{userId}/profile")
     */
    suspend fun updateUserProfile(
        userId: String,
        profile: UserProfileDto
    ): UserProfileResponseDto

    /**
     * Get user's quiz history with pagination
     * @GET("api/v1/users/{userId}/quiz-history")
     */
    suspend fun getQuizHistory(
        userId: String,
        page: Int,
        pageSize: Int
    ): QuizHistoryResponseDto
}

// ============================================
// 7. Repository Interface (Domain Layer)
// ============================================

/**
 * Repository interface - defines contract
 */
interface UserProfileRepository {
    suspend fun getUserProfile(userId: String): Result<UserProfile>
    suspend fun updateUserProfile(profile: UserProfile): Result<UserProfile>
    suspend fun getQuizHistory(userId: String, page: Int, pageSize: Int): Result<List<QuizHistoryItem>>
}

data class QuizHistoryItem(
    val quizId: String,
    val title: String,
    val score: Int,
    val maxScore: Int,
    val completedDate: Long
)

// ============================================
// 8. Repository Implementation (Data Layer)
// ============================================

/**
 * Repository implementation
 */
class UserProfileRepositoryImpl(
    private val apiService: UserProfileApiService
) : UserProfileRepository {

    override suspend fun getUserProfile(userId: String): Result<UserProfile> {
        return try {
            val response = apiService.getUserProfile(userId)
            val profile = response.toDomain()

            if (profile != null) {
                Result.success(profile)
            } else {
                Result.failure(Exception(response.message ?: "Failed to fetch profile"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateUserProfile(profile: UserProfile): Result<UserProfile> {
        return try {
            // Convert domain to DTO
            val dto = profile.toDto()
            val response = apiService.updateUserProfile(profile.userId, dto)
            val updatedProfile = response.toDomain()

            if (updatedProfile != null) {
                Result.success(updatedProfile)
            } else {
                Result.failure(Exception(response.message ?: "Failed to update profile"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getQuizHistory(
        userId: String,
        page: Int,
        pageSize: Int
    ): Result<List<QuizHistoryItem>> {
        return try {
            val response = apiService.getQuizHistory(userId, page, pageSize)

            if (response.success && response.data != null) {
                val items = response.data.quizzes.map { it.toDomain() }
                Result.success(items)
            } else {
                Result.failure(Exception(response.message ?: "Failed to fetch history"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

// Helper mapper
fun UserProfile.toDto(): UserProfileDto {
    return UserProfileDto(
        userId = userId,
        username = username,
        email = email,
        avatarUrl = avatarUrl,
        totalScore = totalScore,
        totalQuizzes = totalQuizzes,
        rank = rank,
        joinedDate = joinedDate,
        achievements = achievements.map { it.toDto() }
    )
}

fun Achievement.toDto(): AchievementDto {
    return AchievementDto(
        id = id,
        title = title,
        description = description,
        iconUrl = iconUrl,
        earnedDate = earnedDate
    )
}

fun QuizHistoryItemDto.toDomain(): QuizHistoryItem {
    return QuizHistoryItem(
        quizId = quizId,
        title = title,
        score = score,
        maxScore = maxScore,
        completedDate = completedDate
    )
}

// ============================================
// 9. Use Case (Domain Layer)
// ============================================

/**
 * Use case encapsulates business logic
 */
class GetUserProfileUseCase(
    private val repository: UserProfileRepository
) {
    suspend operator fun invoke(userId: String): Result<UserProfile> {
        return repository.getUserProfile(userId)
    }
}

// ============================================
// 10. ViewModel (Presentation Layer)
// ============================================

/**
 * ViewModel manages UI state
 */
data class UserProfileUiState(
    val isLoading: Boolean = false,
    val profile: UserProfile? = null,
    val error: String? = null
)

/**
 * Key Points:
 *
 * 1. DTO layer (data/models) - matches API structure
 * 2. Domain layer (domain/entity) - business logic models
 * 3. Mappers convert between layers
 * 4. ApiResponse wrapper provides consistency
 * 5. Repository handles data operations
 * 6. Use cases encapsulate business rules
 * 7. ViewModel manages UI state
 *
 * Benefits:
 * - Type safe throughout
 * - Easy to test each layer
 * - Clear separation of concerns
 * - Ready for API integration
 * - Consistent error handling
 * - Reusable patterns
 */

