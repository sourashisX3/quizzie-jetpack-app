package com.android.quizapp.features.leaderboard.presentation.screens.portrait

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.android.quizapp.features.leaderboard.domain.entity.Leaderboard
import com.android.quizapp.features.leaderboard.presentation.components.LeaderboardEntryItem

@Composable
fun LeaderBoardPortraitContent(
    leaderboard: Leaderboard?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Stats Section
        if (leaderboard != null) {
            Text(
                text = "${leaderboard.totalParticipants} Participants",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Leaderboard List
        if (leaderboard != null && leaderboard.entries.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(bottom = 56.dp),
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                itemsIndexed(leaderboard.entries) { _, entry ->
                    LeaderboardEntryItem(
                        entry = entry,
                        isCurrentUser = entry.userId == leaderboard.currentUserEntry?.userId
                    )
                }
            }
        } else {
            Text(
                text = "No leaderboard data available",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 32.dp)
            )
        }
    }
}