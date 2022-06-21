package com.example.login.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login.services.APIService
import com.example.login.fragments.ConvertFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConvertViewModel : ViewModel() {



    lateinit var fragment: ConvertFragment

    var money : Number = 0.0

    var convertionResult = MutableLiveData<Number>()


    // TODO: Implement the ViewModel
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://v6.exchangerate-api.com/v6/461d82411c02708209a95d88pair/ARS/USD/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun searchByAmount(query: Double) {

        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java)
                .getConversorByAmount("$query")

            val amount = call.body()

            if (call.isSuccessful) {
                if (amount != null) {
                    money = amount.conversion_result
                    try{
                        convertionResult.postValue(money)
                    } catch (e : Exception) {
                        Log.d("ERROR", e.message.toString())
                    }

                } else {
                    fragment.showMessage("A ocurrido un error")
                }
            }

        }
    }
}

