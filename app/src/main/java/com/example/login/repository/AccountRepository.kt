package com.example.login.repository

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.login.entity.Account
import com.example.login.entity.User
import com.example.login.util.RandomWorldGenerator
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.security.AccessController.getContext

class AccountRepository {

    private val db = Firebase.firestore
    private val auth = Firebase.auth

    fun createUserAccount(email:String, context : Context) : Account{

        val cvu = generateRandomCV()
        val string = generateRandomAlias(context)

        var account= Account(email,cvu,generateRandomAlias(context), 2000.00, mutableListOf())
        db.collection("accounts").document(account.CVU).set(account)
        return account
    }

    private fun generateRandomCV(): String {
        var length = 22
        val charset = ('0'..'9')
        return List(length) { charset.random() }
            .joinToString("")
    }

    private fun generateRandomAlias(context : Context) : String {

        var random = RandomWorldGenerator()
        var list =  random.main(context)
        var string1 = list.random()
        var string2 = list.random()
        var string3 = list.random()
        var alias = string1 + "." + string2 + "." + string3

        return alias

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


    suspend fun getAccountByCVU(cvu: String): MutableList<Account> {

        var accountList = mutableListOf<Account>()

        try {

            val questionRef = db.collection("accounts")

            var data = questionRef.get().await()

            for (document in data) {
                Log.d(ContentValues.TAG, "${document.id} => ${document.data}")

                var actualAccount = document.toObject<Account>()

                actualAccount.toString()

                if (document.toObject<Account>().CVU == cvu) {

                    accountList.add(document.toObject<Account>())


                }


            }

            accountList.toString()

        } catch (e: Exception) {

            Log.d("getAccountByCVU", e.message.toString())

        }

        return accountList

    }

}



