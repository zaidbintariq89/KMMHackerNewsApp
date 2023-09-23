package com.kmmhackernewsapp.shared.entity

import kotlinx.serialization.Serializable

@Serializable
data class PromotionsResponseModel(
    val content: PromotionContent,
    val status: Status
)

@Serializable
data class PromotionContent(
    val accounts: List<Promotion>
)