package com.android.quizapp.features.contest.presentation.screens.portrait

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.quizapp.features.contest.domain.entity.Contest
import com.android.quizapp.features.contest.domain.entity.ContestStatistics
import com.android.quizapp.features.contest.presentation.components.ContestCard
import com.android.quizapp.features.contest.presentation.components.ContestStatisticsCard

@Composable
fun ContestPortraitContent(
    contests: List<Contest>,
    statistics: ContestStatistics?,
    onContestClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Statistics Section - wrap content to not take excessive space
        if (statistics != null) {
            ContestStatisticsCard(
                statistics = statistics,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            )
        }

        // Contest List - takes remaining space and scrolls
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 56.dp)
                .weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(contests) { contest ->
                ContestCard(
                    contest = contest,
                    onClick = { onContestClick(contest.id) }
                )
            }
        }
    }
}


