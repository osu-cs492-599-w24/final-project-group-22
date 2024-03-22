package com.example.foodrecipe.data

import com.squareup.moshi.JsonClass
import java.io.Serializable
@JsonClass(generateAdapter = true)
data class FoodRecipe(
    val extendedIngredients: List<ExtendIngredient>,
    val description: String,
    val image: String
) : Serializable
