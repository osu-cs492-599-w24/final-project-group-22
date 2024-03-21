package com.example.foodrecipe.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meal(
    val id: Int,
    val title: String,
    val image: String
)
