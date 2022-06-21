package com.example.login.repository

import android.icu.text.SimpleDateFormat
import android.util.Log
import com.example.login.entity.Account
import com.example.login.entity.TransactionDetail
import com.example.login.entity.User
import com.example.login.util.enums.txTypeEnum
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class TransactionRepository {

    private val db = Firebase.firestore
    private val auth = Firebase.auth

    suspend fun transfer(amount:Double, accountFrom:Account, accountTo:Account){

        try{
            val accountFromFb = db.collection("accounts").document(accountFrom.CVU)

            accountFromFb.update("availableAmount", accountFrom.availableAmount-amount).await()

            var transactionDetailTO : TransactionDetail = TransactionDetail(txTypeEnum.TRANSFER_SEND, accountTo.CVU,accountFrom.CVU, amount, LocalDate.now().toString(), LocalTime.now().toString()) //save tx history

            accountFromFb.update("txHistory", FieldValue.arrayUnion(transactionDetailTO))

            //

            val accountToFb = db.collection("accounts").document(accountTo.CVU)

            accountToFb.update("availableAmount", accountTo.availableAmount+amount).await()

            var transactionDetailFROM : TransactionDetail = TransactionDetail(txTypeEnum.TRANSFER_RESIVED,accountTo.CVU,accountFrom.CVU, amount, LocalDate.now().toString(), LocalTime.now().toString())

            accountToFb.update("txHistory", FieldValue.arrayUnion(transactionDetailFROM)) //save tx history


        }catch (e: Exception) {

        }

    }



}