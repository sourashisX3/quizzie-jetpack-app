package com.android.quizapp.features.contest.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.quizapp.features.contest.domain.entity.ContestType
import com.android.quizapp.features.contest.presentation.screens.landscape.ContestLandscapeContent
import com.android.quizapp.features.contest.presentation.screens.portrait.ContestPortraitContent
import com.android.quizapp.features.contest.presentation.viewmodels.ContestListViewModel
import com.android.quizapp.ui.theme.AppColor

@Composable
fun ContestScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ContestListViewModel = hiltViewModel()
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppColor.bgColorLight)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header
            ContestHeader(
                onRefreshClick = { viewModel.refreshContests() }
            )

            // Tabs
            ContestTypeTabs(
                selectedType = uiState.selectedType,
                onTypeSelect = { viewModel.selectContestType(it) }
            )

            // Content based on selected tab
            val contests = when (uiState.selectedType) {
                ContestType.DAILY -> uiState.dailyContests
                ContestType.WEEKLY -> uiState.weeklyContests
                ContestType.MONTHLY -> uiState.monthlyContests
                else -> uiState.allContests
            }

            // Responsive layout
            if (isLandscape) {
                ContestLandscapeContent(
                    contests = contests,
                    statistics = uiState.statistics,
                    onContestClick = { contestId ->
                        navController.navigate("contest_session/$contestId")
                    }
                )
            } else {
                ContestPortraitContent(
                    contests = contests,
                    statistics = uiState.statistics,
                    onContestClick = { contestId ->
                        navController.navigate("contest_session/$contestId")
                    }
                )
            }
        }

        // Loading indicator
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Error snackbar
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

@Composable
fun ContestHeader(
    onRefreshClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 4.dp,
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Contests",
                    style = MaterialTheme.typography.displaySmall.copy(
                        color = AppColor.brown
                    )
                )
                Text(
                    text = "Compete and win rewards",
                    style = MaterialTheme.typography.bodySmall,
                    color = AppColor.grey
                )
            }

            /*IconButton(onClick = onRefreshClick) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh",
                    tint = AppColor.brown
                )
            }*/
        }
    }
}

@Composable
fun ContestTypeTabs(
    selectedType: ContestType,
    onTypeSelect: (ContestType) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = selectedType.ordinal,
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color.White,
        contentColor = AppColor.brown,
        edgePadding = 16.dp
    ) {
        ContestType.entries.forEach { type ->
            Tab(
                selected = selectedType == type,
                onClick = { onTypeSelect(type) },
                text = {
                    Text(
                        text = type.name.lowercase().replaceFirstChar { it.uppercase() },
                        fontWeight = if (selectedType == type) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
        }
    }
}

