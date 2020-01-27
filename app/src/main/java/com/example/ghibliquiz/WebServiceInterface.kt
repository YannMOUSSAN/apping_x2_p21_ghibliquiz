package com.example.ghibliquiz

import retrofit2.Call
import retrofit2.http.GET

interface WebServiceInterface {

    @GET("people")
    fun listAllPeople(): Call<MutableList<Characters>>
}