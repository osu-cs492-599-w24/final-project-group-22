package com.example.foodrecipe.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.foodrecipe.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.lang.Exception
import java.net.URL
import android.webkit.WebView
import com.example.foodrecipe.data.Meal


class FoodViewFragment : Fragment(R.layout.fragment_recipe_detail) {
    private val viewModel: RecipeSearchViewModel by viewModels()

    private var recipeID: Int = 122

    //A LOT of fields for the view
    private lateinit var recipeTitle: TextView
    private lateinit var recipeImage: ImageView
    private lateinit var recipeDescription: WebView
    private lateinit var recipeInstructions: WebView



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedMeal: Meal? = arguments?.getParcelable("selectedMeal")
        if (selectedMeal != null) {
            recipeID = selectedMeal.id
            Log.d("FoodViewFragment", "Received meal:\nTitle: ${selectedMeal.title}\nID: ${selectedMeal.id}")
        } else {
            Log.d("FoodViewFragment", "No meal selected!")
        }

        //Assigning the fields to the components of the xml file
        recipeTitle = view.findViewById(R.id.tv_recipe_name)
        recipeImage = view.findViewById(R.id.iv_recipe_image)
        recipeDescription = view.findViewById(R.id.tv_recipe_description)
        recipeInstructions = view.findViewById(R.id.tv_instructions)

        viewModel.loadRecipeSearchResults(recipeID.toString(), getString(R.string.spoon_api_key))

        viewModel.searchResults.observe(viewLifecycleOwner) {
            //Upon receiving the data from the API, loads it into the page
            searchResults ->
            recipeTitle.text = searchResults?.title
            loadImageFromInternet(searchResults?.image?: "")
            recipeDescription.loadData(searchResults?.summary?: "", "text/html", "UTF-8")
            recipeInstructions.loadData(searchResults?.instructions?: "", "text/html", "UTF-8")
        }

    }

    private fun loadImageFromInternet(imageUrl: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val bitmap = withContext(Dispatchers.IO) {
                    downloadImage(imageUrl)
                }
                recipeImage.setImageBitmap(bitmap)
            } catch (e: Exception) {
                Log.e("Error Message", e.message.toString())
                e.printStackTrace()
            }
        }
    }

    private suspend fun downloadImage(imageUrl: String): Bitmap {
        var inputStream: InputStream? = null
        try {
            inputStream = URL(imageUrl).openStream()
            return BitmapFactory.decodeStream(inputStream)
        } finally {
            inputStream?.close()
        }
    }

}