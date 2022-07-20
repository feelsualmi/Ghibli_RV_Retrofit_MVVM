package com.example.ghibli_rv_retrofit_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.ghibli_rv_retrofit_mvvm.databinding.ActivityDescriptionBinding

class DescriptionActivity() : AppCompatActivity() {
    lateinit var binding: ActivityDescriptionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieTitle = intent.getStringExtra("Title")
        val movieBanner = intent.getStringExtra("Banner")
        val movieDescription = intent.getStringExtra("Description")
        binding.tvDescription.text = movieDescription
        binding.tvTitle.text = movieTitle

        Glide
            .with(this)
            .load(movieBanner)
            .into(binding.ivBanner)
    }




}