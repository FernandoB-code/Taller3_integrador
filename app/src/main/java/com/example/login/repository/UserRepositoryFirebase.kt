package com.example.login.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.login.entity.Account
import com.example.login.entity.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserRepositoryFirebase {

    private val auth = Firebase.auth
    private val db = Firebase.firestore


    var email: String = "fernandobernasconi@gmail.com"
    var password: String = "test100"

    fun login(email: String, password: String): Task<AuthResult> {

        return auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    createUserAndAccount(email, password)
                } else {
                    // Logica
                    Log.d(TAG, "createUserWithEmail:error")

                }
            }
    }

    fun createUserAndAccount(
        email: String,
        password: String
    ) { // los parametros deberian mandarse desde la vista

        val users = db.collection("users")
        val accounts = db.collection("accounts")


        var userToCreate: User = User("fer", "", "", email, password, "")

        users.document(email).set(userToCreate)


        var accountToCreate: Account = Account(email, generateRandomCV(22), "", 0.0)


        var newAccountCreated =
            db.collection("accounts").add(accountToCreate) //creación de la cuenta

        var accountReference = newAccountCreated.result.id // asi se pide el id (referencia)

        Log.d("IDCUENTATEST", accountReference)


        // var account2 = accounts.document(email).set(account)
        val actualUser = db.collection("users").document(email)


        actualUser
            .update("accountID", accountReference)
            .addOnSuccessListener { Log.d(TAG, "Cuenta agregada con exito") }
            .addOnFailureListener { e -> Log.w(TAG, "Error al crear cuenta", e) }


        //obtener datos del usuario por mail

        db.collection("users")
            .whereEqualTo("email", email)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        // Logging the ID of your desired document & the document data itself
                        Log.d(TAG, document.id + " => " + document.data)
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }
    }

    fun generateRandomCV(length: Int): String {
        val charset = ('0'..'9')

        return List(length) { charset.random() }
            .joinToString("")
    }


    fun transfer(
        userFrom: String,
        amount: Double
    ) { // ver si el userTo puede ser el usuario actual logueado

        var actualUser = auth.currentUser

        validateUserFromAccountExists(userFrom)
    }

    fun validateUserFromAccountExists(CVU: String) {

        var exist: Boolean = false

        var accountsDB = db.collection("accounts")


        val searchedAccount = accountsDB.whereEqualTo("cvu", CVU)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        // Logging the ID of your desired document & the document data itself
                        var monto = document.get("availableAmount")
                        var monto2 = monto.toString().toDoubleOrNull()

                        //COMO SE HACE PARA PODER TRABAJAR FUERA DE ESTE LISTENER Y RETORNAR VALORES!! LPT!!

                    }

                } else {
                    Log.d("Test", "Current data: null")
                }
            }
    }

    fun validateIfUserHaveAmount(amount: Double)  {

        var actualUser = auth.currentUser
        var hasAmount : Boolean = false

        db.collection("accounts")
            .whereEqualTo("accountID",  actualUser!!.email)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var actualAccount =  db.collection("accounts").document(actualUser.email.toString())
                    actualAccount.get().result.get("amount")
                }
            }
    }
}