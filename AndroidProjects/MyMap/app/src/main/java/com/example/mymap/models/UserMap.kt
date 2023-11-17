package com.example.mymap.Models

import java.io.Serializable

data class UserMap (
    val title: String,
    val places : List<Place>
    ):Serializable