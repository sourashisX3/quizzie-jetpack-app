package com.android.quizapp.features.contest.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.quizapp.features.contest.domain.entity.ContestQuestion
import com.android.quizapp.features.contest.domain.entity.ContestResult
import com.android.quizapp.features.contest.domain.entity.ContestSession
import com.android.quizapp.features.contest.domain.usecase.CompleteContestUseCase
import com.android.quizapp.features.contest.domain.usecase.StartContestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * UI State for Contest Session (Active Contest)
 */
data class ContestSessionUiState(
    val isLoading: Boolean = false,
    val session: ContestSession? = null,
    val currentQuestionIndex: Int = 0,
    val currentQuestion: ContestQuestion? = null,
    val selectedAnswer: Int? = null,
    val answers: Map<String, Int> = emptyMap(),
    val timeRemaining: Int = 0,
    val isTimerRunning: Boolean = false,
    val showResult: Boolean = false,
    val result: ContestResult? = null,
    val error: String? = null
)

/**
 * ViewModel for Contest Session Screen
 */
@HiltViewModel
class ContestSessionViewModel @Inject constructor(
    private val startContestUseCase: StartContestUseCase,
    private val completeContestUseCase: CompleteContestUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContestSessionUiState())
    val uiState: StateFlow<ContestSessionUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null
    private var contestStartTime: Long = 0

    /**
     * Start contest
     */
    fun startContest(contestId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val result = startContestUseCase(contestId)
            result.fold(
                onSuccess = { session ->
                    contestStartTime = System.currentTimeMillis()
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            session = session,
                            currentQuestionIndex = 0,
                            currentQuestion = session.questions.firstOrNull(),
                            timeRemaining = session.timePerQuestion,
                            isTimerRunning = true,
                            error = null
                        )
                    }
                    startTimer()
                },
                onFailure = { exception ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = exception.message ?: "Failed to start contest"
                        )
                    }
                }
            )
        }
    }

    /**
     * Select answer
     */
    fun selectAnswer(answerIndex: Int) {
        _uiState.update { it.copy(selectedAnswer = answerIndex) }
    }

    /**
     * Submit answer and move to next question
     */
    fun submitAnswer() {
        val currentState = _uiState.value
        val session = currentState.session ?: return
        val currentQuestion = currentState.currentQuestion ?: return
        val selectedAnswer = currentState.selectedAnswer ?: return

        // Stop timer
        stopTimer()

        // Save answer
        val updatedAnswers = currentState.answers + (currentQuestion.id to selectedAnswer)

        // Check if this is the last question
        val nextIndex = currentState.currentQuestionIndex + 1
        if (nextIndex >= session.questions.size) {
            // Contest complete
            completeContest(updatedAnswers)
        } else {
            // Move to next question
            _uiState.update {
                it.copy(
                    currentQuestionIndex = nextIndex,
                    currentQuestion = session.questions[nextIndex],
                    selectedAnswer = null,
                    answers = updatedAnswers,
                    timeRemaining = session.timePerQuestion,
                    isTimerRunning = true
                )
            }
            startTimer()
        }
    }

    /**
     * Skip question (time out)
     */
    private fun skipQuestion() {
        val currentState = _uiState.value
        val session = currentState.session ?: return

        val nextIndex = currentState.currentQuestionIndex + 1
        if (nextIndex >= session.questions.size) {
            // Contest complete
            completeContest(currentState.answers)
        } else {
            // Move to next question
            _uiState.update {
                it.copy(
                    currentQuestionIndex = nextIndex,
                    currentQuestion = session.questions[nextIndex],
                    selectedAnswer = null,
                    timeRemaining = session.timePerQuestion,
                    isTimerRunning = true
                )
            }
            startTimer()
        }
    }

    /**
     * Complete contest
     */
    private fun completeContest(answers: Map<String, Int>) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, isTimerRunning = false) }

            val session = _uiState.value.session ?: return@launch
            val timeTaken = (System.currentTimeMillis() - contestStartTime) / 1000

            val result = completeContestUseCase(session.contestId, answers, timeTaken)
            result.fold(
                onSuccess = { contestResult ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            showResult = true,
                            result = contestResult,
                            error = null
                        )
                    }
                },
                onFailure = { exception ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = exception.message ?: "Failed to complete contest"
                        )
                    }
                }
            )
        }
    }

    /**
     * Start countdown timer
     */
    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            val session = _uiState.value.session ?: return@launch
            var remaining = session.timePerQuestion

            while (remaining > 0 && _uiState.value.isTimerRunning) {
                delay(1000)
                remaining--
                _uiState.update { it.copy(timeRemaining = remaining) }
            }

            // Time's up
            if (remaining == 0 && _uiState.value.isTimerRunning) {
                skipQuestion()
            }
        }
    }

    /**
     * Stop timer
     */
    private fun stopTimer() {
        timerJob?.cancel()
        _uiState.update { it.copy(isTimerRunning = false) }
    }

    /**
     * Clear error
     */
    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    override fun onCleared() {
        super.onCleared()
        stopTimer()
    }
}

