package com.example.login.entity

import java.time.LocalDate
import java.time.LocalTime


data class TransactionDetail(
    var transactionType: String,
    var amount : Double,
    val date : String,
    val time : String) {


    constructor() : this (
        "",
        0.0,
        "",
        ""

    )
}