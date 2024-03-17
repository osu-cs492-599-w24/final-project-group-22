package com.example.foodrecipe.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodrecipe.ui.R
import com.example.foodrecipe.data.Recipe

class RecipeListAdapter() : RecyclerView.Adapter<RecipeListAdapter.RecipeViewModel>() {
    var recipeList = listOf(Recipe("Title", "Description"), Recipe("Title", "Description"), Recipe("Title", "Description"), Recipe("Title", "Description"),Recipe("Title", "Description"), Recipe("Title", "Description"))

    override fun getItemCount() = recipeList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewModel {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_list_item, parent, false)
        return RecipeViewModel(view)
    }

    override fun onBindViewHolder(holder: RecipeViewModel, position: Int) {
        holder.bind(recipeList[position])
    }


    class RecipeViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTV: TextView = itemView.findViewById(R.id.tv_recipe_title)
        private val descTV: TextView = itemView.findViewById(R.id.tv_recipe_desc)
        private val imageIV: ImageView = itemView.findViewById(R.id.iv_recipe)

        private lateinit var currentRecipe: Recipe

        fun bind(recipe: Recipe) {
            currentRecipe = recipe

            val ctx = itemView.context
            val sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(ctx)

            titleTV.text = "Title"
            descTV.text = "Description"
            /*
             * Load forecast icon into ImageView using Glide: https://bumptech.github.io/glide/
             */
            Glide.with(ctx)
                .load("https://placekitten.com/200/300")
                .into(imageIV)
        }
    }
}