package com.example.foodrecipe.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Meal(
    @PrimaryKey
    val id: Int,
    val title: String,
    val image: String
)
