package com.kmmhackernewsapp.shared.entity

import kotlinx.serialization.Serializable

@Serializable
data class MethodOfPayment(
    val creditCardDetails: CreditCardDetails,
    val type: String
)