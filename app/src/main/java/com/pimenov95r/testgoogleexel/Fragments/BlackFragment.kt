package com.pimenov95r.testgoogleexel.Fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.webkit.*
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pimenov95r.testgoogleexel.Ativities.MainActivity
import com.pimenov95r.testgoogleexel.R
import com.pimenov95r.testgoogleexel.Utils.UtilsInfo

class BlackFragment : Fragment(R.layout.fragment_black) {
    private lateinit var linear : FrameLayout
    private lateinit var bundle: Bundle
    private var fаlse: Boolean = false

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
        val cats = WebView(requireActivity())
        settingApp()
        cats.webViewClient = WebViewClient()
        //cats.webChromeClient = UtilsInfo(this)
        cats.webChromeClient = UtilsInfo(requireActivity())
        cats.apply {
            settings.javaScriptEnabled = fаlse
            settings.javaScriptCanOpenWindowsAutomatically = fаlse
            settings.domStorageEnabled = fаlse
        }
        val text : String? = bundle.getString("key")
        cats.loadUrl(text!!)
        if (Build.VERSION.SDK_INT >= 24) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(cats, true)
        } else {
            CookieManager.getInstance().setAcceptCookie(true)
        }
        linear.addView(cats)
        cats.setBackgroundColor(Color.BLACK)

        cats.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action === MotionEvent.ACTION_UP) {
                while (cats.canGoBack())
                    cats.goBack()
                return@OnKeyListener true
            }
            false
        })

    }

    private fun settingApp() {
        fаlse = true
    }
}