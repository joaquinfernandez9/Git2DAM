package com.example.examendiputadosjoaquinfernandez.framework.xml.crud

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.examendiputadosjoaquinfernandez.R
import com.example.examendiputadosjoaquinfernandez.databinding.PartidoItemBinding
import com.example.examendiputadosjoaquinfernandez.domain.model.Partido

class PartidosAdapter(
    private val actions: Actions,
): ListAdapter<Partido, PartidosAdapter.PartidoViewHolder>(DiffCallback()) {

    interface Actions {
        fun onClickDelete(nombre: String)
        fun onClickUpdate(nombre: String)
    }
    class DiffCallback : DiffUtil.ItemCallback<Partido>() {
        override fun areItemsTheSame(oldItem: Partido, newItem: Partido): Boolean {
            return oldItem.nombre == newItem.nombre
        }
        override fun areContentsTheSame(
            oldItem: Partido,
            newItem: Partido
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PartidoViewHolder(layoutInflater.inflate(R.layout.partido_item, parent, false))
    }

    override fun onBindViewHolder(holder: PartidoViewHolder, position: Int ) {
        val hospital = getItem(position)
        holder.bind(hospital)
    }

    override fun getItemCount(): Int = currentList.size

    inner class PartidoViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = PartidoItemBinding.bind(view)
        fun bind(partido: Partido){
            with(binding) {
                nombre.text = partido.nombre
                borrarBtn.setOnClickListener {
                    actions.onClickDelete(partido.id.toString())
                }
                updateNameBtn.setOnClickListener {
                    actions.onClickUpdate(partido.id.toString())
                }
            }
        }
    }

}