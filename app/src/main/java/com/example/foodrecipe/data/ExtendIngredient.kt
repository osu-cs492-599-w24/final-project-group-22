package com.example.foodrecipe.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExtendIngredient(
    val nameClean: String,
    val original: String,
    val consistency: String,
    val aisle: String
)
