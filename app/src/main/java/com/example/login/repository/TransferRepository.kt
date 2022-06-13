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

                if(document.toObject<Account>().CVU == cvu) {
                    accountList.add(document.toObject<Account>())
                }


            }

            accountList.toString()

        } catch (e: Exception) {

        }

        return accountList

    }

    suspend fun getAccountFrom(): Account {

        var account = Account()

        val questionRef = db.collection("users")

        try {
            var accountOwner= auth.currentUser?.email.toString()
            val data = questionRef.document(accountOwner).get().await()
            var accountId = data.toObject<User>()?.accountID.toString()
            val qRef= db.collection("accounts").document(accountId).get().await()
            account = qRef.toObject<Account?>()!!

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

           var txDetail : TransactionDetail = TransactionDetail("transfer", amount, "13/06/2022", "18:55")


            val accountFromFb = db.collection("accounts").document(accountFrom.CVU)
                .update("txHistory", txDetail).await()

        } catch (e: Exception) {

        }
    }


}