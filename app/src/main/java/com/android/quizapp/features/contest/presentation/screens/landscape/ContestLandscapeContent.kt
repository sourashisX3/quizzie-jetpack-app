package com.android.quizapp.features.contest.presentation.screens.landscape

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
fun ContestLandscapeContent(
    contests: List<Contest>,
    statistics: ContestStatistics?,
    onContestClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxSize()
    ) {
        // Left: Statistics - limited width to not dominate the screen
        if (statistics != null) {
            Column(
                modifier = Modifier
                    .widthIn(min = 280.dp, max = 400.dp)
                    .fillMaxHeight()
                    .padding(16.dp)
            ) {
                ContestStatisticsCard(
                    statistics = statistics,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        }

        // Right: Contests - takes remaining space and scrolls
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight().padding(bottom = 56.dp),
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

