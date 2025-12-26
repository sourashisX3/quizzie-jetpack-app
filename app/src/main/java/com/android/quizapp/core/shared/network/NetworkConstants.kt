package com.android.quizapp.core.shared.network

/**
 * Common network constants used across the application
 */
object NetworkConstants {
    // Base URL for API calls - Update this when backend is ready
    const val BASE_URL = "https://api.quizapp.com/"

    // API version
    const val API_VERSION = "v1"

    // Timeout configurations (in seconds)
    const val CONNECT_TIMEOUT = 30L
    const val READ_TIMEOUT = 30L
    const val WRITE_TIMEOUT = 30L

    // Pagination defaults
    const val DEFAULT_PAGE_SIZE = 10
    const val MAX_PAGE_SIZE = 100
    const val MIN_PAGE_SIZE = 1

    // HTTP Status Codes
    const val HTTP_OK = 200
    const val HTTP_CREATED = 201
    const val HTTP_BAD_REQUEST = 400
    const val HTTP_UNAUTHORIZED = 401
    const val HTTP_FORBIDDEN = 403
    const val HTTP_NOT_FOUND = 404
    const val HTTP_INTERNAL_SERVER_ERROR = 500
    const val HTTP_SERVICE_UNAVAILABLE = 503

    // Headers
    const val HEADER_AUTHORIZATION = "Authorization"
    const val HEADER_CONTENT_TYPE = "Content-Type"
    const val HEADER_ACCEPT = "Accept"

    // Content Types
    const val CONTENT_TYPE_JSON = "application/json"
}


