package com.example.flowsjoaquinfernandez.framework.movies

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.flows.R
import com.example.flows.databinding.RvMovieItemBinding
import coil.load
import com.example.flowsjoaquinfernandez.domain.modelo.Movie

class MovieAdapter : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
        val binding = RvMovieItemBinding.bind(itemView)
        val visible : Boolean = binding.expand.isVisible
        binding.expand.visibility = if (visible) View.GONE else View.VISIBLE
        binding.description.text = item.overview
        binding.imagen1.load(item.poster_path)

        binding.textTitulo.setOnClickListener {
            notifyItemChanged(adapterPosition)
            if (binding.description.maxLines == 3) {
                binding.description.maxLines = Integer.MAX_VALUE
                binding.description.movementMethod = ScrollingMovementMethod()
            } else {
                binding.description.maxLines = 3
            }

        }

    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RvMovieItemBinding.bind(itemView)
        fun bind(item: Movie) {
            binding.textTitulo.text = item.titulo


        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.titulo == newItem.titulo
        }
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}