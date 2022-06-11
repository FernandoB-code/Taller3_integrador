package com.example.login.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.entity.Account
import com.example.login.repository.TransferRepository
import com.example.login.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransferMoneyViewModel : ViewModel() {

    // var accountsList : MutableList<Account>

    var repository = TransferRepository()

    fun transfer(cvuAlias: String, amount: Double) {
        var accountsList: MutableList<Account>

        if (!cvuAlias.isNullOrEmpty() && amount > 0.0) {

            viewModelScope.launch(Dispatchers.Main) {

                var accountsList = repository.getAccountByCVU(cvuAlias)

                var accountTO = accountsList[0]

                if (accountTO != null) {

                    var accountFROM: Account = repository.getAccountFrom()

                    accountFROM.toString()

                    if (accountFROM.availableAmount >= amount) {

                        repository.updateAmount(amount, accountFROM, accountTO)

                        //ver como implementar el historial

                    } else {

                        // error saldo insuficiente
                    }
                } else {

                    // error no existe la cuenta con el CVU xxxxxxxxxxxxxxxxxxxxxx

                }
            }
        } else {

            // error cvu invalido o saldo negativo

        }
    }
}





