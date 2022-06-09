package com.example.login.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login.entity.User
import com.example.login.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth

class NewProfileUserViewModel : ViewModel() {
    var success: MutableLiveData<String> = MutableLiveData()

    fun registerUser(
        name:String?,
        password:String?,
        mail:String?,
        dni: String?
    ){
        if(!name.isNullOrEmpty() && !password.isNullOrEmpty() && !mail.isNullOrEmpty() && !dni.isNullOrEmpty()){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail, password)
                .addOnSuccessListener {
                    val id = FirebaseAuth.getInstance().currentUser?.email.toString()
                    val account = UserRepository.createUserAccount(mail)
                    val accountId = account.CVU
                    UserRepository.saveUser(User(id,name, dni, mail, accountId), success)

                }
                .addOnFailureListener { exception -> success.postValue(exception.message) }
        } else success.postValue("Por favor completar todos los campos")
    }
}