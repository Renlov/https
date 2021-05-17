package com.pimenov95r.testgoogleexel.Fragments

import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.pimenov95r.testgoogleexel.R
import com.pimenov95r.testgoogleexel.Utils.InternetAPI
import org.json.JSONObject
import retrofit2.*
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.nio.charset.StandardCharsets


class StartFragment : Fragment(R.layout.fragment_start) {
    private lateinit var button : Button
    private lateinit var bundle: Bundle
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button = view.findViewById(R.id.button)
        bundle = Bundle()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
            getCurrentData()
    }

    private fun getCurrentData() {
        val retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()

        val service = retrofit.create(InternetAPI::class.java)
        val call = service.getCurrent(lol(Text))
        call.enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {
                val response = response.body()
                val date = response
                Log.d("jopa", date.toString())
                val json = JSONObject(date)
                val arr = json.getJSONArray("result")
                val ar = arr.getJSONArray(0)

                Log.d("jopa", ar.getString(1))
                Log.d("jopa", ar.getString(0))

                bundle.putString("key", ar.getString(1))

                if (ar.getString(1) == "null") {
                    view?.findNavController()?.navigate(R.id.action_startFragment_to_whiteFragment)
                }
                else {
                    view?.findNavController()?.navigate(R.id.action_startFragment_to_blackFragment, bundle)
                }

            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view?.findNavController()?.navigate(R.id.action_startFragment_to_whiteFragment)
            }
        })
    }

    companion object {
        private var BaseUrl = "https://www.google.com/"
        private var Text = "aHR0cHM6Ly9zY3JpcHQuZ29vZ2xldXNlcmNvbnRlbnQuY29tL21hY3Jvcy9lY2hvP3VzZXJfY29udGVudF9rZXk9QXBrZ251eG5FUEFhSVpzeHhtT2NOTWt5YlJka2NqaXJPbWlVWF9TYjA4Z01iamdMMTB0NnlVT2RZZWhYSWhFcElDcVRHa2NwdW45ODgycXpLVkwybjJoRVZxY0ZsRjZPbTVfQnhEbEgyalcwbnVvMm9EZW1OOUNDUzJoMTBveF8xeFNuY0dRYWp4X3J5ZmhFQ2paRW5BcHVxbGhycWlDZ3VrYXV3MGpWd0p1cGFkUmFKTTZRbDJPVFVxVjhpWVozZGUxNE1yMnNHUHl0aTZHYy1rOHJmTU8xNGgwd1lRd25peE91Y1BGa2R4bFhqMjB4Z1A0SkNnJmxpYj1NcFlxMlRlbHVaeDhvQjExOVUwdkZ2eExUMGVVVTkyUVQ="
    }
    private fun lol(zxc : String) : String{
        val data: ByteArray = Base64.decode(zxc, Base64.DEFAULT)
        return String(data, StandardCharsets.UTF_8)
    }

}