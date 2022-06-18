package com.example.login.viewModels

import androidx.lifecycle.ViewModel
import com.example.login.entity.APIService
import com.example.login.fragments.ConvertFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConvertViewModel : ViewModel() {
    lateinit var money : Number
    lateinit var fragment: ConvertFragment
    // TODO: Implement the ViewModel
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://v6.exchangerate-api.com/v6/461d82411c02708209a95d88pair/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun searchByAmount(query: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java)
                .getConversorByAmount("$query/conversion_result")


            val amount = call.body()

            if (call.isSuccessful) {
                if (amount != null) {
                    var money = amount.conversion_result
                } else {
                    fragment.showMessage("A ocurrido un error")
                }
            }

        }
    }
    fun sendMoney(): Number {
        return this.money
    }

}