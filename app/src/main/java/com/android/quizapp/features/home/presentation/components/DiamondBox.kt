package com.android.quizapp.features.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.quizapp.R
import com.android.quizapp.core.constants.StringConstants
import com.android.quizapp.ui.theme.AppColor


@Composable
fun DiamondBox(
    modifier: Modifier = Modifier,
    cnt: Int,
) {
    Box(
        modifier = modifier
            .size(height = 40.dp, width = 100.dp)
            .background(color = AppColor.white, shape = RoundedCornerShape(30.dp))
            .border(width = 1.dp, color = AppColor.lightGrey, shape = RoundedCornerShape(30.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier.padding(6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = cnt.toString(),
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = modifier.size(4.dp))
            Image(
                painter = painterResource(id = R.drawable.gems),
                contentDescription = StringConstants.DIAMOND_IC,
                modifier = modifier.size(24.dp),
                colorFilter = ColorFilter.tint(AppColor.blue, blendMode = BlendMode.SrcIn)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiamondBoxPreview() {
    DiamondBox(
        cnt = 120,
    )
}