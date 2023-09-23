package com.kmmhackernewsapp.shared.entity

import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val accountNumber: String,
    val accountTypeNumber: String,
    val alias: String,
    val autoPayPromoEligInd: String,
    val status: String
)