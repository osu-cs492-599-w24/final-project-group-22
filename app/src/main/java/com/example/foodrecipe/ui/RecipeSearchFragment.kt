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
import com.google.android.material.snackbar.Snackbar

class RecipeSearchFragment : Fragment(R.layout.fragment_recipe_detail) {
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

        adapter.setOnItemClickListener(object: FoodSearchAdapter.onItemClickListener{
            override fun onItemClick(position: Int, activeMeal: Meal) {

                Log.d("RecipeSearchFragment", "Clicked on item $position, contains meal:\nID: ${activeMeal.id}\nName: ${activeMeal.title}")
                val bundle = Bundle()
                bundle.putParcelable("selectedMeal", activeMeal)

                findNavController().navigate(
                    R.id.recipe_detail_fragment,
                    bundle
                )

            }

        })

        viewModel.searchResults.observe(viewLifecycleOwner) {
                searchResults -> adapter.updateMealList(searchResults)
        }
        foodSearchRV.visibility = View.VISIBLE


        // Set up on long click listener
        // Video: Setting up a click listener for recycler view
        // https://www.youtube.com/watch?v=WqrpcWXBz14
        adapter.onItemLongClick = {
            val snackbar = Snackbar.make(
                view,
                it.title,
                Snackbar.LENGTH_LONG
            )
            snackbar.show()
        }

        searchBtn.setOnClickListener {
            val query = searchBoxET.text.toString()
            if (!TextUtils.isEmpty(query)) {
                viewModel.loadSearchResults(query, getString(R.string.spoon_api_key))
                foodSearchRV.scrollToPosition(0)
            }
        }
    }

}