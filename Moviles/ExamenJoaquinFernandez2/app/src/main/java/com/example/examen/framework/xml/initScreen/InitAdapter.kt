package com.example.examen.framework.xml.initScreen


import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.examen.R
import com.example.examen.databinding.HospitalItemBinding
import com.example.examen.domain.model.Hospital

class InitAdapter : ListAdapter<Hospital, InitAdapter.HospitalViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        return HospitalViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.hospital_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int ) = with(holder) {
        val item = getItem(position)
        bind(item)
//        val binding = HospitalItemBinding.bind(itemView)
//
//        binding.textTitulo.setOnClickListener {
//            notifyItemChanged(adapterPosition)
//        }
    }

    inner class HospitalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = HospitalItemBinding.bind(itemView)
        fun bind(item: Hospital){
            binding.nombre.text = item.nombre
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Hospital>(){
        override fun areItemsTheSame(oldItem: Hospital, newItem: Hospital): Boolean {
            return oldItem.nombre == newItem.nombre
        }
        override fun areContentsTheSame(oldItem: Hospital, newItem: Hospital): Boolean {
            return oldItem == newItem
        }
    }


}