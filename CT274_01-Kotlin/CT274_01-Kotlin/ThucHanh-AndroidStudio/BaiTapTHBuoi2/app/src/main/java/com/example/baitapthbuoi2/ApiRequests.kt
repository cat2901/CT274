package com.example.baitapthbuoi2

import retrofit2.Call
import retrofit2.http.GET

interface ApiRequests {
    @GET("quotes/random?limit=3")
    fun getQuotes(): Call<ResponeData>
}