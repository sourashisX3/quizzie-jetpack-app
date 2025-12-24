package com.android.quizapp.features.home.domain.models

data class QuizCategories(
    val id: Int,
    val buttonName: String? = null,
    val title: String? = null,
    val description: String? = null,
    val imageUrl: String? = null,
)
