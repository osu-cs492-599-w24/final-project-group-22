package com.example.foodrecipe.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodrecipe.ui.R

class RecipeListDisplayFragment : Fragment(R.layout.recipe_list_display_fragment) {
    private val recipeListAdapter = RecipeListAdapter()
    private lateinit var recipeListRV: RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeListRV = view.findViewById(R.id.rv_recipe_list)
        recipeListRV.layoutManager = GridLayoutManager(requireContext(), 2)
        recipeListRV.setHasFixedSize(true)
        recipeListRV.adapter = recipeListAdapter

    }
    override fun onStart() {
        super.onStart()
    }
}
