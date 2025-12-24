package com.android.quizapp.core.shared.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.quizapp.ui.theme.AppColor

@Composable
fun TextAndTextButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    textButton: String? = null,
    onTextButtonClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = text ?: "", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.width(8.dp))
        TextButton(
            onClick = onTextButtonClick
        ) {
            Text(
                text = textButton ?: "", style = MaterialTheme.typography.bodyLarge.copy(
                    color = AppColor.brown
                )
            )
        }
    }
}