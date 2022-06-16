package com.example.login.fragments.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.entity.Account
import com.example.login.fragments.TransactionFragment
import com.example.login.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.util.Log

class TransactionViewModel : ViewModel() {

    var repository = TransactionRepository()

    lateinit var fragment : TransactionFragment

    @Throws(Exception::class)
    fun showData(fragment: TransactionFragment) {

        viewModelScope.launch(Dispatchers.Main) {

            try {
                var account = repository.getAccountFrom()

                if (account != null) {
                    var amount = account.availableAmount.toString()
                    fragment.setAmount(amount)
                }

            } catch (e: Exception) {
                fragment.showMessage(e.message.toString())
            }
        }
    }

}