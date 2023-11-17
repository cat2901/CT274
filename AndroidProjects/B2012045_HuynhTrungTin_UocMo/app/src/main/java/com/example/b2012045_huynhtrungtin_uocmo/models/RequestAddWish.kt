package com.example.b2012045_huynhtrungtin_uocmo.models

import com.google.gson.annotations.SerializedName

data class RequestAddWish(

    val idUser: String,
    //Annotation SerializedName dùng để đổi tên tham số truyền lên Server
    @SerializedName("name")

    val fullName: String,
    val content: String
)
