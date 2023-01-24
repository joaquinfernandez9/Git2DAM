package com.example.mundialjoaquinfernandez.ui.pantallas.listaEquipos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mundialjoaquinfernandez.R
import com.example.mundialjoaquinfernandez.databinding.ItemEquipoBinding
import com.example.mundialjoaquinfernandez.model.Equipo

class ListaEquiposAdapter(
    private val actions: Actions
) : ListAdapter<Equipo, ListaEquiposAdapter.ListaEquiposViewHolder>(DiffCallBack()) {

    interface Actions {
        fun onClickDetail(nombre: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaEquiposViewHolder {
        return ListaEquiposViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_equipo, parent, false), actions
        )
    }

    override fun onBindViewHolder(holder: ListaEquiposViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    inner class ListaEquiposViewHolder(view: View, private val actions: Actions) :
        RecyclerView.ViewHolder(view) {
        private val binding = ItemEquipoBinding.bind(view)
        fun bind(equipo: Equipo) = with(binding) {
            tvNombre.text = equipo.nombre
            detail.setOnClickListener {
                actions.onClickDetail(equipo.nombre)
            }
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<Equipo>() {
        override fun areItemsTheSame(oldItem: Equipo, newItem: Equipo): Boolean {
            return oldItem.nombre == newItem.nombre
        }

        override fun areContentsTheSame(oldItem: Equipo, newItem: Equipo): Boolean {
            return oldItem == newItem
        }
    }


}