package com.example.ttappcontacts

import retrofit2.Call
import retrofit2.http.GET

interface ApiRequests {
    @GET("api/?results=10")
    fun getContacts(): Call<ResponeData>
}