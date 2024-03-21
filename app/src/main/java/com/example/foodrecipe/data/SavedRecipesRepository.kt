package com.example.foodrecipe.data

class SavedRecipesRepository(
    private val dao: RecipeDao
) {

    suspend fun insertRecipe(meal: Meal) = dao.insert(meal)

    suspend fun deleteRecipe(meal: Meal) = dao.delete(meal)

    fun getAllRecipes() = dao.getAllRecipes()

}