package com.example.mundialjoaquinfernandez.ui.pantallas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mundialjoaquinfernandez.databinding.PrincipalScreenBinding


class PrincipalScreen : Fragment() {

    private var _binding: PrincipalScreenBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PrincipalScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            verListado.setOnClickListener {
                val action = PrincipalScreenDirections
                    .actionPrimerFragmentToSegundoFragment()
                findNavController().navigate(action)
            }
            jugar.setOnClickListener {
                val action = PrincipalScreenDirections
                    .actionPrimerFragmentToTercerFragment()

                findNavController().navigate(action)
            }
        }
    }


}