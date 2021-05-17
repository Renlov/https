package com.pimenov95r.testgoogleexel.Utils

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface InternetAPI {
    @GET
    fun getCurrent(@Url zxc : String): Call<String>
}