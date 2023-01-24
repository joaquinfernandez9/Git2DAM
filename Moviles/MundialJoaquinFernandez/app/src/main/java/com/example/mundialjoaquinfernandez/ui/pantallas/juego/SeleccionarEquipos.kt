package com.example.mundialjoaquinfernandez.ui.pantallas.juego

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mundialjoaquinfernandez.R
import com.example.mundialjoaquinfernandez.data.const.Constantes
import com.example.mundialjoaquinfernandez.databinding.ElegirEquiposBinding
import com.example.mundialjoaquinfernandez.ui.pantallas.listaEquipos.ListaEquiposEvent
import com.example.mundialjoaquinfernandez.ui.pantallas.listaEquipos.ListaEquiposViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeleccionarEquipos : Fragment() {

    private var _binding: ElegirEquiposBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: SeleccionAdapter

    private val viewModel: SeleccionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = ElegirEquiposBinding.inflate(inflater, container, false)
        viewModel.handleEvent(SeleccionEvent.GetAll)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            viewModel.state.observe(viewLifecycleOwner) {
                adapter.submitList(it.list)
                it.list.let { list ->
                    val adapterLocal = ArrayAdapter(requireContext(), R.layout.seleccion_item, list)
                    (binding.local.editText as? AutoCompleteTextView)?.setAdapter(adapterLocal)
                    val adapterVisitante = ArrayAdapter(requireContext(), R.layout.seleccion_item, list)
                    (binding.visitante.editText as? AutoCompleteTextView)?.setAdapter(adapterVisitante)
                }
            }

            adapter = SeleccionAdapter()


            jugar.setOnClickListener {
                val local = localTF.text.toString()
                val visitante = visitanteTF.text.toString()
                if (local == visitante) {
                    val snack =
                        Snackbar.make(it, Constantes.ERROR_MISMO_EQUIPO, Snackbar.LENGTH_LONG)
                    snack.show()
                } else if (local.isBlank() || visitante.isBlank()) {
                    val snack =
                        Snackbar.make(it, Constantes.ERROR_EQUIPO_VACIO, Snackbar.LENGTH_LONG)
                    snack.show()
                } else {
                    val action =
                        SeleccionarEquiposDirections.resultadoPartido(local, visitante)
                    findNavController().navigate(action)
                }

            }


        }
    }

}