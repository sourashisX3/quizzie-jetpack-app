package com.android.quizapp.features.contest.di

import com.android.quizapp.features.contest.data.repository.ContestRepositoryImpl
import com.android.quizapp.features.contest.domain.repository.ContestRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for Contest dependency injection
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class ContestModule {

    @Binds
    @Singleton
    abstract fun bindContestRepository(
        contestRepositoryImpl: ContestRepositoryImpl
    ): ContestRepository
}

