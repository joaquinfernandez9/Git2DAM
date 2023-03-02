package com.example.examen.framework.xml.getAllPatients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examen.R
import com.example.examen.databinding.AllPatientsBinding
import com.example.examen.databinding.FragmentInitBinding
import com.example.examen.domain.model.Paciente
import com.example.examen.framework.xml.adapter.HospitalsAdapter
import com.example.examen.framework.xml.adapter.PatientsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllPatients : Fragment() {
    private var _binding: AllPatientsBinding? = null
    private val binding get() = _binding!!
    private lateinit var patientsAdapter: PatientsAdapter
    private val viewModel: PatientsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.handleEvent(PatientsContract.Event.Cargar)
        _binding = AllPatientsBinding.inflate(layoutInflater)
        init()
        viewLifecycleOwner.lifecycleScope.launch{
            viewModel.patients.collect { value ->
                patientsAdapter.submitList(value.pacientes)
            }
        }
        return binding.root
    }


    private fun init(){
        val layoutManager = LinearLayoutManager(this.context)
        binding.rvPatients.layoutManager = layoutManager

        patientsAdapter = PatientsAdapter(
            object : PatientsAdapter.PatientActions {
                override fun onPatientClicked(patient: Paciente) {
                    findNavController().navigate(R.id.action_all_patients_to_detailFragment, Bundle().apply {
                        putString("id", patient.id)
                    })
                }
            }
        )
        binding.rvPatients.adapter = patientsAdapter
    }
}