package com.example.login.repository

import com.example.login.entity.Transaction

class TransactionRepository {

    var transactionList : MutableList<Transaction> = mutableListOf()

    init {
        transactionList.add(Transaction("a1b2c3","b1c2d3",150.10))
        transactionList.add(Transaction("a1b2c3","b1c2d3",160.10))
        transactionList.add(Transaction("a1b2c3","b1c2d3",170.10))
        /*transactionList.add(Transaction(160.10, "TRANSFER CVU"))
        transactionList.add(Transaction(170.10, "TRANSFER ALIAS"))
        transactionList.add(Transaction(180.10, "TRANSFER"))
        transactionList.add(Transaction(190.10, "TRANSFER"))
        transactionList.add(Transaction(200.10, "TRANSFER"))*/

    }

}