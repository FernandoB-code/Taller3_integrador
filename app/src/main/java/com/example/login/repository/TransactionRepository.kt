package com.example.login.repository

import android.content.ContentValues
import android.icu.text.SimpleDateFormat
import android.util.Log
import com.example.login.entity.Account
import com.example.login.entity.Transaction
import com.example.login.entity.TransactionDetail
import com.example.login.entity.User
import com.example.login.util.enums.txTypeEnum
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.*

class TransactionRepository {

    private val db = Firebase.firestore
    private val auth = Firebase.auth

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

    suspend fun getAccountFrom(): Account {
        var account = Account()
        val questionRef = db.collection("users")
        try {
            var accountOwner= auth.currentUser?.email.toString()
            if(accountOwner != null) {
                val data = questionRef.document(accountOwner).get().await()
                var actualUser = data.toObject<User>()
                if(actualUser != null) {
                    val qRef= db.collection("accounts").document(actualUser.accountID).get().await()
                    if(qRef.exists() && qRef != null) {
                        account = qRef.toObject<Account>()!!
                    }
                }
            }
        } catch (e: Exception) {
            Log.v("Error", e.toString())
        }
        return account
    }
}