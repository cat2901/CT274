package com.example.democatfactapi

import retrofit2.Call
import retrofit2.http.GET


interface ApiRequests {
    @GET("fact")
    fun getFact(): Call<CatJSON>
}