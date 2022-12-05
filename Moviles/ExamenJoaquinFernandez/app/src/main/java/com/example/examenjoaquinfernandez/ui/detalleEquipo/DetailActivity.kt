package com.example.examenjoaquinfernandez.ui.detalleEquipo

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examenjoaquinfernandez.databinding.DetailEquipoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity(){


    private lateinit var binding: DetailEquipoBinding

    private val viewModel: DetailViewmodel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState, )

        binding = DetailEquipoBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)

            viewModel.handleEvent(
                DetailEvent.GetEquipo(
                    intent.getStringExtra("nombre")
                )
            )

            viewModel.handleEvent(
                DetailEvent.GetAllComponents(
                    intent.getStringExtra("nombre")
                )
            )

            val listaCompo = viewModel.state.value?.equipo?.componentes

            val adapter = DetailAdapter()

            viewModel.state.observe(this@DetailActivity) { state->
                state.error.let {
                    Toast.makeText(this@DetailActivity, "it", Toast.LENGTH_SHORT).show()
                }

                state.equipo?.let {
                    tvNombreEquipo.text = it.nombreEquipo
                    Toast.makeText(this@DetailActivity, it.nombreEquipo, Toast.LENGTH_SHORT).show()

                    adapter.submitList(it.componentes)
                }

            }

            listaCompo.let {
                rvComponentes.adapter = adapter
                rvComponentes.layoutManager = LinearLayoutManager(this@DetailActivity)
            }


        }
    }


}