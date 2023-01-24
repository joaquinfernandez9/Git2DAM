package com.example.mundialjoaquinfernandez.ui.pantallas.juego.resultado

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.mundialjoaquinfernandez.R
import com.example.mundialjoaquinfernandez.data.const.Constantes
import com.example.mundialjoaquinfernandez.databinding.ResultadoPartidoBinding
import com.example.mundialjoaquinfernandez.model.Partido
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultadoPartido : Fragment() {

    private var _binding: ResultadoPartidoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ResultadoViewModel by viewModels()
    val args : ResultadoPartidoArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ResultadoPartidoBinding.inflate(inflater, container, false)

        val local : TextView = binding.localTeam
        val visitante : TextView = binding.visitingTeam


        local.append(args.local)
        visitante.append(args.visitante)

        val partido = Partido(args.local, args.visitante,)
        viewModel.handleEvent(ResultadoEvent.JugarPartido(partido))


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            verResultado.setOnClickListener {
                viewModel.handleEvent(ResultadoEvent.GetPartido(args.local, args.visitante))

                viewModel.state.observe(viewLifecycleOwner){
                    goals.append(args.local+ Constantes.BLANK + it.partido.golesLocal.toString() + Constantes.GUION + args.visitante+ Constantes.BLANK+ it.partido.golesVisitante.toString())
                    fechaText.append(it.partido.fecha.toString())
                }
                verResultado.visibility = View.GONE
            }

        }

    }


}