package com.kmmhackernewsapp.shared.entity

import kotlinx.serialization.Serializable

@Serializable
data class Promotion(
    val description: String,
    val id: String,
    val image: String,
    val link: String,
    val title: String
)