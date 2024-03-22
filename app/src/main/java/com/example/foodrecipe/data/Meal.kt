package com.example.foodrecipe.data

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Meal(
    val id: Int,
    val title: String,
    val image: String
) : Serializable
