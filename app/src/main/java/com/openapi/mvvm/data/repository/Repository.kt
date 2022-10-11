package com.openapi.mvvm.data.repository

import androidx.lifecycle.MutableLiveData
import com.openapi.mvvm.BuildConfig
import com.openapi.mvvm.data.api.RetrofitService
import com.openapi.mvvm.data.api.NetworkState
import com.openapi.mvvm.data.model.MovieCritics
import com.openapi.mvvm.data.model.ResponseWrapper

class Repository constructor(private val retrofitService: RetrofitService) {

    private val mutableLiveData: MutableLiveData<List<MovieCritics>> = MutableLiveData<List<MovieCritics>>()

    suspend fun getCriticsFor(movie: String) : NetworkState<ResponseWrapper> {
        val response = retrofitService.getCritics(
            query = movie, apiKey = BuildConfig.API_KEY
        )

        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                mutableLiveData.value = response.body()!!.movieCriticsList
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }

}
