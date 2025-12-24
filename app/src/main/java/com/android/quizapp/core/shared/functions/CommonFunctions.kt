package com.android.quizapp.core.shared.functions


import androidx.compose.ui.graphics.Color
import com.android.quizapp.ui.theme.AppColor
import kotlin.random.Random

object CommonFunctions {
    fun randomColor(): Color {
        val colors = listOf(
            AppColor.redCardColor,
            AppColor.greenCardColor,
            AppColor.yellowCardColor,
        )
        val randomIndex = Random.nextInt(colors.size)
        return colors[randomIndex]
    }
}