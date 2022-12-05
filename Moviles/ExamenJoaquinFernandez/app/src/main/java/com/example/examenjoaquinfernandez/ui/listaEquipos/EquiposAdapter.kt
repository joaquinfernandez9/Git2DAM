package com.example.examenjoaquinfernandez.ui.listaEquipos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.examenjoaquinfernandez.R
import com.example.examenjoaquinfernandez.databinding.ItemEquipoBinding
import com.example.examenjoaquinfernandez.domain.model.Equipo

class EquiposAdapter(
    private val actions: Actions
) : ListAdapter<Equipo, EquiposAdapter.EquiposViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquiposViewHolder {
        return EquiposViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_equipo, parent, false), actions
        )
    }

    override fun onBindViewHolder(holder: EquiposViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    inner class EquiposViewHolder(view: View, private val actions: Actions) :
        RecyclerView.ViewHolder(view) {
        private val binding = ItemEquipoBinding.bind(view)
        fun bind(equipo: Equipo) = with(binding) {
            tvNombre.text = equipo.nombreEquipo
            delete.setOnClickListener {
                actions.onClickDelete(equipo.nombreEquipo)
            }
            detail.setOnClickListener {
                actions.onClickDetail(equipo.nombreEquipo)
            }
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<Equipo>() {
        override fun areItemsTheSame(oldItem: Equipo, newItem: Equipo): Boolean {
            return oldItem.nombreEquipo == newItem.nombreEquipo
        }
        override fun areContentsTheSame(oldItem: Equipo, newItem: Equipo): Boolean {
            return oldItem == newItem
        }
    }


}