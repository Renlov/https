package com.pimenov95r.testgoogleexel.Utils

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.pimenov95r.testgoogleexel.Ativities.MainActivity.Companion.FILECHOOSER_RESULTCODE
import com.pimenov95r.testgoogleexel.Ativities.MainActivity.Companion.REQUEST_SELECT_FILE
import com.pimenov95r.testgoogleexel.Ativities.MainActivity.Companion.message
import com.pimenov95r.testgoogleexel.Ativities.MainActivity.Companion.messageArray

//или активити, в котором будет сам фрагмент
class UtilsInfo(var activity: FragmentActivity) : WebChromeClient() {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onShowFileChooser(view: android.webkit.WebView, filePath: ValueCallback<Array<Uri>>, fileChooserParams: WebChromeClient.FileChooserParams): Boolean {
        if (message != null) {
            message!!.onReceiveValue(null)
            message = null
        }
        messageArray = filePath
        val intent = fileChooserParams.createIntent()
        intent.type = "image/*"
        try {
            activity.startActivityForResult(
                    intent,
                    REQUEST_SELECT_FILE
            )
        } catch (e: Exception) {
            message = null
            return false
        }
        return true
    }

    fun openFileChooser(uploadMsg: ValueCallback<Uri>, acceptType: String = "") {
        Log.d("spectra" ,"openFileChooser")
        message = uploadMsg
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        activity.startActivityForResult(
                Intent.createChooser(intent, ""),
                FILECHOOSER_RESULTCODE
        )
    }
}