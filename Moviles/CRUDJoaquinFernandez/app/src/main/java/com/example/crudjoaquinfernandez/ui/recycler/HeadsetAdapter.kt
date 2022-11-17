package com.example.crudjoaquinfernandez.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.crudjoaquinfernandez.R
import com.example.crudjoaquinfernandez.databinding.ItemHeadsetBinding
import com.example.crudjoaquinfernandez.domain.model.Headset

class HeadsetAdapter(
    private val actions: Actions
) : ListAdapter<Headset, HeadsetAdapter.HeadsetViewHolder>(DiffCallBack()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadsetViewHolder {
        return HeadsetViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_headset, parent, false), actions
        )
    }

    override fun onBindViewHolder(holder: HeadsetViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    class HeadsetViewHolder(private val headsetView : View, private val actions: Actions) : RecyclerView.ViewHolder(headsetView) {
        private val binding = ItemHeadsetBinding.bind(headsetView)

        fun bind(headset: Headset) = with(binding) {

            tvNombre.text = headset.name
            tvID.text = headset.id.toString()

            button2.setOnClickListener {
                actions.onClickDelete(headset.id)
            }

            detail.setOnClickListener {
                actions.onClickDetail(headset.id)
            }

        }

    }

    class DiffCallBack : DiffUtil.ItemCallback<Headset>() {
        override fun areItemsTheSame(oldItem: Headset, newItem: Headset): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Headset, newItem: Headset): Boolean {
            return oldItem == newItem
        }
    }

}







