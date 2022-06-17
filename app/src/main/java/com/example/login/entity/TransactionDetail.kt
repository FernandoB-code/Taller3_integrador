package com.example.login.entity

import com.example.login.util.enums.txTypeEnum
import java.time.LocalDate
import java.time.LocalTime


data class TransactionDetail(
    var transactionType: txTypeEnum,
    var accountTO: String,
    var accountFROM: String,
    var amount : Double,
    val date : String,
    val time : String) {


    constructor() : this (
        txTypeEnum.DEFAULT,
        "",
        "",
        0.0,
        "",
        ""
    )
}