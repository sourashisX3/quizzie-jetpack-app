package com.android.quizapp.features.navbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import com.android.quizapp.R
import com.android.quizapp.core.constants.StringConstants
import com.android.quizapp.ui.theme.AppColor


data class NavBarItem(
    val id: Int,
    val title: String,
    val iconRes: Int,
    val route: String,
)

@Composable
fun AppNavBar(
    modifier: Modifier = Modifier,
    items: List<NavBarItem>,
    selectedRoute: String?,
    onItemSelected: (NavBarItem) -> Unit,
) {

    val unselectedIconColor = AppColor.grey
    val selectedBackground = AppColor.black
    val selectedContentColor = AppColor.white
    val density = LocalDensity.current
    val screenWidthDp = with(density) { LocalWindowInfo.current.containerSize.width.toDp() }
    val showLabelsOnWideScreen = screenWidthDp >= 600.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            shape = RoundedCornerShape(45.dp),
            color = AppColor.white,
            shadowElevation = 6.dp
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                items.forEach { item ->
                    val selected = item.route == selectedRoute
                    val showLabel = showLabelsOnWideScreen || selected

                    val tintColor by animateColorAsState(
                        targetValue = if (selected) selectedContentColor else unselectedIconColor,
                        animationSpec = tween(durationMillis = 300),
                        label = "tintColor"
                    )

                    val interactionSource = remember { MutableInteractionSource() }
                    val rippleRadius = if (selected) 26.dp else 24.dp

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .widthIn(min = 48.dp)
                            .semantics { this.selected = selected }
                    ) {
                        if (selected) {
                            Box(
                                modifier = Modifier
                                    .height(46.dp)
                                    .clip(RoundedCornerShape(26.dp))
                                    .background(selectedBackground)
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = ripple(
                                            bounded = true,
                                            radius = rippleRadius,
                                            color = AppColor.white.copy(alpha = 0.3f)
                                        ),
                                        onClick = { onItemSelected(item) },
                                        role = Role.Button
                                    )
                                    .padding(horizontal = 12.dp, vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Image(
                                        painter = painterResource(item.iconRes),
                                        contentDescription = item.title,
                                        modifier = Modifier.size(20.dp),
                                        colorFilter = ColorFilter.tint(tintColor)
                                    )
                                    AnimatedVisibility(
                                        visible = selected,
                                        enter = fadeIn(animationSpec = tween(300)) +
                                                expandHorizontally(animationSpec = tween(300)),
                                        exit = fadeOut(animationSpec = tween(200)) +
                                               shrinkHorizontally(animationSpec = tween(200))
                                    ) {
                                        Text(
                                            text = item.title,
                                            style = MaterialTheme.typography.labelSmall,
                                            color = selectedContentColor,
                                            maxLines = 1
                                        )
                                    }
                                }
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(24.dp))
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = ripple(
                                            bounded = true,
                                            radius = rippleRadius,
                                            color = AppColor.grey.copy(alpha = 0.2f)
                                        ),
                                        onClick = { onItemSelected(item) },
                                        role = Role.Button
                                    )
                                    .padding(horizontal = 8.dp, vertical = 12.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Image(
                                        painter = painterResource(item.iconRes),
                                        contentDescription = item.title,
                                        modifier = Modifier.size(22.dp),
                                        colorFilter = ColorFilter.tint(tintColor)
                                    )

                                    if (showLabel) {
                                        Text(
                                            text = item.title,
                                            color = unselectedIconColor,
                                            style = MaterialTheme.typography.labelSmall,
                                            maxLines = 1
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NavBarPreview() {
    val sampleItems = listOf(
        NavBarItem(0, StringConstants.HOME, R.drawable.home, "home"),
        NavBarItem(1, StringConstants.CONTEST, R.drawable.trophy, "contest"),
        NavBarItem(2, StringConstants.LEADERBOARD, R.drawable.leader_board, "leaderboard"),
        NavBarItem(3, StringConstants.PROFILE, R.drawable.profile, "profile"),
    )

    AppNavBar(
        modifier = Modifier,
        items = sampleItems,
        selectedRoute = "home",
        onItemSelected = {}
    )
}