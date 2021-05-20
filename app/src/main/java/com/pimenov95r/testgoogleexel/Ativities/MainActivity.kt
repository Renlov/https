package com.pimenov95r.testgoogleexel.Ativities

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import com.pimenov95r.testgoogleexel.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    companion object {
        var message: ValueCallback<Uri>? = null
        var messageArray: ValueCallback<Array<Uri>>? = null
        val REQUEST_SELECT_FILE = 921
        val FILECHOOSER_RESULTCODE = 72
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode == REQUEST_SELECT_FILE) {
                if (messageArray == null)
                    return
                messageArray!!.onReceiveValue(
                        WebChromeClient.FileChooserParams.parseResult(
                                resultCode,
                                data
                        )
                )
                messageArray = null
            }
        } else if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == message)
                return
            val result =
                    if (data == null || resultCode != AppCompatActivity.RESULT_OK) null else data.data
            message!!.onReceiveValue(result)
            message = null
        } else {
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
