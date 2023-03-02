package com.example.examen.framework.xml.initScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Snackbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examen.R
import com.example.examen.databinding.FragmentInitBinding
import com.example.examen.domain.model.Hospital
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.google.android.material.snackbar.Snackbar


@AndroidEntryPoint
class InitFrag : Fragment() {

    private var _binding: FragmentInitBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: InitAdapter
    //falta paciente adapter
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

        //decos por aqui por alla noseque

        adapter = InitAdapter(
            object : InitAdapter.HospitalActions {
                override fun onHospitalClicked(hospital: Hospital) {
                    TODO("Not yet implemented")
                    /*
                    viewmodel.onEvent...
                     */
                }
            }
        )
        binding.rvHospitales.adapter = adapter
    }

    //init patients

}