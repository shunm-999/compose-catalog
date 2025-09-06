package com.shunm.android.infra.github.api.service

import kotlinx.serialization.Serializable
import retrofit2.http.GET

internal interface GithubUserService {
    @GET("user")
    suspend fun getUser(): List<GithubUserResponse>
}

@Serializable
internal data class GithubUserResponse(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val name: String? = null,
    val email: String? = null,
)
