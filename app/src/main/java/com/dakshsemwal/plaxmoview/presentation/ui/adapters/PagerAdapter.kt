package com.dakshsemwal.plaxmoview.presentation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dakshsemwal.plaxmoview.R
import com.dakshsemwal.plaxmoview.databinding.ItemCardBinding
import com.dakshsemwal.plaxmoview.domain.model.Movie

class PagerAdapter(context: Context, data: List<Movie>) :
    ListAdapter<Movie, PagerAdapter.ViewHolder>(MovieDC()) {
    private val mData: List<Movie> = data
    private var mItemClickListener: AdapterView.OnItemClickListener? = null
    fun SetOnItemClickListener(mItemClickListener: AdapterView.OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, itemPosition: Int, model: Movie?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerAdapter.ViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder internal constructor(private val itemCardBinding: ItemCardBinding) :
        RecyclerView.ViewHolder(itemCardBinding.root) {
        fun bind(movie: Movie, position: Int) {
            if (movie.backdrop_path != null)
                Glide.with(itemCardBinding.root.context).load(movie.backdrop_url)
                    .into(itemCardBinding.imgBanner)
            else
                Glide.with(itemCardBinding.root.context)
                    .load(R.mipmap.ic_launcher)
                    .into(itemCardBinding.imgBanner)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = mData[position]
        holder.bind(movie, position)
    }

}