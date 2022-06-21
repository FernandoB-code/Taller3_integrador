package com.example.login.repository

import android.util.Log
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
import kotlinx.coroutines.tasks.await

object UserRepository {

    private val auth = Firebase.auth


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