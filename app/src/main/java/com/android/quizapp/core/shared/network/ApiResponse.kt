package com.android.quizapp.core.shared.network

import com.google.gson.annotations.SerializedName

/**
 * Generic API Response wrapper for all API calls
 * This standardizes the response structure across the entire application
 *
 * @param T The type of data payload returned by the API
 */
data class ApiResponse<T>(
    @SerializedName("success")
    val success: Boolean = false,

    @SerializedName("status_code")
    val statusCode: Int = 200,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("data")
    val data: T? = null,

    @SerializedName("timestamp")
    val timestamp: Long = System.currentTimeMillis(),

    @SerializedName("pagination")
    val pagination: Pagination? = null
)

/**
 * Pagination metadata for paginated responses
 */
data class Pagination(
    @SerializedName("current_page")
    val currentPage: Int = 1,

    @SerializedName("page_size")
    val pageSize: Int = 10,

    @SerializedName("total_pages")
    val totalPages: Int = 1,

    @SerializedName("total_items")
    val totalItems: Int = 0,

    @SerializedName("has_next")
    val hasNext: Boolean = false,

    @SerializedName("has_previous")
    val hasPrevious: Boolean = false
)

/**
 * Extension function to check if API response is successful
 */
fun <T> ApiResponse<T>.isSuccessful(): Boolean {
    return success && data != null
}

/**
 * Extension function to get data or throw exception
 */
fun <T> ApiResponse<T>.getDataOrThrow(): T {
    return when {
        !success -> throw ApiException(message ?: "API request failed", statusCode)
        data == null -> throw ApiException("No data available", statusCode)
        else -> data
    }
}

/**
 * Extension function to convert to Result
 */
fun <T> ApiResponse<T>.toResult(): Result<T> {
    return if (isSuccessful()) {
        Result.success(data!!)
    } else {
        Result.failure(ApiException(message ?: "Unknown error", statusCode))
    }
}

/**
 * Custom exception for API errors
 */
class ApiException(
    override val message: String,
    val statusCode: Int
) : Exception(message)

