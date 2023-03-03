package com.example.examendiputadosjoaquinfernandez.framework.xml.crud

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
import com.example.examendiputadosjoaquinfernandez.databinding.CrudPartidosBinding
import com.example.examendiputadosjoaquinfernandez.domain.model.Partido
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CrudPartidos : Fragment() {
    private var _binding: CrudPartidosBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PartidosAdapter
    private val viewModel: ViewModelPartidos by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CrudPartidosBinding.inflate(layoutInflater)
        viewModel.handleEvent(Contract.Event.Cargar)
        init()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.state.collect { value ->
                    binding.btnAAdir.setOnClickListener {
                        viewModel.handleEvent(Contract.Event.PostPartido(binding.partidoNuevo.text.toString()))
                    }
                    viewModel.handleEvent(Contract.Event.Cargar)
                    adapter.submitList(value.partidos)
                }
            }
        }
        return binding.root
    }

    fun init() {
        val layoutManager = LinearLayoutManager(this.context)
        binding.rvPartidos.layoutManager = layoutManager

        adapter = PartidosAdapter(
            object : PartidosAdapter.Actions {
                override fun onClickDelete(nombre: String) {
                    viewModel.handleEvent(Contract.Event.DeletePartido(nombre))
                }
                override fun onClickUpdate(nombre: String) {
                    viewModel.handleEvent(Contract.Event.UpdatePartido(nombre, binding.tvNombre.text.toString()))
                }
            }
        )
        binding.rvPartidos.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}