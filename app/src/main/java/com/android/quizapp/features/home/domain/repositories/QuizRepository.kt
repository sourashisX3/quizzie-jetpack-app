package com.android.quizapp.features.home.domain.repositories

import com.android.quizapp.features.home.domain.models.QuizCategories

interface QuizRepository {

    fun getQuizCategories(): List<QuizCategories?>
}