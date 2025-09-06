package com.shunm.android.domain.github.usecase

import com.shunm.android.domain.github.model.GithubUser
import com.shunm.android.domain.github.repository.GithubUserRepository
import com.shunm.android.domain.shared.ExceptionResult
import javax.inject.Inject

class GetGithubUserListUseCase @Inject constructor(
    private val repository: GithubUserRepository,
) {
    suspend operator fun invoke(): ExceptionResult<List<GithubUser>> {
        return repository.getUserList()
    }
}
