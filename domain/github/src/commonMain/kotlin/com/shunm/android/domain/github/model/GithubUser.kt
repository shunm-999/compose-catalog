package com.shunm.android.domain.github.model

import kotlinx.serialization.Serializable

@Serializable
data class GithubUser(
    val id: GithubUserId,
    val login: String,
    val avatarUrl: String,
    val htmlUrl: String,
    val name: String?,
    val email: String?,
)
