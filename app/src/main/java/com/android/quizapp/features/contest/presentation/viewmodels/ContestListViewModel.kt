package com.android.quizapp.features.contest.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.quizapp.features.contest.domain.entity.Contest
import com.android.quizapp.features.contest.domain.entity.ContestStatistics
import com.android.quizapp.features.contest.domain.entity.ContestType

import com.android.quizapp.features.contest.domain.usecase.GetContestStatisticsUseCase
import com.android.quizapp.features.contest.domain.usecase.GetContestsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * UI State for Contest List Screen
 */
data class ContestListUiState(
    val isLoading: Boolean = false,
    val selectedType: ContestType = ContestType.DAILY,
    val allContests: List<Contest> = emptyList(),
    val dailyContests: List<Contest> = emptyList(),
    val weeklyContests: List<Contest> = emptyList(),
    val monthlyContests: List<Contest> = emptyList(),
    val statistics: ContestStatistics? = null,
    val error: String? = null
)

/**
 * ViewModel for Contest List Screen
 */
@HiltViewModel
class ContestListViewModel @Inject constructor(
    private val getContestsUseCase: GetContestsUseCase,
    private val getStatisticsUseCase: GetContestStatisticsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContestListUiState())
    val uiState: StateFlow<ContestListUiState> = _uiState.asStateFlow()

    init {
        loadContests()
        loadStatistics()
    }

    /**
     * Load all contests
     */
    private fun loadContests() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            getContestsUseCase().collect { result ->
                result.fold(
                    onSuccess = { contests ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                allContests = contests,
                                dailyContests = contests.filter { c -> c.type == ContestType.DAILY },
                                weeklyContests = contests.filter { c -> c.type == ContestType.WEEKLY },
                                monthlyContests = contests.filter { c -> c.type == ContestType.MONTHLY },
                                error = null
                            )
                        }
                    },
                    onFailure = { exception ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = exception.message ?: "Failed to load contests"
                            )
                        }
                    }
                )
            }
        }
    }

    /**
     * Load statistics
     */
    private fun loadStatistics() {
        viewModelScope.launch {
            getStatisticsUseCase().collect { result ->
                result.fold(
                    onSuccess = { statistics ->
                        _uiState.update { it.copy(statistics = statistics) }
                    },
                    onFailure = { /* Ignore statistics error */ }
                )
            }
        }
    }

    /**
     * Select contest type tab
     */
    fun selectContestType(type: ContestType) {
        _uiState.update { it.copy(selectedType = type) }
    }

    /**
     * Refresh contests
     */
    fun refreshContests() {
        loadContests()
        loadStatistics()
    }

    /**
     * Clear error
     */
    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

