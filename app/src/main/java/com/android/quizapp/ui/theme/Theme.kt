package com.android.quizapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    // Dark theme: use high-contrast light text on dark surfaces
    primary = AppColor.white,
    onPrimary = AppColor.black,
    secondary = AppColor.purple,
    onSecondary = AppColor.black,
    tertiary = AppColor.pink,
    onTertiary = AppColor.black,

    background = AppColor.bgColorDark,
    onBackground = AppColor.white,
    surface = AppColor.black,
    onSurface = AppColor.white,

    error = AppColor.red,
    onError = AppColor.black
)

private val LightColorScheme = lightColorScheme(
    // Light theme: beige background, dark text, white cards/surfaces
    primary = AppColor.black,
    onPrimary = AppColor.white,
    secondary = AppColor.purple,
    onSecondary = AppColor.white,
    tertiary = AppColor.pink,
    onTertiary = AppColor.white,

    background = AppColor.bgColorDark,
    onBackground = AppColor.black,
    surface = AppColor.white,
    onSurface = AppColor.black,

    error = AppColor.red,
    onError = AppColor.white

    /* Other default colors to override
    surfaceVariant = bgColorMiddle,
    onSurfaceVariant = black,
    outline = lightGrey*/
)

@Composable
fun QuizAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}