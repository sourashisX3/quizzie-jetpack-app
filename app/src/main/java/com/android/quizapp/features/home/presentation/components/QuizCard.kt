package com.android.quizapp.features.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.android.quizapp.R
import com.android.quizapp.core.constants.StringConstants
import com.android.quizapp.core.shared.components.AppButton
import com.android.quizapp.core.shared.components.LikeButton
import com.android.quizapp.ui.theme.AppColor

@Composable
fun QuizCard(
    modifier: Modifier = Modifier,
    color: Color,
    buttonName: String,
    title: String,
    description: String,
    imageUrl: String,
    onClick: () -> Unit,
    onButtonClick: () -> Unit,
    onLikeClick: () -> Unit
) {
    QuizCardView(
        modifier = modifier,
        color = color,
        buttonName = buttonName,
        title = title,
        description = description,
        imageUrl = imageUrl,
        onClick = onClick,
        onButtonClick = onButtonClick,
        onLikeClick = onLikeClick
    )
}

@Composable
fun QuizCardView(
    modifier: Modifier = Modifier,
    color: Color,
    buttonName: String,
    title: String,
    description: String,
    imageUrl: String,
    onClick: () -> Unit,
    onButtonClick: () -> Unit,
    onLikeClick: () -> Unit
) {
    Card(
        modifier = modifier
            .size(height = 350.dp, width = 300.dp)
            .clickable(onClick = onClick, role = Role.Button),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val (buttonRef, likeButtonRef, titleRef, descriptionRef, imageUrlRef) = createRefs()

            // --- Main button ---
            AppButton(
                buttonName = buttonName,
                onClick = onButtonClick,
                modifier = Modifier
                    .constrainAs(buttonRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(likeButtonRef.start, margin = 56.dp)
                        width = Dimension.fillToConstraints
                    }
            )
            // --- Like button ---
            LikeButton(
                onClick = onLikeClick,
                modifier = Modifier
                    .constrainAs(likeButtonRef) {
                        top.linkTo(buttonRef.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(buttonRef.bottom)
                    }
            )

            // --- Title ---
            Text(
                title, style = MaterialTheme.typography.titleLarge.copy(color = AppColor.white),
                modifier = Modifier
                    .constrainAs(titleRef) {
                        top.linkTo(buttonRef.bottom, margin = 24.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
            )

            // -- Description ---
            Text(
                description,
                style = MaterialTheme.typography.bodyMedium.copy(color = AppColor.white),
                modifier = Modifier
                    .constrainAs(descriptionRef) {
                        top.linkTo(titleRef.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
            )

            // --- Image ---
            if (imageUrl.isNotEmpty() || imageUrl.isNotBlank()) {
                // Load image from URL if provided (implementation not shown here)
            } else {
                Image(
                    painter = painterResource(id = R.drawable.quiz_logo),
                    contentDescription = StringConstants.QUIZ_TITLE,
                    modifier = Modifier
                        .constrainAs(imageUrlRef) {
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                        }
                        .height(150.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun QuizCardPreview() {
    QuizCardView(
        color = AppColor.redCardColor,
        buttonName = "Tech",
        title = "Tech Quiz",
        description = "Test your technology knowledge with this exciting quiz!",
        onClick = {},
        onButtonClick = { println("Clicked") },
        onLikeClick = { println("Liked") },
        imageUrl = ""
    )
}