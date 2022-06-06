package com.example.login.entity

import android.os.Parcel
import android.os.Parcelable
import java.util.*
import java.util.Random
import kotlin.random.Random.Default.nextInt


data class User(
    var firstName : String,
    var lastName : String,
    var dni : String,
    var email: String,
    var password: String,
    //var account: Account?
    var accountID: String

    )
{

    constructor() : this(
            "",
            "",
            "",
            "",
            "",
            ""
           )

    /*companion object{
        private fun generateAccount(): Account{
            var rand = Random()
            var accountNumber = List(10) { rand.nextInt(9) }
            println(accountNumber)
            var balance = 2000.0
            println(balance)
            var cvu = String
            println(cvu)
            var alias = "TRIGO.CASA.VELA"

            var account = Account(accountNumber,balance,cvu,alias)

            return account
        }

    }*/


    //Falta traer la cuenta
    fun transfer(transferRequest : TransferRequest){
        //account?.transfer(transferRequest)
    }



}




