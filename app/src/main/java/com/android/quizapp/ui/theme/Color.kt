package com.android.quizapp.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object AppColor {

    // -- background colors --
    val bgColorDark = Color(0xFFede6da)
    val bgColorMiddle = Color(0xFFf8f4ee)
    val bgColorLight = Color(0xFFfdfbf9)

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            bgColorDark,
            bgColorMiddle,
            bgColorLight,
        )
    )

    // -- card colors --
    val redCardColor = Color(0xFFea4c37)
    val yellowCardColor = Color(0xFFffcb3d)
    val greenCardColor = Color(0xFF477b65)

    // -- common colors --
    val white = Color(0xFFFFFFFF)
    val black = Color(0xFF000000)
    val grey = Color(0xFF7d7d7d)
    val lightGrey = Color(0xFFbfbfbf)
    val red = Color(0xFFea4c37)
    val blue = Color(0xFF3b82f6)
    val green = Color(0xFF10b981)
    val yellow = Color(0xFFf59e0b)
    val orange = Color(0xFFf97316)
    val pink = Color(0xFFec4899)
    val purple = Color(0xFF8b5cf6)
    val brown = Color(0xFFa0522d)
}