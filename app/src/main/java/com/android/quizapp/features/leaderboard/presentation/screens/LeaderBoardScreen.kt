package com.android.quizapp.features.leaderboard.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.quizapp.features.leaderboard.presentation.screens.landscape.LeaderBoardLandscapeContent
import com.android.quizapp.features.leaderboard.presentation.screens.portrait.LeaderBoardPortraitContent
import com.android.quizapp.features.leaderboard.presentation.viewmodels.LeaderboardViewModel
import com.android.quizapp.ui.theme.AppColor

@Composable
fun LeaderBoardScreen(
    modifier: Modifier = Modifier,
    viewModel: LeaderboardViewModel = hiltViewModel()
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // -- Header Section --
            Text(
                text = "Leaderboard🔥",
                style = MaterialTheme.typography.displaySmall.copy(
                    color = AppColor.brown,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // -- Content Section --
            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                uiState.leaderboard != null -> {
                    if (isLandscape) {
                        LeaderBoardLandscapeContent(leaderboard = uiState.leaderboard)
                    } else {
                        LeaderBoardPortraitContent(leaderboard = uiState.leaderboard)
                    }
                }

                else -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No data available",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }

        // Refresh FAB
        /*if (!uiState.isLoading && !uiState.isRefreshing) {
            FloatingActionButton(
                onClick = { viewModel.refreshLeaderboard() },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh Leaderboard"
                )
            }
        }*/

        // Refreshing indicator
        if (uiState.isRefreshing) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 80.dp)
            )
        }

        // Error Snackbar
        if (uiState.error != null) {
            Snackbar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                action = {
                    TextButton(onClick = { viewModel.clearError() }) {
                        Text("Dismiss")
                    }
                }
            ) {
                Text(text = uiState.error ?: "An error occurred")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LeaderBoardScreenPreview() {
    // Preview without ViewModel for design purposes
    // LeaderBoardScreen()
}