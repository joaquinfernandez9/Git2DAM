package com.example.mundialjoaquinfernandez.ui.pantallas.listaJugadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mundialjoaquinfernandez.R
import com.example.mundialjoaquinfernandez.databinding.JugadorItemBinding
import com.example.mundialjoaquinfernandez.model.Jugador

class ListaJugadoresAdapter(
) : ListAdapter<Jugador, ListaJugadoresAdapter.ListaJugadoresViewHolder>(DiffCallBack()) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaJugadoresViewHolder {
        return ListaJugadoresViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.jugador_item, parent, false),
        )
    }

    override fun onBindViewHolder(holder: ListaJugadoresViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    inner class ListaJugadoresViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val binding = JugadorItemBinding.bind(view)
        fun bind(jugador: Jugador) = with(binding) {
            tvNombreJugadorItem.text = jugador.nombre
            tvPosicion.text = jugador.posicion
            tvDorsal.text = jugador.dorsal.toString()

        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<Jugador>() {
        override fun areItemsTheSame(oldItem: Jugador, newItem: Jugador): Boolean {
            return oldItem.nombre == newItem.nombre
        }

        override fun areContentsTheSame(oldItem: Jugador, newItem: Jugador): Boolean {
            return oldItem == newItem
        }
    }

}