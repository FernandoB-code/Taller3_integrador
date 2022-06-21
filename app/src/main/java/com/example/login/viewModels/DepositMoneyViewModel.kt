package com.example.login.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.entity.Account
import com.example.login.entity.User
import com.example.login.fragments.DepositMoneyFragment
import com.example.login.fragments.TransactionFragment
import com.example.login.repository.AccountRepository
import com.example.login.repository.TransactionRepository
import com.example.login.repository.UserRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DepositMoneyViewModel : ViewModel() {

    var transactionRepository = TransactionRepository()
    var accountRepository = AccountRepository()
    var userRepository = UserRepository

    private val db = Firebase.firestore
    private val auth = Firebase.auth

    lateinit var fragment : DepositMoneyFragment

    @Throws(Exception::class)
    fun setAccountDetail(fragment: DepositMoneyFragment) {

        viewModelScope.launch(Dispatchers.Main) {

            try {
                var account = accountRepository.getAccountFrom()

                if (account != null) {
                    var cbu = account.CVU
                    var alias = account.alias
                    var owner = account.ownerID
                    fragment.setCbuAlias(cbu, alias)
                }
            } catch (e: Exception) {
                fragment.showMessage(e.message.toString())
            }

        }
    }

    @Throws(Exception::class)
    fun setUserName(fragment: DepositMoneyFragment) {

        viewModelScope.launch(Dispatchers.Main) {

            try {
                var userName = userRepository.getUserName()

                if (userName.isNotEmpty()) {
                    fragment.setUserName(userName)
                }

            } catch (e: Exception) {
                fragment.showMessage(e.message.toString())
            }

        }
    }



}