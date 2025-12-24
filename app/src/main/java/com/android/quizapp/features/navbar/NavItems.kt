package com.android.quizapp.features.navbar

import com.android.quizapp.R
import com.android.quizapp.core.constants.StringConstants

val BottomNavItems = listOf(
    NavBarItem(0, StringConstants.HOME, R.drawable.home, "home"),
    NavBarItem(1, StringConstants.CONTEST, R.drawable.trophy, "contest"),
    NavBarItem(2, StringConstants.LEADERBOARD, R.drawable.leader_board, "leaderboard"),
    NavBarItem(3, StringConstants.PROFILE, R.drawable.profile, "profile"),
)

