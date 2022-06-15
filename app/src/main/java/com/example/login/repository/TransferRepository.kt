package com.example.login.repository

import android.content.ContentValues
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
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

class TransferRepository() {

    private val db = Firebase.firestore
    private val auth = Firebase.auth

    suspend fun getAccountByCVU(cvu : String): MutableList<Account> {

        var accountList = mutableListOf<Account>()

        try {

            val questionRef = db.collection("accounts")

            var data = questionRef.get().await()

            for (document in data) {
                Log.d(ContentValues.TAG, "${document.id} => ${document.data}")

                var actualAccount = document.toObject<Account>()

                actualAccount.toString()

                if(document.toObject<Account>().CVU == cvu) {

                    accountList.add(document.toObject<Account>())



                }


            }

            accountList.toString()

        } catch (e: Exception) {

            Log.d("getAccountByCVU", e.message.toString())

        }

        return accountList

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

        }
        return account
    }

    suspend fun updateAmount(amount:Double, accountFrom:Account, accountTo:Account){

        try{
            val accountFromFb = db.collection("accounts").document(accountFrom.CVU)

            accountFromFb.update("availableAmount", accountFrom.availableAmount-amount).await()

            var transactionDetailTO : TransactionDetail = TransactionDetail(txTypeEnum.TRANSFER_TO, amount, LocalDate.now().toString(), LocalTime.now().toString())

            accountFromFb.update("txHistory", FieldValue.arrayUnion(transactionDetailTO))

            //

            val accountToFb = db.collection("accounts").document(accountTo.CVU)

            accountToFb.update("availableAmount", accountTo.availableAmount+amount).await()

            var transactionDetailFROM : TransactionDetail = TransactionDetail(txTypeEnum.TRANSFER_TO, amount, LocalDate.now().toString(), LocalTime.now().toString())

            accountToFb.update("txHistory", FieldValue.arrayUnion(transactionDetailFROM))


        }catch (e: Exception) {

        }

    }


    }
