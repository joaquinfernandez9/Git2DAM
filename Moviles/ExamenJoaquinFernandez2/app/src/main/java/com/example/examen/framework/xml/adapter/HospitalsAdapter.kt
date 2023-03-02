package com.example.examen.framework.xml.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.examen.R
import com.example.examen.databinding.HospitalItemBinding
import com.example.examen.domain.model.Hospital

class HospitalsAdapter(
    val actions: HospitalActions
) : ListAdapter<Hospital, HospitalsAdapter.HospitalViewHolder>(DiffCallback()) {
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