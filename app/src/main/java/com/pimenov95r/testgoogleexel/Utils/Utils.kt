package com.pimenov95r.testgoogleexel.Utils

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import androidx.annotation.RequiresApi
import com.pimenov95r.testgoogleexel.Ativities.MainActivity
import com.pimenov95r.testgoogleexel.Ativities.MainActivity.Companion.FILECHOOSER_RESULTCODE
import com.pimenov95r.testgoogleexel.Ativities.MainActivity.Companion.REQUEST_SELECT_FILE
import com.pimenov95r.testgoogleexel.Ativities.MainActivity.Companion.message
import com.pimenov95r.testgoogleexel.Ativities.MainActivity.Companion.messageArray

class Utils : WebChromeClient() {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onShowFileChooser(
        view: android.webkit.WebView,
        filePath: ValueCallback<Array<Uri>>,
        fileChooserParams: WebChromeClient.FileChooserParams
    ): Boolean {
        if (message != null) {
            message!!.onReceiveValue(null)
            message = null
        }
        messageArray = filePath
        val intent = fileChooserParams.createIntent()
        intent.type = "image/*"
        try {
            MainActivity().startActivityForResult(
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
        message = uploadMsg
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        MainActivity().startActivityForResult(
            Intent.createChooser(intent, ""),
            FILECHOOSER_RESULTCODE
        )
    }
}