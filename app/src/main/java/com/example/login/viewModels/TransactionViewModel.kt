package com.example.login.fragments.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.entity.Account
import com.example.login.fragments.TransactionFragment
import com.example.login.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.login.entity.TransactionDetail
import com.example.login.repository.AccountRepository

class TransactionViewModel : ViewModel() {

    var accountRepository = AccountRepository()

    lateinit var fragment : TransactionFragment

    var accountList = MutableLiveData<MutableList<TransactionDetail>>()


    @Throws(Exception::class)
    fun showData(fragment: TransactionFragment) {

        viewModelScope.launch(Dispatchers.Main) {

            try {
                var account = accountRepository.getAccountFrom()

                if (account != null) {
                    var amount = account.availableAmount.toString()
                    fragment.setAmount(amount)
                }

            } catch (e: Exception) {
                fragment.showMessage(e.message.toString())
            }
        }
    }

    fun getAccountList(){

        viewModelScope.launch(Dispatchers.Main) {
            var account = accountRepository.getAccountFrom()
            accountList.value = account.txHistory
        }

    }

}