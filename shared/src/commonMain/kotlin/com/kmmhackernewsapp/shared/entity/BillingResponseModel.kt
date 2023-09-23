package com.kmmhackernewsapp.shared.entity

import kotlinx.serialization.Serializable

@Serializable
data class BillingResponseModel(
    val content: BillingContent,
    val status: Status
)