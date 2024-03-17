package com.example.foodrecipe.ui

import FoodSearchAdapter
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipe.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class FoodSearchFragment : Fragment(R.layout.fragment_recipe_search) {
    private val viewModel: FoodSearchViewModel by viewModels()
    private val adapter: FoodSearchAdapter = FoodSearchAdapter()
    private lateinit var forecastListRV: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        forecastListRV = view.findViewById<RecyclerView>(R.id.rv_search_results)
        forecastListRV.layoutManager = LinearLayoutManager(requireContext())
        forecastListRV.setHasFixedSize(true)

        forecastListRV.adapter = adapter

        viewModel.searchResults.observe(viewLifecycleOwner) {
                searchResults -> adapter.updateMealList(searchResults)
        }
        forecastListRV.visibility = View.VISIBLE

        viewModel.loadSearchResults("pasta", "7eeb9dec6924484dbd18320c7316ee6c")



    }
}