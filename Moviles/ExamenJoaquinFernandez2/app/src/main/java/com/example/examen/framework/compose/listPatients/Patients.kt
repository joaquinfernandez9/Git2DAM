package com.example.examen.framework.compose.listPatients

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.examen.domain.model.Paciente

@Composable
fun Patients(onNavigate: (String) -> Unit) {
    val lista = listOf<Paciente>()
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)){
        Text(text = "pacientes")
        Column() {
            LazyColumn(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally) {
                items(lista){item ->
                Button(onClick = { onNavigate("detail_patient/{${item.nombre}}")}, ) {
                    Text(text = item.nombre)
                }
                }
            }

        }
    }
}