package com.android.quizapp.features.home.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import com.android.quizapp.R
import com.android.quizapp.core.constants.StringConstants
import com.android.quizapp.core.shared.functions.CommonFunctions
import com.android.quizapp.features.home.domain.models.QuizCategories
import com.android.quizapp.features.home.domain.models.RecentlyPlayedData
import com.android.quizapp.features.home.presentation.components.DiamondBox
import com.android.quizapp.features.home.presentation.components.QuizCard
import com.android.quizapp.features.home.presentation.components.RecentlyPlayed
import com.android.quizapp.ui.theme.AppColor
import com.android.quizapp.ui.theme.QuizAppTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    HomeScreenView(
        modifier = modifier,
    )
}

@Composable
fun HomeScreenView(
    modifier: Modifier = Modifier,
) {

    val textTheme = MaterialTheme.typography
    val listState = rememberLazyListState()

    val recentlyPlayedData = listOf<RecentlyPlayedData?>(
        RecentlyPlayedData(
            id = 1,
            title = "Science Quiz",
            subtitle = "Last played: 2 days ago",
        ),
        RecentlyPlayedData(
            id = 2,
            title = "Math Quiz",
            subtitle = "Last played: 5 days ago",
        ),
        RecentlyPlayedData(
            id = 3,
            title = "History Quiz",
            subtitle = "Last played: 1 week ago",
        ),
        RecentlyPlayedData(
            id = 3,
            title = "History Quiz",
            subtitle = "Last played: 1 week ago",
        ),
        RecentlyPlayedData(
            id = 3,
            title = "History Quiz",
            subtitle = "Last played: 1 week ago",
        ),
        RecentlyPlayedData(
            id = 3,
            title = "History Quiz",
            subtitle = "Last played: 1 week ago",
        ),
        RecentlyPlayedData(
            id = 3,
            title = "History Quiz",
            subtitle = "Last played: 1 week ago",
        ),
    )
    val categoryList = listOf<QuizCategories?>(
        QuizCategories(
            id = 1,
            buttonName = "Science",
            title = "Science Quiz",
            description = "Test your knowledge about the world of science.",
            imageUrl = "https://example.com/images/science_quiz.jpg",
        ),

        QuizCategories(
            id = 2,
            buttonName = "Math",
            title = "Math Quiz",
            description = "Challenge yourself with math problems and puzzles.",
            imageUrl = "https://example.com/images/math_quiz.jpg",
        ),

        QuizCategories(
            id = 3,
            buttonName = "History",
            title = "History Quiz",
            description = "Explore historical events and figures.",
            imageUrl = "https://example.com/images/history_quiz.jpg",
        ),

        QuizCategories(
            id = 4,
            buttonName = "Geography",
            title = "Geography Quiz",
            description = "Test your knowledge of world geography.",
            imageUrl = "https://example.com/images/geography_quiz.jpg",
        ),

        QuizCategories(
            id = 5,
            buttonName = "Literature",
            title = "Literature Quiz",
            description = "Dive into the world of literature and authors.",
            imageUrl = "https://example.com/images/literature_quiz.jpg",
        )
    )
    val cardColors = remember {
        categoryList.associate { it?.id to CommonFunctions.randomColor() }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // --- Header Section ---
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // --- quiz title ---
            Text(text = StringConstants.QUIZ_TITLE, style = textTheme.displayLarge)
            // --- trophy icon ---
            Image(
                painter = painterResource(id = R.drawable.trophy),
                contentDescription = StringConstants.TROPHY_IC,
                modifier = Modifier
                    .size(64.dp)
                    .padding(start = 8.dp),
                colorFilter = ColorFilter.tint(AppColor.yellow, blendMode = BlendMode.SrcIn)
            )
            Spacer(modifier = Modifier.weight(1f))
            // --- diamond box ---
            DiamondBox(cnt = 200)
        }
        // --- greeting text ---
        Text(
            text = StringConstants.HOME_SCREEN_GREETING,
            style = textTheme.titleMedium.copy(color = AppColor.yellow)
        )

        // --- popular ---
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            Text(
                StringConstants.POPULAR_QUIZZES_SECTION_TITLE,
                style = textTheme.titleLarge
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(R.drawable.fire),
                contentDescription = StringConstants.FIRE_IC,
                modifier = Modifier.size(24.dp),
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // --- quiz cards ---
        LazyRow(
            state = listState,
            contentPadding = PaddingValues(start = 0.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            flingBehavior = rememberSnapFlingBehavior(listState)
        ) {
            items(
                count = categoryList.size,
            ) { itemIndex ->
                val category = categoryList[itemIndex]
                val cardColor = cardColors[category?.id] ?: CommonFunctions.randomColor()
                QuizCard(
                    modifier = Modifier,
                    color = cardColor,
                    buttonName = category?.buttonName ?: "Quiz",
                    title = category?.title ?: "Quiz Title",
                    description = category?.description ?: "Quiz Description",
                    imageUrl = /*category?.imageUrl ?:*/ "",
                    onClick = { println("Clicked on ${category?.title ?: "Quiz"}") },
                    onButtonClick = { println("Quiz Started") },
                    onLikeClick = { println("Liked") }
                )
            }
        }

        // --- Recently Played Section ---
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(StringConstants.RECENTLY_PLAYED_SECTION_TITLE, style = textTheme.titleLarge)
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(R.drawable.recent),
                contentDescription = StringConstants.RECENT_IC,
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(AppColor.yellow)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
        ) {
            recentlyPlayedData.forEach { recentItem ->
                RecentlyPlayed(
                    title = recentItem?.title ?: "Quiz Title",
                    subtitle = recentItem?.subtitle ?: "Subtitle",
                    onPlayNowClick = { println("Play Now Clicked for ${recentItem?.title ?: "Quiz"}") }
                )
            }

        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomePreview() {
    QuizAppTheme {
        HomeScreenView()
    }
}