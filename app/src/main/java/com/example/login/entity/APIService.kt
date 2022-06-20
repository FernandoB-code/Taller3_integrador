package com.example.login.entity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
interface APIService {
    @GET
    suspend fun getConversorByAmount(@Url url:String): Response<Convert>
}