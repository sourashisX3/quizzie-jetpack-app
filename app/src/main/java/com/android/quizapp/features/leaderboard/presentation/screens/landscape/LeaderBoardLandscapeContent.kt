package com.android.quizapp.features.leaderboard.presentation.screens.landscape

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
fun LeaderBoardLandscapeContent(
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

        // Leaderboard Grid (2 columns for landscape)
        if (leaderboard != null && leaderboard.entries.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 56.dp)
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