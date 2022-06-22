package com.example.login.fragments.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.example.login.entity.TransactionDetail
import com.example.login.repository.AccountRepository

class TransactionViewModel : ViewModel() {

    var accountRepository = AccountRepository()
    var accountList = MutableLiveData<MutableList<TransactionDetail>>()


    fun showData( textAmount : TextView) {

        viewModelScope.launch(Dispatchers.Main) {
            val account = accountRepository.getAccountFrom()
            var amount = account.availableAmount.toString()
            textAmount.text = amount
        }
    }

    fun getAccountList(){

        viewModelScope.launch(Dispatchers.Main) {
            var account = accountRepository.getAccountFrom()
            accountList.value = account.txHistory
        }

    }

}