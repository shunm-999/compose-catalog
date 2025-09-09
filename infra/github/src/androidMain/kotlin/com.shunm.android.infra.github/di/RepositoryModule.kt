package com.shunm.android.infra.github.di

import com.shunm.android.domain.github.repository.GithubUserRepository
import com.shunm.android.infra.github.repository.GithubUserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal interface RepositoryModule {
    @Binds
    fun bindGithubUserRepository(
        impl: GithubUserRepositoryImpl,
    ): GithubUserRepository
}
