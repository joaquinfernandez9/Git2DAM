package com.example.crudjoaquinfernandez.ui.storeList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.crudjoaquinfernandez.R
import com.example.crudjoaquinfernandez.databinding.ItemStoreBinding
import com.example.crudjoaquinfernandez.domain.model.Store

class StoresAdapter(
    private val actions: Actions
) : ListAdapter<Store, StoresAdapter.StoresViewHolder>(DiffCallBack()) {

    override fun onBindViewHolder(holder: StoresViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoresViewHolder {
        return StoresViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_store, parent, false), actions
        )
    }

    inner class StoresViewHolder(view: View, private val actions: Actions) :
        RecyclerView.ViewHolder(view) {
        private val binding = ItemStoreBinding.bind(view)

        fun bind(store: Store) = with(binding) {
            tvStoreName.text = store.name
            delete.setOnClickListener {
                actions.onClickDelete(store.name)
            }
            detail.setOnClickListener {
                actions.onClickDetail(store.name)
            }
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<Store>() {
        override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
            return oldItem == newItem
        }
    }

}