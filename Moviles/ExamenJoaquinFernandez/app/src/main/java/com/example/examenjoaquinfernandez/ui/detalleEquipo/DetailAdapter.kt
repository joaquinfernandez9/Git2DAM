package com.example.examenjoaquinfernandez.ui.detalleEquipo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.examenjoaquinfernandez.R
import com.example.examenjoaquinfernandez.databinding.ItemComponenteBinding
import com.example.examenjoaquinfernandez.domain.model.Componente

class DetailAdapter (
        )
    : ListAdapter<Componente, DetailAdapter.ComponenteViewHolder>(DiffCallBack()){

    override fun onBindViewHolder(holder: ComponenteViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponenteViewHolder {
        return ComponenteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_componente,
                parent,
                false
            ),
        )
    }

    inner class ComponenteViewHolder(view: View,) :
        RecyclerView.ViewHolder(view) {
        private val binding = ItemComponenteBinding.bind(view)
        fun bind(comp : Componente) = with(binding) {
            tvNombre.text = comp.nombre

            if (comp.tipo.lowercase() ==  "jugador"){
                entrenador.setImageResource(R.drawable.ic_detail)
            } else {
                entrenador.setImageResource(R.drawable.outline_delete_24)
            }
        }

    }

    class DiffCallBack : DiffUtil.ItemCallback<Componente>(){
        override fun areItemsTheSame(oldItem: Componente, newItem: Componente): Boolean {
            return oldItem.nombre == newItem.nombre
        }

        override fun areContentsTheSame(oldItem: Componente, newItem: Componente): Boolean {
            return oldItem == newItem
        }

    }

}