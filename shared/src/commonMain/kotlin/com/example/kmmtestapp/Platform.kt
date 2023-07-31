package com.example.kmmtestapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform