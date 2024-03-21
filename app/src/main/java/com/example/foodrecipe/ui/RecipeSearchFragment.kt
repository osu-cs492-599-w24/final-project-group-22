package com.example.foodrecipe.ui

import FoodSearchAdapter
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipe.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodrecipe.data.FoodRecipe

class RecipeSearchFragment : Fragment(R.layout.fragment_recipe_search) {
    private val viewModel: FoodSearchViewModel by viewModels()

    //    private val adapter = FoodSearchAdapter(::onFoodSearchResultClick)
    private val adapter: FoodSearchAdapter = FoodSearchAdapter()
    private lateinit var foodSearchRV: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchBtn: Button = view.findViewById(R.id.btn_search)
        val searchBoxET: EditText = view.findViewById(R.id.et_search_box)
        foodSearchRV = view.findViewById<RecyclerView>(R.id.rv_search_results)

        foodSearchRV.layoutManager = LinearLayoutManager(requireContext())
        foodSearchRV.setHasFixedSize(true)

        foodSearchRV.adapter = adapter

        viewModel.searchResults.observe(viewLifecycleOwner) {
                searchResults -> adapter.updateMealList(searchResults)
        }
        foodSearchRV.visibility = View.VISIBLE

//        viewModel.loadSearchResults("pasta", "7eeb9dec6924484dbd18320c7316ee6c")

        searchBtn.setOnClickListener {
            val query = searchBoxET.text.toString()
            if (!TextUtils.isEmpty(query)) {
                viewModel.loadSearchResults(query, "e41f5f1e8cb74aa781657879e31d8ea6")
                foodSearchRV.scrollToPosition(0)
            }
        }
    }


    private fun onFoodSearchResultClick(recipe: FoodRecipe) {
        val directions = FoodSearchFragmentDirections.navigateToRecipeDetail()
        findNavController().navigate(directions)
    }

}