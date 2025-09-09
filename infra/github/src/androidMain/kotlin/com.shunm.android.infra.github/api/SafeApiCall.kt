package com.shunm.android.infra.github.api

import com.shunm.android.domain.shared.Err
import com.shunm.android.domain.shared.ExceptionResult
import com.shunm.android.domain.shared.Ok
import javax.inject.Inject

class SafeApiCall @Inject constructor() {
    suspend operator fun <T> invoke(apiCall: suspend () -> T): ExceptionResult<T> = try {
        Ok(apiCall())
    } catch (e: Exception) {
        println("⭐️ SafeApiCall Error: $e")
        Err(e)
    }
}
