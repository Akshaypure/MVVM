package com.openapi.mvvm.data.api

import com.openapi.mvvm.data.model.ResponseWrapper
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("reviews/search.json")
    suspend fun getCritics(@Query("query") query: String,
                           @Query("api-key") apiKey: String) : Response<ResponseWrapper>

    companion object {
        private var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.nytimes.com/svc/movies/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}
