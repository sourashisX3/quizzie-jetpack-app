package com.android.quizapp.features.navbar

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.quizapp.core.navigation.Screen
import com.android.quizapp.features.contest.ContestScreen
import com.android.quizapp.features.home.presentation.screens.HomeScreen
import com.android.quizapp.features.leaderboard.LeaderBoardScreen
import com.android.quizapp.features.profile.presentation.screens.EditProfileScreen
import com.android.quizapp.features.profile.presentation.screens.ProfileScreen
import com.android.quizapp.ui.theme.AppColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    // Local NavController
    val mainNavController = rememberNavController()
    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Hide bottom bar
    val showBottomBar = currentRoute in BottomNavItems.map { it.route }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(AppColor.backgroundBrush)) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            bottomBar = {
                // Only show bottom bar on main tab screens
                if (showBottomBar) {
                    AppNavBar(
                        modifier = Modifier,
                        items = BottomNavItems,
                        selectedRoute = currentRoute,
                        onItemSelected = { item ->
                            mainNavController.navigate(item.route) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(mainNavController.graph.startDestinationId) { saveState = true }
                            }
                        }
                    )
                }
            }
        ) {
            NavHost(
                navController = mainNavController,
                startDestination = BottomNavItems.first().route,
                modifier = Modifier.fillMaxSize()
            ) {
                // --- Bottom Tab Screens ---
                composable(
                    route = BottomNavItems[0].route,
                    enterTransition = { fadeIn(animationSpec = tween(300)) },
                    exitTransition = { fadeOut(animationSpec = tween(300)) }
                ) {
                    HomeScreen()
                }
                composable(
                    route = BottomNavItems[1].route,
                    enterTransition = { fadeIn(animationSpec = tween(300)) },
                    exitTransition = { fadeOut(animationSpec = tween(300)) }
                ) {
                    ContestScreen()
                }
                composable(
                    route = BottomNavItems[2].route,
                    enterTransition = { fadeIn(animationSpec = tween(300)) },
                    exitTransition = { fadeOut(animationSpec = tween(300)) }
                ) {
                    LeaderBoardScreen()
                }
                composable(
                    route = BottomNavItems[3].route,
                    enterTransition = { fadeIn(animationSpec = tween(300)) },
                    exitTransition = { fadeOut(animationSpec = tween(300)) }
                ) {
                    ProfileScreen(navController = mainNavController)
                }

                // --- Nested Detail Screens ---
                // Edit Profile Screen
                composable(
                    route = Screen.EditProfile.route,
                    enterTransition = { fadeIn(animationSpec = tween(300)) },
                    exitTransition = { fadeOut(animationSpec = tween(300)) }
                ) {
                    EditProfileScreen(navController = mainNavController)
                }
            }
        }
    }
}
