package com.example.foodrecipe.ui

import FoodSearchAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipe.R

class MainActivity : ComponentActivity() {
    private val viewModel: FoodSearchViewModel by viewModels()
    private val adapter: FoodSearchAdapter = FoodSearchAdapter()
    private lateinit var forecastListRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastListRV = findViewById<RecyclerView>(R.id.rv_search_results)
        forecastListRV.layoutManager = LinearLayoutManager(this)
        forecastListRV.setHasFixedSize(true)

        forecastListRV.adapter = adapter

        viewModel.searchResults.observe(this) {
                searchResults -> adapter.updateMealList(searchResults)
        }
        forecastListRV.visibility = View.VISIBLE

        viewModel.loadSearchResults("pasta, 7eeb9dec6924484dbd18320c7316ee6c")



    }


    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "Running onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "Running onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "Running onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "Running onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "Running onDestroy()")
    }
}
