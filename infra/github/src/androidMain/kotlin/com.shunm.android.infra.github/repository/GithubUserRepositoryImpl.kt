package com.shunm.android.infra.github.repository

import com.shunm.android.domain.github.model.GithubUser
import com.shunm.android.domain.github.repository.GithubUserRepository
import com.shunm.android.domain.shared.ExceptionResult
import com.shunm.android.infra.github.api.RetrofitProvider
import com.shunm.android.infra.github.api.SafeApiCall
import com.shunm.android.infra.github.api.service.GithubUserService
import retrofit2.create
import javax.inject.Inject

internal class GithubUserRepositoryImpl @Inject constructor(
    private val safeApiCall: SafeApiCall,
    private val retrofitProvider: RetrofitProvider,
) : GithubUserRepository {
    override suspend fun getUserList(): ExceptionResult<List<GithubUser>> {
        val service = retrofitProvider.provide(RetrofitProvider.ProviderType.GITHUB).create<GithubUserService>()

        return safeApiCall {
            service.getUsers().map { it.toDomain() }
        }
    }
}
