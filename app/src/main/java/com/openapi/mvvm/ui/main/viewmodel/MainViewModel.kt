package com.openapi.mvvm.ui.main.viewmodel

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
        viewModelScope.launch {
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