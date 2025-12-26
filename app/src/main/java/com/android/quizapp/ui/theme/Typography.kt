package com.android.quizapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.android.quizapp.R


private val Belanosima = FontFamily(
    Font(R.font.belanosima_regular, FontWeight.Normal),
    Font(R.font.belanosima_semibold, FontWeight.SemiBold),
    Font(R.font.belanosima_bold, FontWeight.Bold)
)

private val Chango = FontFamily(
    Font(R.font.chango_regular, FontWeight.Normal)
)


val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Chango,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = 0.sp,
        color = AppColor.black
    ),

    displayMedium = TextStyle(
        fontFamily = Chango,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp,
        color = AppColor.black
    ),

    displaySmall = TextStyle(
        fontFamily = Chango,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp,
        color = AppColor.black
    ),

    headlineLarge = TextStyle(
        fontFamily = Belanosima,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp,
        color = AppColor.black
    ),

    headlineMedium = TextStyle(
        fontFamily = Belanosima,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp,
        color = AppColor.black
    ),

    headlineSmall = TextStyle(
        fontFamily = Belanosima,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp,
        color = AppColor.black
    ),

    titleLarge = TextStyle(
        fontFamily = Belanosima,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        color = AppColor.black
    ),

    titleMedium = TextStyle(
        fontFamily = Belanosima,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
        color = AppColor.black
    ),

    titleSmall = TextStyle(
        fontFamily = Belanosima,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        color = AppColor.black
    ),

    bodyLarge = TextStyle(
        fontFamily = Belanosima,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = AppColor.black
    ),

    bodyMedium = TextStyle(
        fontFamily = Belanosima,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
        color = AppColor.black
    ),

    bodySmall = TextStyle(
        fontFamily = Belanosima,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.25.sp,
        color = AppColor.black
    ),

    labelLarge = TextStyle(
        fontFamily = Belanosima,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        color = AppColor.black
    ),

    labelMedium = TextStyle(
        fontFamily = Belanosima,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        color = AppColor.black
    ),

    labelSmall = TextStyle(
        fontFamily = Belanosima,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        color = AppColor.black
    )
)