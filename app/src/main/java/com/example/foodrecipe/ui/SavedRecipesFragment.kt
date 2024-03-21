package com.example.foodrecipe.ui

import FoodSearchAdapter
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipe.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.foodrecipe.data.Meal
import com.example.foodrecipe.data.RecipesEntity

class SavedRecipesFragment : Fragment(R.layout.fragment_recipe_saved) {
    private val recipeViewModel: SavedRecipesViewModel by viewModels()
    private val adapter: FoodSearchAdapter = FoodSearchAdapter()
    private lateinit var forecastListRV: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        forecastListRV = view.findViewById<RecyclerView>(R.id.rv_search_results)

        forecastListRV.layoutManager = LinearLayoutManager(requireContext())
        forecastListRV.setHasFixedSize(true)

        forecastListRV.adapter = adapter


        // Displays livedata from databse

        recipeViewModel.savedRecipes.observe(viewLifecycleOwner) {
            recipes ->
                val meals : MutableList<Meal> = mutableListOf()
                for (recipe in recipes) {
                    meals.add(recipe.toMeal())
                }
                adapter.updateMealList(meals)
        }

        // TEST CASES FOR DATABASE

//        // Adding Recipes Manually
//        val testRecipe1 = RecipesEntity(1, "Borgor", "b.png")
//        val testRecipe2 = RecipesEntity(2, "Donot", "circle.png")
//        val testRecipe3 = RecipesEntity(3, "SandWitch", "sand.png")
//        val testRecipe4 = RecipesEntity(4, "SurvivesTheCulling", "alive.png")
//
//        recipeViewModel.addSavedRecipe(testRecipe1)
//        recipeViewModel.addSavedRecipe(testRecipe2)
//        recipeViewModel.addSavedRecipe(testRecipe3)
//        recipeViewModel.addSavedRecipe(testRecipe4)
//
//
//        // Removing Recipes from Database
//
//        // VERSION 1
//        recipeViewModel.removeSavedRecipe(testRecipe1)
//
//        // VERSION 2
//        recipeViewModel.removeSavedRecipeById(2, viewLifecycleOwner)
//
//        // VERSION 3
//        // -- What Version 2 does under the hood
//        // Returns a LiveData<RecipesEntity?>
//        //      .observe(viewLifecycleOwner) to get access to RecipesEntity?
//        //      ?.let {} to get access to RecipesEntity
//        val toBeDeletedRecipe = recipeViewModel.queryRecipeById(3)
//        toBeDeletedRecipe.observe(viewLifecycleOwner) {
//            recipe ->
//                recipe?.let {
//                    recipeViewModel.removeSavedRecipe(recipe)
//                }
//        }



        // Hardcoded items for UI

//        val entries = listOf(
////            Meal(1, "Borgor", "b.png"),
////            Meal(2, "Donot", "circle.png"),
////            Meal(3, "SandWitch", "sand.png"),
//
//            RecipesEntity(1, "Borgor", "b.png").toMeal(),
//            RecipesEntity(2, "Donot", "circle.png").toMeal(),
//            RecipesEntity(3, "SandWitch", "sand.png").toMeal()
//        )
//
//        adapter.updateMealList(entries)



        forecastListRV.visibility = View.VISIBLE

    }
}