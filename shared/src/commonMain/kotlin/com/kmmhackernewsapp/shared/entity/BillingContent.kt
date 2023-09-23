package com.kmmhackernewsapp.shared.entity

import kotlinx.serialization.Serializable

@Serializable
data class BillingContent(
    val accountId: String,
    val accountNumber: String,
    val availableCreditAmount: Double,
    val balanceAmount: Double,
    val billCycleEndDate: String,
    val billCycleStartDate: String,
    val billType: String,
    val billingCycleDay: Int,
    val creditLimit: Double,
    val currentBillCycleEndDate: String,
    val currentBillCycleStartDate: String,
    val delinquentStatus: String,
    val exceedCreditLimit: Boolean,
    val lastPaymentDate: String,
    val lastUpdated: Long,
    val methodOfPayment: MethodOfPayment,
    val minPaymentAmount: Int,
    val nextBillCycleStartDate: String,
    val nextBillDate: String,
    val nextBillingDate: String,
    val pastDueBalance: Double,
    val recommandedPaymentAmount: Double,
    val requiredPaymentDate: String,
    val unbilledUsageAmount: Int
)