package com.android.quizapp.features.contest.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.quizapp.features.contest.presentation.components.OptionButton
import com.android.quizapp.features.contest.presentation.components.TimerDisplay
import com.android.quizapp.features.contest.presentation.viewmodels.ContestSessionViewModel
import com.android.quizapp.ui.theme.AppColor

@Composable
fun ContestSessionScreen(
    contestId: String,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ContestSessionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(contestId) {
        viewModel.startContest(contestId)
    }

    // Show result screen if contest is complete
    if (uiState.showResult && uiState.result != null) {
        ContestResultScreen(
            result = uiState.result!!,
            onBackClick = { navController.popBackStack() },
            onRetryClick = {
                navController.popBackStack()
                navController.navigate("contest_session/$contestId")
            }
        )
    } else {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(AppColor.bgColorLight)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Header
                ContestSessionHeader(
                    contestTitle = uiState.session?.contestTitle ?: "",
                    currentQuestion = uiState.currentQuestionIndex + 1,
                    totalQuestions = uiState.session?.questions?.size ?: 0,
                    onCloseClick = { navController.popBackStack() }
                )

                // Timer
                if (uiState.isTimerRunning) {
                    TimerDisplay(
                        timeRemaining = uiState.timeRemaining,
                        totalTime = uiState.session?.timePerQuestion ?: 30,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 24.dp)
                            .padding(top = 24.dp)
                    )
                }

                // Question and Options - scrollable to ensure all options are visible
                uiState.currentQuestion?.let { question ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(24.dp)
                                ) {
                                    Text(
                                        text = "Question ${uiState.currentQuestionIndex + 1}",
                                        style = MaterialTheme.typography.labelLarge,
                                        color = AppColor.blue,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(modifier = Modifier.height(12.dp))

                                    Text(
                                        text = question.questionText,
                                        style = MaterialTheme.typography.headlineSmall.copy(
                                            fontWeight = FontWeight.Bold
                                        )
                                    )

                                    Spacer(modifier = Modifier.height(24.dp))

                                    // Options
                                    question.options.forEachIndexed { index, option ->
                                        OptionButton(
                                            option = option,
                                            optionIndex = index,
                                            isSelected = uiState.selectedAnswer == index,
                                            onClick = { viewModel.selectAnswer(index) }
                                        )
                                        if (index < question.options.size - 1) {
                                            Spacer(modifier = Modifier.height(12.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // Submit button
                Button(
                    onClick = { viewModel.submitAnswer() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(56.dp),
                    enabled = uiState.selectedAnswer != null && !uiState.isLoading,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColor.blue
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = if (uiState.currentQuestionIndex < (uiState.session?.questions?.size
                                ?: 1) - 1
                        ) "Next Question" else "Finish Contest",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            // Loading indicator
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun ContestSessionHeader(
    contestTitle: String,
    currentQuestion: Int,
    totalQuestions: Int,
    onCloseClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 4.dp,
        color = Color.White
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = contestTitle,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = onCloseClick) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = AppColor.red
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Progress bar
            Column {
                Text(
                    text = "Question $currentQuestion of $totalQuestions",
                    style = MaterialTheme.typography.bodySmall,
                    color = AppColor.grey
                )
                Spacer(modifier = Modifier.height(4.dp))
                LinearProgressIndicator(
                    progress = { currentQuestion.toFloat() / totalQuestions.toFloat() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = AppColor.blue,
                    trackColor = AppColor.lightGrey
                )
            }
        }
    }
}

@Composable
fun ContestResultScreen(
    result: com.android.quizapp.features.contest.domain.entity.ContestResult,
    onBackClick: () -> Unit,
    onRetryClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColor.bgColorLight)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "🎉",
                style = MaterialTheme.typography.displayLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Contest Completed!",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = result.contestTitle,
                style = MaterialTheme.typography.titleMedium,
                color = AppColor.grey
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Result card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Score
                    Text(
                        text = "${result.totalScore}",
                        style = MaterialTheme.typography.displayLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = AppColor.blue
                        )
                    )
                    Text(
                        text = "Total Score",
                        style = MaterialTheme.typography.titleSmall,
                        color = AppColor.grey
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Stats
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ResultStatItem(
                            label = "Correct",
                            value = "${result.correctAnswers}",
                            color = AppColor.green
                        )
                        ResultStatItem(
                            label = "Wrong",
                            value = "${result.wrongAnswers}",
                            color = AppColor.red
                        )
                        ResultStatItem(
                            label = "Accuracy",
                            value = "${result.accuracy.toInt()}%",
                            color = AppColor.blue
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Rank
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(AppColor.blue.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Your Rank",
                                style = MaterialTheme.typography.labelMedium,
                                color = AppColor.grey
                            )
                            Text(
                                text = "#${result.rank}",
                                style = MaterialTheme.typography.headlineLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = AppColor.blue
                                )
                            )
                            Text(
                                text = "out of ${result.totalParticipants} participants",
                                style = MaterialTheme.typography.bodySmall,
                                color = AppColor.grey
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Back to Contests")
                }

                Button(
                    onClick = onRetryClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColor.blue
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Try Again")
                }
            }
        }
    }
}

@Composable
fun ResultStatItem(
    label: String,
    value: String,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = color
            )
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = AppColor.grey
        )
    }
}
