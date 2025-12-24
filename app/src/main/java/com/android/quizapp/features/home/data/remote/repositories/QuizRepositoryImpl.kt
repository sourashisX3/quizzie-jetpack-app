package com.android.quizapp.features.home.data.remote.repositories

import com.android.quizapp.features.home.domain.models.QuizCategories
import com.android.quizapp.features.home.domain.repositories.QuizRepository

class QuizRepositoryImpl : QuizRepository {
    override fun getQuizCategories(): List<QuizCategories?> {
        return listOf<QuizCategories?>();
    }
}