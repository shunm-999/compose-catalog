package com.shunm.android.domain.github.repository

import com.shunm.android.domain.github.model.GithubUser

interface GithubUserRepository {
    fun getUserList(): List<GithubUser>
}
