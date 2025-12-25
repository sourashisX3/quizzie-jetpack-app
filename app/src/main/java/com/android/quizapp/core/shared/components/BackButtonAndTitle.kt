package com.android.quizapp.core.shared.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.quizapp.R
import com.android.quizapp.ui.theme.AppColor

@Composable
fun BackButtonAndTitle(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.back_ic),
                contentDescription = "Back",
                tint = AppColor.brown
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.displaySmall,
            color = AppColor.brown
        )
    }
}