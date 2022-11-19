package com.example.crudjoaquinfernandez.ui.mainScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.crudjoaquinfernandez.R
import com.example.crudjoaquinfernandez.databinding.ItemModelBinding
import com.example.crudjoaquinfernandez.domain.model.Model

class ModelAdapter(
    private val actions: Actions
) : ListAdapter<Model, ModelAdapter.ModelViewHolder>(DiffCallBack()) {

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        return ModelViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_model,
                parent,
                false
            ), actions
        )
    }

    inner class ModelViewHolder(modelView: View, private val actions: Actions) :
        RecyclerView.ViewHolder(modelView) {
        private val binding = ItemModelBinding.bind(modelView)
        fun bind(model: Model) = with(binding) {
            tvNombre.text = model.modelName
            bntDelete.setOnClickListener {
                model.idModel.let { it1 -> actions.onClickDelete(it1) }
            }
        }

    }

    class DiffCallBack : DiffUtil.ItemCallback<Model>(){
        override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem.idModel == newItem.idModel
        }

        override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem == newItem
        }

    }


}