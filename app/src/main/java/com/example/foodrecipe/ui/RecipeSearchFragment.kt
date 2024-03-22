package com.example.foodrecipe.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.foodrecipe.R
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs


class RecipeSearchFragment : Fragment(R.layout.fragment_recipe_detail) {
    private val args: RecipeSearchFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imgTV: TextView = view.findViewById(R.id.iv_recipe_image)
        val nameTV: TextView = view.findViewById(R.id.tv_recipe_name)
        val descriptionTV: TextView = view.findViewById(R.id.tv_recipe_description)
        val originalTV: TextView = view.findViewById(R.id.tv_recipe_original)
        val consistencyTV: TextView = view.findViewById(R.id.tv_recipe_consistency)
        val aisleTV: TextView = view.findViewById(R.id.tv_recipe_aisle)


        nameTV.text = args.recipe.name
        descriptionTV.text = args.recipe.description
        originalTV.text = args.recipe.original
        consistencyTV.text = args.recipe.consistency
        aisleTV.text = args.recipe.aisle





    }

}