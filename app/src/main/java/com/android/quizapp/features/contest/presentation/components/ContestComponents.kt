package com.android.quizapp.features.contest.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.android.quizapp.features.contest.domain.entity.Contest
import com.android.quizapp.features.contest.domain.entity.ContestDifficulty
import com.android.quizapp.features.contest.domain.entity.ContestStatus
import com.android.quizapp.features.contest.domain.entity.ContestType
import com.android.quizapp.ui.theme.AppColor

/**
 * Contest Card Component
 */
@Composable
fun ContestCard(
    contest: Contest,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header with image and badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                // Contest image
                AsyncImage(
                    model = contest.imageUrl,
                    contentDescription = contest.title,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(AppColor.lightGrey)
                )

                Spacer(modifier = Modifier.width(12.dp))

                // Title and type
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = contest.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        maxLines = 2,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Type and difficulty badges
                    Row {
                        ContestTypeBadge(type = contest.type)
                        Spacer(modifier = Modifier.width(4.dp))
                        DifficultyBadge(difficulty = contest.difficulty)
                    }
                }

                // Status badge
                ContestStatusBadge(status = contest.status)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Description
            Text(
                text = contest.description,
                style = MaterialTheme.typography.bodySmall,
                color = AppColor.grey,
                maxLines = 2,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Stats row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatItem(
                    icon = Icons.Default.People,
                    value = "${contest.totalParticipants}",
                    label = "Participants"
                )
                StatItem(
                    icon = Icons.Default.AccessTime,
                    value = "${contest.totalQuestions}Q",
                    label = "${contest.timePerQuestion}s each"
                )
                if (contest.prizePool != null) {
                    StatItem(
                        icon = Icons.Default.EmojiEvents,
                        value = contest.prizePool,
                        label = "Prize"
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Action button
            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = when (contest.status) {
                        ContestStatus.ONGOING -> AppColor.green
                        ContestStatus.UPCOMING -> AppColor.blue
                        ContestStatus.COMPLETED -> AppColor.grey
                    }
                )
            ) {
                Text(
                    text = when {
                        contest.status == ContestStatus.COMPLETED -> "View Results"
                        contest.isEnrolled -> "Start Contest"
                        else -> "Enroll Now"
                    }
                )
            }
        }
    }
}

@Composable
fun ContestTypeBadge(type: ContestType) {
    val (color, text) = when (type) {
        ContestType.DAILY -> AppColor.blue to "Daily"
        ContestType.WEEKLY -> AppColor.purple to "Weekly"
        ContestType.MONTHLY -> AppColor.orange to "Monthly"
        ContestType.SPECIAL -> AppColor.pink to "Special"
    }

    Box(
        modifier = Modifier
            .background(color.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = color,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun DifficultyBadge(difficulty: ContestDifficulty) {
    val (color, text) = when (difficulty) {
        ContestDifficulty.EASY -> AppColor.green to "Easy"
        ContestDifficulty.MEDIUM -> AppColor.yellow to "Medium"
        ContestDifficulty.HARD -> AppColor.orange to "Hard"
        ContestDifficulty.EXPERT -> AppColor.red to "Expert"
    }

    Box(
        modifier = Modifier
            .background(color.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = color,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ContestStatusBadge(status: ContestStatus) {
    val (color, text) = when (status) {
        ContestStatus.ONGOING -> AppColor.green to "Live"
        ContestStatus.UPCOMING -> AppColor.blue to "Soon"
        ContestStatus.COMPLETED -> AppColor.grey to "Ended"
    }

    Box(
        modifier = Modifier
            .background(color, RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun StatItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    value: String,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = AppColor.brown,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = AppColor.grey
        )
    }
}

/**
 * Timer display component
 */
@Composable
fun TimerDisplay(
    timeRemaining: Int,
    totalTime: Int,
    modifier: Modifier = Modifier
) {
    val progress = timeRemaining.toFloat() / totalTime.toFloat()
    val color = when {
        progress > 0.5f -> AppColor.green
        progress > 0.25f -> AppColor.yellow
        else -> AppColor.red
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier.size(80.dp),
            color = color,
            strokeWidth = 8.dp,
            trackColor = color.copy(alpha = 0.2f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "$timeRemaining",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = color
            )
        )
        Text(
            text = "seconds",
            style = MaterialTheme.typography.labelSmall,
            color = AppColor.grey
        )
    }
}

/**
 * Question option button
 */
@Composable
fun OptionButton(
    option: String,
    optionIndex: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val labels = listOf("A", "B", "C", "D")

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(unbounded = false),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) AppColor.blue else Color.White,
            contentColor = if (isSelected) Color.White else AppColor.black
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = if (isSelected) 8.dp else 2.dp
        ),
        contentPadding = PaddingValues(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        if (isSelected) Color.White else AppColor.blue.copy(alpha = 0.1f),
                        RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = labels.getOrNull(optionIndex) ?: "",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) AppColor.blue else AppColor.blue
                    )
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = option,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

/**
 * Contest Statistics Card with Graph
 */
@Composable
fun ContestStatisticsCard(
    statistics: com.android.quizapp.features.contest.domain.entity.ContestStatistics,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = "Your Statistics",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Stats grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatColumnItem(
                    label = "Total",
                    value = "${statistics.totalContestsParticipated}",
                    color = AppColor.blue
                )
                StatColumnItem(
                    label = "Avg Score",
                    value = "${statistics.averageScore.toInt()}",
                    color = AppColor.green
                )
                StatColumnItem(
                    label = "Best Rank",
                    value = "#${statistics.bestRank}",
                    color = AppColor.orange
                )
                StatColumnItem(
                    label = "Points",
                    value = "${statistics.totalPoints}",
                    color = AppColor.purple
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Simple bar chart for monthly stats
            Text(
                text = "Monthly Performance",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(8.dp))

            SimpleBarChart(
                data = statistics.monthlyStats.map { it.averageScore },
                labels = statistics.monthlyStats.map { it.month.take(3) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
    }
}

@Composable
fun StatColumnItem(
    label: String,
    value: String,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                color = color
            ),
            maxLines = 1
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = AppColor.grey,
            maxLines = 1
        )
    }
}

@Composable
fun SimpleBarChart(
    data: List<Double>,
    labels: List<String>,
    modifier: Modifier = Modifier
) {
    val maxValue = data.maxOrNull() ?: 100.0

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        data.forEachIndexed { index, value ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                // Bar
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height((value / maxValue * 100).dp)
                        .background(
                            AppColor.blue.copy(alpha = 0.7f),
                            RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                        )
                )
                Spacer(modifier = Modifier.height(4.dp))
                // Label
                Text(
                    text = labels.getOrNull(index) ?: "",
                    style = MaterialTheme.typography.labelSmall,
                    color = AppColor.grey
                )
            }
        }
    }
}

