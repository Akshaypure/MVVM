package com.openapi.mvvm.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openapi.mvvm.data.api.NetworkState
import com.openapi.mvvm.data.model.MovieCritics
import com.openapi.mvvm.data.repository.Repository
import kotlinx.coroutines.launch


class MainViewModel constructor(private val mainRepository: Repository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
    get() = _errorMessage

    val movieList = MutableLiveData<List<MovieCritics>?>()

    fun getCriticsForMovies() {
        Log.d("Thread Outside", Thread.currentThread().name)

        viewModelScope.launch {
            Log.d("Thread Inside", Thread.currentThread().name)
            when (val response = mainRepository.getCriticsFor(movie = "godfather")) {
                is NetworkState.Success -> {
                    movieList.postValue(response.data.movieCriticsList)
                }
                is NetworkState.Error -> {
                    "Error type".onError()
                }
            }
        }
    }

    private fun String.onError() {
        also { _errorMessage.value = it }
    }



}