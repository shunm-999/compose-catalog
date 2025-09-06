package com.shunm.android.domain.github.repository

import com.shunm.android.domain.github.model.GithubUser
import com.shunm.android.domain.shared.ExceptionResult

interface GithubUserRepository {
    suspend fun getUserList(): ExceptionResult<List<GithubUser>>
}
