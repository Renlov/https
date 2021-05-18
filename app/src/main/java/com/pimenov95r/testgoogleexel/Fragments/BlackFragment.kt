package com.pimenov95r.testgoogleexel.Fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.pimenov95r.testgoogleexel.R
import com.pimenov95r.testgoogleexel.Utils.Utils

class BlackFragment : Fragment(R.layout.fragment_black) {
    private lateinit var linear : FrameLayout
    private lateinit var bundle: Bundle
    private var falseCommand : Boolean = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linear = view.findViewById(R.id.linear)
        bundle = this.requireArguments()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
        super.onActivityCreated(savedInstanceState)
        val cats = WebView(requireContext())
        settingApp()
        cats.apply {
            settings.javaScriptEnabled = falseCommand
            settings.javaScriptCanOpenWindowsAutomatically = falseCommand
            settings.domStorageEnabled = falseCommand
        }
        cats.webViewClient = WebViewClient()
        cats.webChromeClient = Utils()
        val text : String? = bundle.getString("key")
        cats.loadUrl(text!!)
        linear.addView(cats)
        cats.setBackgroundColor(Color.BLACK)




        if (Build.VERSION.SDK_INT >= 23) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(cats, true)
        } else {
            CookieManager.getInstance().setAcceptCookie(true)
        }
    }

    private fun settingApp() {
        falseCommand = true
    }


}