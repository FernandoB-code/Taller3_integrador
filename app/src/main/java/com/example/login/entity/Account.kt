package com.example.login.entity

import java.util.*

data class Account(
    var ownerID: String,
    var CVU: String,
    var alias: String,
    var availableAmount: Double,
    var txHistory: MutableList<TransactionDetail>


){

    constructor() : this(
        "",
        "",
        "",
        0.0,
        mutableListOf()

    )


}



