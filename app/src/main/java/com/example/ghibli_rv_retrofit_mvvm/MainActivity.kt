package com.example.ghibli_rv_retrofit_mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback

import androidx.activity.viewModels
import androidx.core.net.toUri
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

        adapter = RecyclerViewAdapter({ onClickImage ->
            val intent = Intent(applicationContext, DescriptionActivity::class.java)
            intent.putExtra("Title", onClickImage.title)
            intent.putExtra("Banner", onClickImage.movie_banner)
            intent.putExtra("Description", onClickImage.description)
            startActivity(intent)
        }) { onLongClickImage ->
            BottomDialogFragment.newInstance(
                "${onLongClickImage.movie_banner}",
                "${onLongClickImage.title}",
                "${onLongClickImage.description}"
            ).show(supportFragmentManager, "tag")
        }
        binding.rvMovie.adapter = adapter
        viewModel.getAllMovies()
        viewModel.movieList.observe(this) {
            adapter.fillMovies(it)
        }

    }
}