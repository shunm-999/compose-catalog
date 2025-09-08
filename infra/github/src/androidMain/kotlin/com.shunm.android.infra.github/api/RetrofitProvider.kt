package com.shunm.android.infra.github.api

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RetrofitProvider @Inject constructor() {

    enum class ProviderType {
        GITHUB,
    }

    private val lock = Any()

    private val retrofits = mutableMapOf<String, Retrofit>()

    fun provide(type: ProviderType): Retrofit {
        return when (type) {
            ProviderType.GITHUB -> provide("https://api.github.com/")
        }
    }

    private fun provide(baseUrl: String): Retrofit {
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

    @OptIn(ExperimentalSerializationApi::class)
    private fun getRetrofit(
        baseUrl: String,
        client: OkHttpClient,
    ): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
            namingStrategy = JsonNamingStrategy.SnakeCase
        }

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json; charset=UTF8".toMediaType(),
                ),
            )
            .client(client)
            .build()
    }
}
