package com.android.quizapp.features.leaderboard.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.quizapp.features.leaderboard.domain.entity.Leaderboard
import com.android.quizapp.features.leaderboard.domain.usecase.GetLeaderboardUseCase
import com.android.quizapp.features.leaderboard.domain.usecase.RefreshLeaderboardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * UI State for Leaderboard Screen
 */
data class LeaderboardUiState(
    val isLoading: Boolean = false,
    val leaderboard: Leaderboard? = null,
    val error: String? = null,
    val isRefreshing: Boolean = false
)

/**
 * ViewModel for Leaderboard Screen
 */
@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val getLeaderboardUseCase: GetLeaderboardUseCase,
    private val refreshLeaderboardUseCase: RefreshLeaderboardUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LeaderboardUiState())
    val uiState: StateFlow<LeaderboardUiState> = _uiState.asStateFlow()

    init {
        loadLeaderboard()
    }

    /**
     * Load leaderboard data
     */
    fun loadLeaderboard() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            getLeaderboardUseCase().collect { result ->
                result.fold(
                    onSuccess = { leaderboard ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                leaderboard = leaderboard,
                                error = null
                            )
                        }
                    },
                    onFailure = { exception ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = exception.message ?: "Failed to load leaderboard"
                            )
                        }
                    }
                )
            }
        }
    }

    /**
     * Load leaderboard with specific limit
     */
    fun loadLeaderboard(limit: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            getLeaderboardUseCase(limit).collect { result ->
                result.fold(
                    onSuccess = { leaderboard ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                leaderboard = leaderboard,
                                error = null
                            )
                        }
                    },
                    onFailure = { exception ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = exception.message ?: "Failed to load leaderboard"
                            )
                        }
                    }
                )
            }
        }
    }

    /**
     * Refresh leaderboard data
     */
    fun refreshLeaderboard() {
        viewModelScope.launch {
            _uiState.update { it.copy(isRefreshing = true, error = null) }

            val result = refreshLeaderboardUseCase()
            result.fold(
                onSuccess = { leaderboard ->
                    _uiState.update {
                        it.copy(
                            isRefreshing = false,
                            leaderboard = leaderboard,
                            error = null
                        )
                    }
                },
                onFailure = { exception ->
                    _uiState.update {
                        it.copy(
                            isRefreshing = false,
                            error = exception.message ?: "Failed to refresh leaderboard"
                        )
                    }
                }
            )
        }
    }

    /**
     * Clear error state
     */
    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

