package com.android.quizapp.features.leaderboard.di

import com.android.quizapp.features.leaderboard.data.repository.LeaderboardRepositoryImpl
import com.android.quizapp.features.leaderboard.domain.repository.LeaderboardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for Leaderboard dependency injection
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class LeaderboardModule {

    @Binds
    @Singleton
    abstract fun bindLeaderboardRepository(
        leaderboardRepositoryImpl: LeaderboardRepositoryImpl
    ): LeaderboardRepository
}

