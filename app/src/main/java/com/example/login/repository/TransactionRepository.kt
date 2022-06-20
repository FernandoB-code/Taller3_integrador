package com.example.login.repository

import android.icu.text.SimpleDateFormat
import android.util.Log
import com.example.login.entity.Account
import com.example.login.entity.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.*

class TransactionRepository {

    private val db = Firebase.firestore
    private val auth = Firebase.auth

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

    suspend fun getUserName(): String {
        var userName : String = ""
        val questionRef = db.collection("users")
        try {
            var accountOwner= auth.currentUser?.email.toString()
            if(accountOwner != null) {
                val data = questionRef.document(accountOwner).get().await()
                var actualUser = data.toObject<User>()
                if(actualUser != null) {
                    userName = actualUser.name
                    }
                }
        } catch (e: Exception) {
            Log.v("Error", e.toString())
        }
        return userName
    }
}