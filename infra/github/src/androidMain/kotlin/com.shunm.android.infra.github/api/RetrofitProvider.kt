package com.shunm.android.infra.github.api

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RetrofitProvider @Inject constructor() {

    private val lock = Any()

    private val retrofits = mutableMapOf<String, Retrofit>()

    fun provide(baseUrl: String): Retrofit {
        return synchronized(lock) {
            retrofits[baseUrl] ?: getRetrofit(baseUrl, getOkHttpClient()).also {
                retrofits[baseUrl] = it
            }
        }
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        setLevel(HttpLoggingInterceptor.Level.HEADERS)
                    },
            )
            .build()
    }

    private fun getRetrofit(
        baseUrl: String,
        client: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(
                Json.asConverterFactory(
                    "application/json; charset=UTF8".toMediaType(),
                ),
            )
            .build()
    }
}
