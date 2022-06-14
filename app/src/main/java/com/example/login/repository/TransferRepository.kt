package com.example.login.repository

import android.content.ContentValues
import android.util.Log
import com.example.login.entity.Account
import com.example.login.entity.TransactionDetail
import com.example.login.entity.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

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
                .update("availableAmount", accountFrom.availableAmount-amount).await()

            val accountToFb = db.collection("accounts").document(accountTo.CVU)
                .update("availableAmount", accountTo.availableAmount+amount).await()

            testHistory(amount)

        }catch (e: Exception) {

        }

    }

    suspend fun testHistory(amount:Double){

        try{

            var accountFrom = this.getAccountFrom()

           var txDetail : TransactionDetail = TransactionDetail(amount, "13/06/2022")


            val accountFromFb = db.collection("accounts").document(accountFrom.CVU)
                .update("txHistory", txDetail).await()

        } catch (e: Exception) {

        }
    }


}