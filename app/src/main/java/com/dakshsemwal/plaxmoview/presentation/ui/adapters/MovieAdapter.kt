package com.dakshsemwal.plaxmoview.presentation.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dakshsemwal.plaxmoview.R
import com.dakshsemwal.plaxmoview.databinding.ItemMovieBinding
import com.dakshsemwal.plaxmoview.domain.model.Movie

class MovieAdapter(
    private val movies: ArrayList<Movie>
) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDC()) {

    private var mItemClickListener: ListItemClickListener? = null

    interface ListItemClickListener {
        fun onItemClick(listItem: Movie, position: Int)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    fun SetOnItemClickListener(mItemClickListener: ListItemClickListener) {
        this.mItemClickListener = mItemClickListener
    }

    inner class MovieViewHolder(private val itemMovieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {

        fun bind(movie: Movie, position: Int) {
            with(itemMovieBinding) {
                if (movie.poster_path != null)
                    Glide.with(itemMovieBinding.root.context)
                        .load(movie.poster_url)
                        .into(image)
                else
                    Glide.with(itemMovieBinding.root.context)
                        .load(R.mipmap.ic_launcher)
                        .into(image)
                itemMovieBinding.root.setOnClickListener {
                    mItemClickListener?.onItemClick(movie, position)
                }
            }
        }
    }

    override fun getItemCount(): Int = movies.size

}