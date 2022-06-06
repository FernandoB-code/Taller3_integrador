package com.example.login.repository

import android.icu.text.SimpleDateFormat
import com.example.login.entity.Transaction
import java.time.LocalDate.now
import java.time.LocalDateTime.now
import java.util.*

class TransactionRepository {

    var transactionList : MutableList<Transaction> = mutableListOf()

    init {

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        println(currentDate)

        transactionList.add(Transaction("a1b2c3","b1c2d3",150.10, currentDate.toString()))
        transactionList.add(Transaction("a1b2c3","b1c2d3",160.10,currentDate.toString()))
        transactionList.add(Transaction("a1b2c3","b1c2d3",170.10,currentDate.toString()))
        /*transactionList.add(Transaction(160.10, "TRANSFER CVU"))
        transactionList.add(Transaction(170.10, "TRANSFER ALIAS"))
        transactionList.add(Transaction(180.10, "TRANSFER"))
        transactionList.add(Transaction(190.10, "TRANSFER"))
        transactionList.add(Transaction(200.10, "TRANSFER"))*/

    }

}