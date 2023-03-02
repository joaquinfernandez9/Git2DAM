package com.example.examen.framework.xml.initScreen


import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.examen.R
import com.example.examen.databinding.HospitalItemBinding
import com.example.examen.domain.model.Hospital

class InitAdapter(
    val actions: HospitalActions
) : ListAdapter<Hospital, InitAdapter.HospitalViewHolder>(DiffCallback()) {
    /*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
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
    }*/
    interface HospitalActions {
        fun onHospitalClicked(hospital: Hospital)
    }

    class DiffCallback: DiffUtil.ItemCallback<Hospital>(){
        override fun areItemsTheSame(oldItem: Hospital, newItem: Hospital): Boolean {
            return oldItem.nombre == newItem.nombre
        }
        override fun areContentsTheSame(oldItem: Hospital, newItem: Hospital): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HospitalViewHolder(layoutInflater.inflate(R.layout.hospital_item, parent, false))
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int ) {
        val hospital = getItem(position)
        holder.bind(hospital)
    }

    override fun getItemCount(): Int = currentList.size

    inner class HospitalViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = HospitalItemBinding.bind(view)
        fun bind(hospital: Hospital){
            with(binding) {
                nombre.text = hospital.nombre
                cardViewHospi.setOnClickListener {
                    actions.onHospitalClicked(hospital)
                }
            }
        }
    }


}