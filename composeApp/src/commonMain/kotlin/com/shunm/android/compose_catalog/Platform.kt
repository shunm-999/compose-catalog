package com.shunm.android.compose_catalog

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform