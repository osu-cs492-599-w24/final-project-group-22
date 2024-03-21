package com.example.foodrecipe.ui

import FoodSearchAdapter
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
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
import com.example.foodrecipe.data.Meal

class FoodSearchFragment : Fragment(R.layout.fragment_recipe_search) {
    private val viewModel: FoodSearchViewModel by viewModels()

//    private val adapter = FoodSearchAdapter(::onFoodSearchResultClick)
    private val adapter: FoodSearchAdapter = FoodSearchAdapter()
    private lateinit var forecastListRV: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchBtn: Button = view.findViewById(R.id.btn_search)
        val searchBoxET: EditText = view.findViewById(R.id.et_search_box)
        forecastListRV = view.findViewById<RecyclerView>(R.id.rv_search_results)

        forecastListRV.layoutManager = LinearLayoutManager(requireContext())
        forecastListRV.setHasFixedSize(true)

        forecastListRV.adapter = adapter

        adapter.setOnItemClickListener(object: FoodSearchAdapter.onItemClickListener{
            override fun onItemClick(position: Int, activeMeal: Meal) {


                Log.d("FoodSearchFragment", "Clicked on item $position, contains meal:\nID: ${activeMeal.id}\nName: ${activeMeal.title}")



            }

        })

        viewModel.searchResults.observe(viewLifecycleOwner) {
                searchResults -> adapter.updateMealList(searchResults)
        }
        forecastListRV.visibility = View.VISIBLE

//        viewModel.loadSearchResults("pasta", "7eeb9dec6924484dbd18320c7316ee6c")

        searchBtn.setOnClickListener {
            val query = searchBoxET.text.toString()
            if (!TextUtils.isEmpty(query)) {
                viewModel.loadSearchResults(query, "e41f5f1e8cb74aa781657879e31d8ea6")
                forecastListRV.scrollToPosition(0)
            }
        }
    }


//    private fun onFoodSearchResultClick(recipe: FoodRecipe) {
//        val directions = FoodSearchFragmentDirections.navigateToRecipeDetail()
//        findNavController().navigate(directions)
//    }

}