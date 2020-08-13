package com.azp.newsapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azp.newsapp.api.NewsApiClient
import com.azp.newsapp.model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewmodel: ViewModel() {

    private var result: MutableLiveData<News> = MutableLiveData()

    fun getResult(): LiveData<News> = result

    fun loadNews() {
        var apiClient = NewsApiClient()
        val call = apiClient.getTopHeadlines(
            "us", "Technology", NewsApiClient.API_KEY
        )
        call.enqueue(object: Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Error>>>>",t.toString())
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                result.value = response.body()
            }

        })
    }
}