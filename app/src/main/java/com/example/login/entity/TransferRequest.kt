package com.example.login.entity

import java.util.*

data class TransferRequest(
    var transferAmount: Double,
    var requestConsumer: String,
    //var creationDate: Date,
    var creationDate: String,
    var state: State
){
    constructor() : this (
        0.0,
        "",
        "",
        //Calendar.getInstance().time()
        State.PENDING
    )

    open fun changeRequestState(state:State){
        this.state=state
    }

}
