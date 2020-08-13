package com.azp.newsapp.api

import com.azp.newsapp.model.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiClient {

    private val BASE_URL = "http://newsapi.org/v2/"

    companion object{
        val API_KEY = "e5a7abe04cb44e41843fc49f810f6591"
    }

    private val apiInterface: NewsApiInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface = retrofit.create(
            NewsApiInterface::class.java
        )
    }

    fun getTopHeadlines(
        country: String,
        category: String,
        apiKey: String
    ): Call<News>{
        return apiInterface.getTopHeadlines(
            country, category, apiKey
        )
    }

}