package com.example.foodrecipe.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.foodrecipe.R

class CalorieFragment : Fragment(R.layout.calorie_analysis_fragment) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )
            : View? {
        val rootView: View = inflater.inflate(R.layout.calorie_analysis_fragment, container, false)

        val url = "https://happyforks.com/analyzer"
        val view = rootView.findViewById<View>(R.id.idWebView) as WebView
        view.settings.javaScriptEnabled = true
        view.loadUrl(url)
        return rootView
    }
}