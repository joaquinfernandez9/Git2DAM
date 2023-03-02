package com.example.examen.framework.xml.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.examen.R
import com.example.examen.databinding.PacientesItemBinding
import com.example.examen.domain.model.Paciente

class PatientsAdapter(
    val actions: PatientActions
) : ListAdapter<Paciente, PatientsAdapter.PatientViewHolder>(DiffCallback()) {
    interface PatientActions {
        fun onPatientClicked(patient: Paciente)
    }

    class DiffCallback : DiffUtil.ItemCallback<Paciente>() {
        override fun areItemsTheSame(oldItem: Paciente, newItem: Paciente): Boolean {
            return oldItem.nombre == newItem.nombre
        }

        override fun areContentsTheSame(oldItem: Paciente, newItem: Paciente): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PatientViewHolder(layoutInflater.inflate(R.layout.pacientes_item, parent, false))
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val patient = getItem(position)
        holder.bind(patient)
    }

    override fun getItemCount(): Int = currentList.size

    inner class PatientViewHolder(view: View) : ViewHolder(view) {
        private val binding = PacientesItemBinding.bind(view)
        fun bind(patient: Paciente) {
            with(binding) {
                nombre.text = patient.nombre
                cardViewPatient.setOnClickListener {
                    actions.onPatientClicked(patient)
                }
            }
        }
    }

}