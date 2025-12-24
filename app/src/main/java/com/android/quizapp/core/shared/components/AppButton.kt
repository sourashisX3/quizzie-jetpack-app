package com.android.quizapp.core.shared.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.android.quizapp.ui.theme.AppColor

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    height: Dp = 50.dp,
    shape: Shape = RoundedCornerShape(30.dp),
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = AppColor.black),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    leadingIcon: (@Composable (() -> Unit))? = null,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(height),
        enabled = enabled,
        shape = shape,
        colors = colors,
        contentPadding = contentPadding
    ) {
        if (leadingIcon != null) {
            leadingIcon()
            Spacer(modifier = Modifier.width(8.dp))
        }
        content()
    }
}

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonName: String,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(30.dp),
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = AppColor.black),
    textStyle: TextStyle? = null,
    leadingIcon: (@Composable (() -> Unit))? = null,
) {
    AppButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = colors,
        leadingIcon = leadingIcon,
    ) {
        Text(
            text = buttonName,
            style = textStyle ?: MaterialTheme.typography.titleLarge.copy(color = AppColor.white)
        )
    }
}