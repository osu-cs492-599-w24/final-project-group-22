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


//        recipeViewModel.savedRecipes.observe(viewLifecycleOwner) {
//            recipes -> adapter.updateMealList(recipes)
//        }

        val entries = listOf(
            Meal(1, "Borgor", "b.png"),
            Meal(2, "Donot", "circle.png"),
            Meal(3, "SandWitch", "sand.png")
        )

        adapter.updateMealList(entries)



        forecastListRV.visibility = View.VISIBLE

    }
}