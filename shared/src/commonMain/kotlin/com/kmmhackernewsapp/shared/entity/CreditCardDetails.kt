package com.kmmhackernewsapp.shared.entity

import kotlinx.serialization.Serializable

@Serializable
data class CreditCardDetails(
    val expiry: String,
    val number: String,
    val type: String
)