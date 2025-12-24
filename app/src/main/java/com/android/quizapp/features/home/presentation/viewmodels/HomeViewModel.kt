package com.android.quizapp.features.home.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.android.quizapp.features.home.domain.models.QuizCategories
import com.android.quizapp.features.home.domain.usecases.QuizUseCase
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel(
    private val quizUseCase: QuizUseCase
) : ViewModel() {
    private val _quizCategories = MutableStateFlow<List<QuizCategories?>>(emptyList())
    val quizCategories = _quizCategories

    init {
        _quizCategories.value = quizUseCase.getQuizCategories()
    }
}