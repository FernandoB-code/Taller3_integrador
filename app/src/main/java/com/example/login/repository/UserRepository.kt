package com.example.login.repository

import androidx.lifecycle.MutableLiveData
import com.example.login.entity.Account
import com.example.login.entity.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

object UserRepository {

    private val auth = Firebase.auth
    //private val db = Firebase.firestore

    private val db: FirebaseFirestore by lazy { Firebase.firestore }

    fun getUser(liveData: MutableLiveData<User>, email: String) {
        db.collection("users").document(email).get()
            .addOnSuccessListener {
                liveData.postValue(it.toObject())
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }

    fun saveUser(user: User, mutableLiveData: MutableLiveData<String>) {
        db.collection("users").document(user.email).set(user)
            .addOnSuccessListener {
                mutableLiveData.postValue("success")
            }
            .addOnFailureListener { exception ->
                println("Error saving document: ${exception.message}")
                mutableLiveData.postValue("Error creating User: ${exception.message}")
            }
    }

    fun createUserAccount(email:String) : Account{
        val cvu = generateRandomCV()
        var account= Account(email,cvu,"ALIAS", 2000.00, listOf())
        db.collection("accounts").document(account.CVU).set(account)
        // var accountRef = account.result.id // asi se pide el id (referencia)
        //return account.result.id
        return account
    }

    fun generateRandomCV(): String {
        var length = 22
        val charset = ('0'..'9')
        return List(length) { charset.random() }
            .joinToString("")
    }

    fun getEmail() : String{
        return auth.currentUser?.email.toString()
    }

}