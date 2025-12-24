package com.android.quizapp.features.home.domain.usecases

import com.android.quizapp.features.home.data.remote.repositories.QuizRepositoryImpl

class QuizUseCase(
    private val quizRepo : QuizRepositoryImpl
) {
    fun getQuizCategories() = quizRepo.getQuizCategories()
}