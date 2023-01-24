package com.example.mundialjoaquinfernandez.ui.pantallas.juego

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mundialjoaquinfernandez.R
import com.example.mundialjoaquinfernandez.databinding.ActivityMainBinding.inflate
import com.example.mundialjoaquinfernandez.databinding.ActivitySplashBinding.inflate
import com.example.mundialjoaquinfernandez.databinding.SeleccionItemBinding
import com.example.mundialjoaquinfernandez.model.Equipo
import com.example.mundialjoaquinfernandez.ui.pantallas.listaEquipos.ListaEquiposAdapter

class SeleccionAdapter() :
    ListAdapter<Equipo, SeleccionAdapter.SeleccionViewHolder>(SeleccionDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeleccionViewHolder {
        return SeleccionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.seleccion_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SeleccionViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class SeleccionViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val binding = SeleccionItemBinding.bind(view)
        fun bind(item: Equipo) = with(binding) {
            tvNombreEquipo.text = item.nombre
        }
    }

    class SeleccionDiffCallback : DiffUtil.ItemCallback<Equipo>() {
        override fun areItemsTheSame(oldItem: Equipo, newItem: Equipo): Boolean {
            return oldItem.nombre == newItem.nombre
        }

        override fun areContentsTheSame(oldItem: Equipo, newItem: Equipo): Boolean {
            return oldItem == newItem
        }

    }
}
