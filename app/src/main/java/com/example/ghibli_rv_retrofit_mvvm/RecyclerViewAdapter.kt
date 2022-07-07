package com.example.ghibli_rv_retrofit_mvvm

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdapter(
    private val shortListener: (Movie) -> Unit,
    //private val longListener: (Movie) -> Boolean,
) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var movies: List<Movie> = emptyList()

    fun fillMovies(list: List<Movie>){
        this.movies = list
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val movieTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val movieImage: ImageView = itemView.findViewById(R.id.ivTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val shortClickOnImage = movies[position]
        //val longClickOnImage = movies[position]

        holder.movieTitle.text = movies[position].title
        Glide
            .with(holder.itemView.context)
            .load(movies[position].image)
            .into(holder.movieImage)

        holder.movieImage.setOnClickListener{shortListener(shortClickOnImage)}
        //holder.movieImage.setOnLongClickListener{longListener(longClickOnImage)}

    }

    override fun getItemCount(): Int {
        return movies.size
    }
}