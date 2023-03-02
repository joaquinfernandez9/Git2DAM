package com.example.examen.framework.xml.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examen.R
import com.example.examen.databinding.DetailPatientBinding
import com.example.examen.domain.model.Enfermedades
import com.example.examen.framework.xml.adapter.EnfermedadesAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {


    private var _binding: DetailPatientBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: EnfermedadesAdapter
    private val viewModel: DetailViewModel by viewModels()
    private var id: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailPatientBinding.inflate(layoutInflater)
        arguments?.let {
            id = it.getString("id")
        }
        viewModel.event(DetailContract.Event.GetPaciente(id!!))
        init()
        viewLifecycleOwner.lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.paciente.collect { value->
                    if (viewModel.paciente.value.error != null){
                        Snackbar.make(
                            binding.root,
                            "no tira bro",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }

                    binding.nombrePaciente.setText(value.pacientes?.nombre)
                    //update name
                    binding.button.setOnClickListener {
                        viewModel.event(DetailContract.Event.UpdatePaciente(id!!, value.pacientes!!))
                    }

                    //add sickness
                    binding.button2.setOnClickListener {
                        val enfermedad = Enfermedades(nombre = binding.nombreEnfermedad.text.toString())
                        viewModel.event(DetailContract.Event.PostEnfermedad(id!!, enfermedad))
                    }

                    //mostrar enfermedades
                    binding.button3.setOnClickListener {
                        viewModel.event(DetailContract.Event.VerEnfermedades(value.pacientes!!))
                    }
                    adapter.submitList(value.enfermedades)
                }
            }
        }
        return binding.root
    }

    fun init() {
        val layoutManager = LinearLayoutManager(this.context)
        binding.rvEnfermedades.layoutManager = layoutManager
        adapter = EnfermedadesAdapter()
        binding.rvEnfermedades.adapter = adapter
    }

}