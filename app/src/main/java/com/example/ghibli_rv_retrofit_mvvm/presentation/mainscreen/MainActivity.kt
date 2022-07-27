package com.example.ghibli_rv_retrofit_mvvm.presentation.mainscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ghibli_rv_retrofit_mvvm.data.MovieApi
import com.example.ghibli_rv_retrofit_mvvm.data.MovieRepository
import com.example.ghibli_rv_retrofit_mvvm.databinding.ActivityMainBinding
import com.example.ghibli_rv_retrofit_mvvm.presentation.description.DescriptionDialogFragment
import com.example.ghibli_rv_retrofit_mvvm.presentation.description.DescriptionActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: MainAdapter


    private val viewModel: MovieViewModel by viewModels {
        MyViewModelFactory(MovieRepository(MovieApi.create()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovie.layoutManager = GridLayoutManager(this, 3)

        adapter = MainAdapter({ onClickImage ->
            val intent = Intent(applicationContext, DescriptionActivity::class.java)
            intent.putExtra("Title", onClickImage.title)
            intent.putExtra("Banner", onClickImage.movie_banner)
            intent.putExtra("Description", onClickImage.description)
            startActivity(intent)
        }) { onLongClickImage ->
            DescriptionDialogFragment.newInstance(
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