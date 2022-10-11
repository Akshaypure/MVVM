package com.openapi.mvvm.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.openapi.mvvm.*
import com.openapi.mvvm.data.api.RetrofitService
import com.openapi.mvvm.data.repository.Repository
import com.openapi.mvvm.databinding.ActivityMainBinding
import com.openapi.mvvm.ui.base.ViewModelFactory
import com.openapi.mvvm.ui.main.adapter.MovieCriticsAdapter
import com.openapi.mvvm.ui.main.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    lateinit var recyclerViewCritics: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = Repository(retrofitService)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(mainRepository)
        )[MainViewModel::class.java]

        recyclerViewCritics = binding.rvMovieCritics

        viewModel.movieList.observe(this) {
            if (it != null) {
                recyclerViewCritics.adapter = MovieCriticsAdapter(it)
                recyclerViewCritics.layoutManager = LinearLayoutManager(this)
                recyclerViewCritics.setHasFixedSize(true)
            }
        }

        viewModel.errorMessage.observe(this) {
            println(it)
        }
        viewModel.getCriticsForMovies()
    }
}