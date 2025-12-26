package com.android.quizapp.core.shared.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * Utility extensions for network operations
 */

/**
 * Extension to safely handle API calls and map to Result
 */
fun <T> Flow<ApiResponse<T>>.mapToResult(): Flow<Result<T>> {
    return this.map { response ->
        response.toResult()
    }.catch { exception ->
        emit(Result.failure(exception))
    }
}

/**
 * Extension to check if HTTP status code is successful
 */
fun Int.isSuccessful(): Boolean {
    return this in 200..299
}

/**
 * Extension to check if HTTP status code is client error
 */
fun Int.isClientError(): Boolean {
    return this in 400..499
}

/**
 * Extension to check if HTTP status code is server error
 */
fun Int.isServerError(): Boolean {
    return this in 500..599
}

/**
 * Get error message based on status code
 */
fun Int.getErrorMessage(): String {
    return when (this) {
        NetworkConstants.HTTP_BAD_REQUEST -> "Bad request. Please check your input."
        NetworkConstants.HTTP_UNAUTHORIZED -> "Unauthorized. Please login again."
        NetworkConstants.HTTP_FORBIDDEN -> "You don't have permission to access this resource."
        NetworkConstants.HTTP_NOT_FOUND -> "The requested resource was not found."
        NetworkConstants.HTTP_INTERNAL_SERVER_ERROR -> "Internal server error. Please try again later."
        NetworkConstants.HTTP_SERVICE_UNAVAILABLE -> "Service temporarily unavailable. Please try again later."
        else -> "An unexpected error occurred (Code: $this)."
    }
}

