package com.example.login.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.entity.Account
import com.example.login.fragments.TransferMoneyFragment
import com.example.login.repository.AccountRepository
import com.example.login.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransferMoneyViewModel : ViewModel() {

    var transactionRepository = TransactionRepository()

    var accountRepository = AccountRepository()



    lateinit var fragment : TransferMoneyFragment



    @Throws(Exception::class)
    fun transfer(cvuAlias: String?, amount: Double, fragment: TransferMoneyFragment) {

            viewModelScope.launch(Dispatchers.Main) {

                try {

                    if (!cvuAlias.isNullOrEmpty() && amount > 0) {


                        var accountsList : MutableList<Account> = accountRepository.getAccountByCVU(cvuAlias)

                        if (accountsList.size != 0) { // valida la lista tenga un valor, si no, no encontró la cuenta

                            var accountTO = accountsList[0]

                            if (accountTO != null) {

                                var accountFROM: Account = accountRepository.getAccountFrom()

                                accountFROM.toString()

                                if (accountFROM.availableAmount >= amount) {

                                    transactionRepository.transfer(amount, accountFROM, accountTO)

                                } else {

                                    throw Exception("No tiene saldo suficiente")
                                }
                            }
                        } else {

                            throw Exception("No existe la cuenta con ese CVU")
                        }
                    } else {

                        throw Exception("Datos invalidos")

                    }

                fragment.showMessage("Transferencia realizada con exito")

                } catch (e : Exception) {

                    fragment.showMessage(e.message.toString())

                }
            }
        }
    }













