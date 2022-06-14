package com.example.login.entity

import java.util.*

data class Account(
    var ownerID: String,
    var CVU: String,
    var alias: String,
    var availableAmount: Double,
    var txHistory: List<TransactionDetail>

){

    constructor() : this(
        "",
        "",
        "",
        0.0,
        listOf()

    )


}



