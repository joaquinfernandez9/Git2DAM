package com.example.examen.framework.xml.initScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examen.R
import com.example.examen.databinding.FragmentInitBinding
import com.example.examen.domain.model.Hospital
import com.example.examen.domain.model.Paciente
import com.example.examen.framework.xml.adapter.HospitalsAdapter
import com.example.examen.framework.xml.adapter.PatientsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.google.android.material.snackbar.Snackbar


@AndroidEntryPoint
class InitFrag : Fragment() {

    private var _binding: FragmentInitBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HospitalsAdapter
    private lateinit var patientsAdapter: PatientsAdapter
    private val viewModel: InitViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInitBinding.inflate(layoutInflater)
        viewModel.handleEvent(InitContract.Event.Cargar)
        init()
        initPatients()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { value ->
                    if (viewModel.state.value.error != null) {
                        Snackbar.make(
                            binding.root, "no internet", Snackbar.LENGTH_LONG
                        ).show()
                    }
                    adapter.submitList(value.hospitales)
                }
            }
        }
        return binding.root
    }


    private fun init() {
        val layoutManager = LinearLayoutManager(context)
        binding.rvHospitales.layoutManager = layoutManager

        adapter = HospitalsAdapter(
            object : HospitalsAdapter.HospitalActions {
                override fun onHospitalClicked(hospital: Hospital) {
                    viewModel.handleEvent(InitContract.Event.GetPacientes(hospital))
                    viewLifecycleOwner.lifecycleScope.launch {
                        repeatOnLifecycle(Lifecycle.State.STARTED) {
                            viewModel.state.collect { value ->
                                patientsAdapter.submitList(value.pacientes)
                            }
                        }
                    }
                }
            }
        )
        binding.rvHospitales.adapter = adapter
    }

    //init patients
    private fun initPatients() {
        val layoutManager = LinearLayoutManager(this.context)
        binding.recyclerViewPacientes.layoutManager = layoutManager

        patientsAdapter = PatientsAdapter(
            object : PatientsAdapter.PatientActions {
                override fun onPatientClicked(patient: Paciente) {
                    findNavController().navigate(R.id.action_initFrag_to_detailFragment,
                        Bundle().apply
                        { putString("id", patient.id) })
                }
            }
        )
        binding.recyclerViewPacientes.adapter = patientsAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = FragmentInitBinding.bind(binding.root)
    }
}