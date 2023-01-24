package com.example.mundialjoaquinfernandez.ui.pantallas.listaEquipos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mundialjoaquinfernandez.databinding.ListaEquiposBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListaEquipos : Fragment() {

    private var _binding: ListaEquiposBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ListaEquiposAdapter

    private val viewModel: ListaEquiposViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ListaEquiposBinding.inflate(layoutInflater)

        viewModel.handleEvent(ListaEquiposEvent.GetAll)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewModel.state.observe(viewLifecycleOwner) {
                adapter.submitList(it.list)
            }


            adapter = ListaEquiposAdapter(
                object : ListaEquiposAdapter.Actions {
                    override fun onClickDetail(nombre: String) {
                        val action = ListaEquiposDirections.verJugadores(nombre)
                        findNavController().navigate(action)
                    }
                }
            )

            rvEquipos.adapter = adapter

        }


    }


}


