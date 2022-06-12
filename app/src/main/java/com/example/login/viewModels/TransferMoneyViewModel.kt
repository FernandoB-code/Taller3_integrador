package com.example.login.viewModels

import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.entity.Account
import com.example.login.fragments.TransferMoneyFragment
import com.example.login.repository.TransferRepository
import com.example.login.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransferMoneyViewModel : ViewModel() {

    var repository = TransferRepository()

    lateinit var fragment : TransferMoneyFragment

    @Throws(Exception::class)
    fun transfer(cvuAlias: String?, amount: Double, fragment: TransferMoneyFragment) {

            viewModelScope.launch(Dispatchers.Main) {

                try {

                    if (!cvuAlias.isNullOrEmpty() && amount > 0) {


                        var accountsList : MutableList<Account> = repository.getAccountByCVU(cvuAlias)

                        if (accountsList.size != 0) { // valida la lista tenga un valor, si no, no encontró la cuenta

                            var accountTO = accountsList[0]

                            if (accountTO != null) {

                                var accountFROM: Account = repository.getAccountFrom()

                                accountFROM.toString()

                                if (accountFROM.availableAmount >= amount) {

                                    repository.updateAmount(amount, accountFROM, accountTO)

                                    //ver como implementar el historial

                                } else {

                                    // error saldo insuficiente
                                    throw Exception("No tiene saldo suficiente")
                                }
                            }
                        } else {

                            // // error no existe la cuenta con el CVU xxxxxxxxxxxxxxxxxxxxxx
                            throw Exception("No existe la cuenta con ese CVU")
                        }
                    } else {

                        // // CVU null o vacio
                        throw Exception("Datos invalidos")

                    }

                    fragment.showMessage("Transferencia realizada con exito")

                } catch (e : Exception) {

                    fragment.showMessage(e.message.toString())

                }
            }
        }
    }













