package com.example.login.entity

import java.util.*

data class Account(
    var ownerID : String,
    var CVU : String,
    var alias : String,
    var availableAmount : Double

){

    constructor() : this(
        "",
        "",
        "",
        0.0
    )

    /*fun transfer(transferRequest : TransferRequest){

        var amount = transferRequest.transferAmount
        var res = checkAmount(amount)

        if(res==1){
            registerTransaction(transferRequest.requestConsumer,
                cvu,amount,transferRequest.creationDate)
            balance-=amount
            transferRequest.changeRequestState(State.APPROVED)
        }else{
            transferRequest.changeRequestState(State.CANCELED)
        }

    }

    private fun checkAmount(amount:Double) : Int{
        var res: Int

        if(balance>=amount){
            res=1
        }else{
            res=0
        }

        return res
    }

    private fun registerTransaction(
        requestConsumer: String,
        cvu: String,
        transferAmount: Double,
        creationDate: String
    ) {
        var t: Transaction
        t = Transaction(requestConsumer, cvu,transferAmount,creationDate)
    }*/
}



