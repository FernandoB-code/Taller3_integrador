package com.example.login.repository


import com.example.login.entity.Account
import com.example.login.entity.TransactionDetail
import com.example.login.util.enums.txTypeEnum
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.LocalTime


class TransactionRepository {

    private val db = Firebase.firestore

    suspend fun transfer(amount:Double, accountFrom:Account, accountTo:Account){

        try{
            val accountFromFB = db.collection("accounts").document(accountFrom.CVU)

            accountFromFB.update("availableAmount", accountFrom.availableAmount-amount).await()

            var transactionDetailTO : TransactionDetail = TransactionDetail(txTypeEnum.TRANSFER_SEND, accountTo.CVU,accountFrom.CVU, amount, LocalDate.now().toString(), LocalTime.now().toString()) //save tx history

            accountFromFB.update("txHistory", FieldValue.arrayUnion(transactionDetailTO))

            //

            val accountToFB = db.collection("accounts").document(accountTo.CVU)

            accountToFB.update("availableAmount", accountTo.availableAmount+amount).await()

            var transactionDetailFROM : TransactionDetail = TransactionDetail(txTypeEnum.TRANSFER_RESIVED,accountTo.CVU,accountFrom.CVU, amount, LocalDate.now().toString(), LocalTime.now().toString())

            accountToFB.update("txHistory", FieldValue.arrayUnion(transactionDetailFROM)) //save tx history


        }catch (e: Exception) {

        }

    }
}