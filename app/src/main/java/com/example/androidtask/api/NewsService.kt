package com.example.androidtask.api


import com.example.androidtask.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "72384e79ecef4a9982b94f64911f1f73"

interface NewsService {
    @GET("/v2/top-headlines/")
    suspend fun getTop(
        @Query("country") countryCode: String,
        @Query("page") page: Int,
        @Query("apikey") apikey: String = API_KEY
    ):Response<NewsResponse>
}