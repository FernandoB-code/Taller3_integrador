package com.example.login.entity

import java.util.*

data class TransactionRequest(
    var transaction : Transaction,
    var state : State,
    var creationDate : Date,
    var requestCreator: String,
    var requestConsumer: String?
    )
