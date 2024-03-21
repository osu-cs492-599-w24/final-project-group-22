package com.example.foodrecipe.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodrecipe.data.AppDatabase
import com.example.foodrecipe.data.Meal
import com.example.foodrecipe.data.SavedRecipesRepository
import kotlinx.coroutines.launch

class SavedRecipesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SavedRecipesRepository(
        AppDatabase.getInstance(application).recipeDao()
    )

    val savedRecipes = repository.getAllRecipes().asLiveData()


    fun addSavedRecipe(meal: Meal) {
        viewModelScope.launch {
            repository.insertRecipe(meal)
        }
    }

    fun removeSavedRecipe(meal: Meal) {
        viewModelScope.launch {
            repository.deleteRecipe(meal)
        }
    }

}