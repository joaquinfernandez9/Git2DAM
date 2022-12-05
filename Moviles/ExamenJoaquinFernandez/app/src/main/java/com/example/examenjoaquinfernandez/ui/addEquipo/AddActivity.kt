package com.example.examenjoaquinfernandez.ui.addEquipo

import android.icu.number.IntegerWidth
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.examenjoaquinfernandez.R
import com.example.examenjoaquinfernandez.databinding.AddEquipoBinding
import com.example.examenjoaquinfernandez.domain.model.Equipo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddActivity: AppCompatActivity() {

    private lateinit var binding: AddEquipoBinding

    private val viewModel: AddViewmodel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState,)

        binding = AddEquipoBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)

            addEquipo.setOnClickListener {
                if (posicion.text.isNotBlank() && nombreEquipo.text.isNotBlank()
                    && nacionalidad.text.isNotBlank()) {
                    viewModel.handleEvent(
                        AddEvent.AddEquipo(
                            Equipo(
                                nombreEquipo = nombreEquipo.text.toString(),
                                nacionalidad = nacionalidad.text.toString(),
                                puestoFinal = Integer.parseInt(posicion.text.toString()),
                                componentes = emptyList()
                            )
                        )
                    )

                } else {
                    Toast.makeText(
                        this@AddActivity,
                        "llena toods los campos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            viewModel.state.observe(this@AddActivity) {state->
                state.error?.let {
                    Toast.makeText(this@AddActivity, "Error al añadir", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}