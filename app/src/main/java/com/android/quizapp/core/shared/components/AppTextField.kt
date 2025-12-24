package com.android.quizapp.core.shared.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.quizapp.ui.theme.AppColor
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = { },
    label: String = "",
    placeholder: String = "",
    leadingIcon: (@Composable (() -> Unit))? = null,
    trailingIcon: (@Composable (() -> Unit))? = null,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    isError: Boolean = false,
    supportingText: (@Composable (() -> Unit))? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            // -- focused colors ---
            cursorColor = AppColor.brown,
            focusedContainerColor = AppColor.white,
            focusedIndicatorColor = AppColor.brown,
            focusedLeadingIconColor = AppColor.brown,
            focusedTrailingIconColor = AppColor.brown,
            focusedLabelColor = AppColor.brown,
            // -- unfocused colors ---
            unfocusedContainerColor = AppColor.white,
            unfocusedLabelColor = AppColor.grey,
            unfocusedIndicatorColor = AppColor.grey,
            unfocusedLeadingIconColor = AppColor.grey,
            unfocusedTrailingIconColor = AppColor.grey,
            // -- error colors ---
            errorLabelColor = AppColor.red,
            errorTextColor = AppColor.red,
            errorIndicatorColor = AppColor.red,
            errorCursorColor = AppColor.red,
            errorContainerColor = AppColor.red
        ),
        value = value,
        onValueChange = onValueChange,
        label = if (label.isNotEmpty()) ({ Text(text = label) }) else null,
        placeholder = if (placeholder.isNotEmpty()) ({ Text(text = placeholder) }) else null,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        singleLine = singleLine,
        maxLines = maxLines,
        isError = isError,
        supportingText = supportingText,
        visualTransformation = visualTransformation,
        enabled = enabled,
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppTextFieldPreview() {
    Column {
        AppTextField(
            label = "Email",
            placeholder = "Enter your email",
            value = "",
            onValueChange = {},
        )
        var password by remember { mutableStateOf("") }
        var visible by remember { mutableStateOf(false) }
        AppTextField(
            label = "Password",
            placeholder = "Enter your password",
            value = password,
            onValueChange = { password = it },
            visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        )
    }
}
