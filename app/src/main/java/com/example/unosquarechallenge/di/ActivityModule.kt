package com.example.unosquarechallenge.di

import com.example.unosquarechallenge.repository.PostRepositoryImpl
import com.example.unosquarechallenge.repository.PostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {
    @Binds
    abstract fun bindPostRepoImpl(repoImpl: PostRepositoryImpl): PostRepository
}