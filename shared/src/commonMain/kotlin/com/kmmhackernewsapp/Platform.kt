package com.kmmhackernewsapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform