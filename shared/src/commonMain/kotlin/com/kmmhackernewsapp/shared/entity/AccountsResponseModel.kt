package com.kmmhackernewsapp.shared.entity

import kotlinx.serialization.Serializable

@Serializable
data class AccountsResponseModel(
    val content: AccountContent,
    val status: Status
)

@Serializable
data class Status(
    val code: String,
    val codeName: String
)

@Serializable
data class AccountContent(
    val accounts: List<Account>
)