package com.example.b2012045_mymap.models

import java.io.Serializable

data class UserMap(
    val title: String,
    val places: List<Place>
): Serializable

