package com.example.login.viewModels

import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.repository.AccountRepository
import com.example.login.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DepositMoneyViewModel : ViewModel() {

    var accountRepository = AccountRepository()
    var userRepository = UserRepository

    fun setAccountDetail( cbu : TextView, alias: TextView) {

        viewModelScope.launch(Dispatchers.Main) {
                val account = accountRepository.getAccountFrom()
                val cbuAccount = account.CVU
                val aliasAccount = account.alias
                cbu.text = cbuAccount
                alias.text = aliasAccount
        }

    }

    fun setUserName(name : TextView) {

        viewModelScope.launch(Dispatchers.Main) {
            val userName = userRepository.getUserName()
            name.text = userName
        }

    }

}