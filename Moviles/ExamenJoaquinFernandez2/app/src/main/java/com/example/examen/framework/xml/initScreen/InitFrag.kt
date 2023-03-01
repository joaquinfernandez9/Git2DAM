package com.example.examen.framework.xml.initScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examen.R
import com.example.examen.databinding.FragmentInitBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InitFrag : Fragment() {

    private var _binding: FragmentInitBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: InitAdapter
    private val viewModel: InitViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInitBinding.inflate(layoutInflater)
        viewModel.handleEvent(InitContract.Event.Cargar)
        init()
        return binding.root
    }


    private fun init(){
        val layoutManager = LinearLayoutManager(context)
        binding.rvHospitales.layoutManager = layoutManager



        adapter = InitAdapter()
        binding.rvHospitales.adapter = adapter
    }

}