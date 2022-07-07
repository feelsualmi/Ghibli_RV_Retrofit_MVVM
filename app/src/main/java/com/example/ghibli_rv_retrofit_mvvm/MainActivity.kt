package com.example.ghibli_rv_retrofit_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback

import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ghibli_rv_retrofit_mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: RecyclerViewAdapter

    private val viewModel: MovieViewModel by viewModels {
        MyViewModelFactory(MovieRepository(MovieApi.create()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovie.layoutManager = GridLayoutManager(this, 3)

        adapter = RecyclerViewAdapter { onClickImage ->
            BottomDialogFragment.newInstance(
                "${onClickImage.movie_banner}",
                "${onClickImage.title}",
                "${onClickImage.description}"
            ).show(supportFragmentManager, "tag")
        }
        binding.rvMovie.adapter = adapter

        viewModel.getAllMovies()
        viewModel.movieList.observe(this) {
            adapter.fillMovies(it)
        }

        // val name = intent.getStringExtra(adapter.movies[0].title)
        //val fragment = BottomDialogFragment.newInstance(name!!)

    }
}