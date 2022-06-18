package com.example.ghibli_rv_retrofit_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ghibli_rv_retrofit_mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: RecyclerViewAdapter

    private val viewModel:MovieViewModel by viewModels{
        MyViewModelFactory(MovieRepository(MovieApi.create()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView : RecyclerView = binding.rvMovie
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        adapter = RecyclerViewAdapter()
        recyclerView.adapter = adapter

        viewModel.getAllMovies()
        viewModel.movieList.observe(this){
            adapter.fillMovies(it)
        }

    }
}