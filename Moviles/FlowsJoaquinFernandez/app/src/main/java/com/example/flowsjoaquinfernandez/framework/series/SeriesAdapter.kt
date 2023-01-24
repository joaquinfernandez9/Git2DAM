package com.example.flowsjoaquinfernandez.framework.series

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.flows.R
import com.example.flows.databinding.RvSeriesItemBinding
import com.example.flowsjoaquinfernandez.domain.modelo.Series

class SeriesAdapter: ListAdapter<Series, SeriesAdapter.SeriesViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        return SeriesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_series_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SeriesAdapter.SeriesViewHolder, position: Int) =
        with(holder) {
            val item = getItem(position)
            bind(item)
        }

    inner class SeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RvSeriesItemBinding.bind(itemView)
        fun bind(item: Series) {
            with(binding){
                textTitulo.text = item.name
            }

        }

    }

    class DiffCallback : DiffUtil.ItemCallback<Series>() {
        override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem.name == newItem.name
        }
        override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem == newItem
        }
    }


}