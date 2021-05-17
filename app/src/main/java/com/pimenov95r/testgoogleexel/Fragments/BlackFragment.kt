package com.pimenov95r.testgoogleexel.Fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.CookieSyncManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pimenov95r.testgoogleexel.R
import java.net.CookieManager

class BlackFragment : Fragment(R.layout.fragment_black) {
    private lateinit var linear : LinearLayout
    private lateinit var bundle: Bundle
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linear = view.findViewById(R.id.linear)
        bundle = this.requireArguments()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var test : WebView = WebView(requireContext())
        test.webViewClient = WebViewClient()
        test.settings.javaScriptEnabled = true
        test.settings.javaScriptCanOpenWindowsAutomatically = true
        test.settings.domStorageEnabled = true
        val text : String? = bundle.getString("key")
        Log.d("spectra", text!!)
        test.loadUrl(text)
        linear.addView(test)

        test.webViewClient = (object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                CookieSyncManager.getInstance().sync()
                Toast.makeText(requireContext(), "Page loading complete", Toast.LENGTH_LONG).show();
            }
        })
}
}