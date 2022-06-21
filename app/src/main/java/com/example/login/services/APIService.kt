package com.example.login.services
import com.example.login.entity.Convert
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
interface APIService {
    @GET
    suspend fun getConversorByAmount(@Url url:String): Response<Convert>
}