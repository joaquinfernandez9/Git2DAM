package com.example.examen.framework.compose.detail

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.examen.domain.model.Enfermedades
import com.example.examen.framework.xml.detail.DetailContract
import com.example.examen.framework.xml.detail.DetailViewModel

@Composable
fun DetailPatient(nombre: String) {
    val viewModel: DetailViewModel = hiltViewModel()
    LaunchedEffect(key1 = true){
        viewModel.event(DetailContract.Event.GetPacienteNombre(nombre))
    }
    val state by viewModel.paciente.collectAsState()
    val paciente = state.pacientes

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        var nombrePaciente by remember { mutableStateOf(paciente?.nombre ?: "") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(text = "Nombre del paciente: $nombre")
            OutlinedTextField(
                value = nombrePaciente,
                onValueChange = { nombrePaciente = it },
                label = { Text(text = "Nombre del paciente") }
            )

            //boton para actualizar ese nombre
            Button(onClick = {
                if (paciente != null) {
                    paciente.nombre = nombrePaciente
                    viewModel.event(DetailContract.Event.UpdatePaciente(paciente.id, paciente))
                }
            }) {
                Text(text = "Actualizar nombre")
            }

            var nombreEnfermedad by remember { mutableStateOf("") }

            OutlinedTextField(
                value = nombreEnfermedad,
                onValueChange = { nombreEnfermedad = it },
                label = { Text(text = "Nombre de la enfermedad") }
            )
            Button(onClick = {
                if (paciente != null) {
                    viewModel.event(
                        DetailContract.Event.PostEnfermedad(
                            paciente.id,
                            Enfermedades(nombreEnfermedad)
                        )
                    )
                }
            }) {
                Text(text = "Añadir enfermedad")
            }


            Button(onClick = {
                if (paciente != null) {
                    viewModel.event(DetailContract.Event.VerEnfermedades(paciente))
                }
            }) {
                Text(text = "Mostrar enfermedades")
            }

            //lazy column para las enfermedades
            LazyColumn {
                items(paciente?.enfermedades ?: emptyList()) { enfermedad ->
                    Text(text = enfermedad.toString())
                }
            }

        }


    }


}