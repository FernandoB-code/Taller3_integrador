package com.example.login.repository

import com.example.login.entity.Transaction

class TransactionRepository {

    var transactionList : MutableList<Transaction> = mutableListOf()

    init {
        transactionList.add(Transaction(150.10, "TRANSFER"))
        transactionList.add(Transaction(160.10, "TRANSFER"))
        transactionList.add(Transaction(170.10, "TRANSFER"))
        transactionList.add(Transaction(180.10, "TRANSFER"))
        transactionList.add(Transaction(190.10, "TRANSFER"))
        transactionList.add(Transaction(200.10, "TRANSFER"))

    }

}