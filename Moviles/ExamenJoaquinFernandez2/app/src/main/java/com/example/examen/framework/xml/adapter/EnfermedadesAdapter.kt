package com.example.examen.framework.xml.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.examen.R
import com.example.examen.databinding.EnfermedadItemBinding
import com.example.examen.domain.model.Enfermedades
import com.example.examen.domain.model.Paciente

class EnfermedadesAdapter: ListAdapter<Enfermedades, EnfermedadesAdapter.EnfermedadViewHolder> (DiffCallback()){

    class DiffCallback : DiffUtil.ItemCallback<Enfermedades>() {
        override fun areItemsTheSame(oldItem: Enfermedades, newItem: Enfermedades): Boolean {
            return oldItem.nombre == newItem.nombre
        }

        override fun areContentsTheSame(oldItem: Enfermedades, newItem: Enfermedades): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnfermedadesAdapter.EnfermedadViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EnfermedadViewHolder(layoutInflater.inflate(R.layout.enfermedad_item, parent, false))
    }

    override fun onBindViewHolder(holder: EnfermedadesAdapter.EnfermedadViewHolder, position: Int) {
        val patient = getItem(position)
        holder.bind(patient)
    }


    override fun getItemCount (): Int = currentList.size

    inner class EnfermedadViewHolder(view: View): ViewHolder(view){
        private val binding = EnfermedadItemBinding.bind(view)
        fun bind(enfermedades: Enfermedades){
            with(binding){
                nombre.text = enfermedades.nombre
            }
        }
    }

}