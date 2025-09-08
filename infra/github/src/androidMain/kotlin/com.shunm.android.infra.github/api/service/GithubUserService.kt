package com.shunm.android.infra.github.api.service

import com.shunm.android.domain.github.model.GithubUser
import com.shunm.android.domain.github.model.GithubUserId
import kotlinx.serialization.Serializable
import retrofit2.http.GET

internal interface GithubUserService {
    @GET("users")
    suspend fun getUsers(): List<GithubUserResponse>
}

@Serializable
internal data class GithubUserResponse(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val htmlUrl: String,
    val name: String? = null,
    val email: String? = null,
) {
    fun toDomain(): GithubUser {
        return GithubUser(
            login = login,
            id = GithubUserId(id),
            avatarUrl = avatarUrl,
            htmlUrl = htmlUrl,
            name = name,
            email = email,
        )
    }
}
