package com.example.examen.framework.compose.litsHospitals

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.examen.framework.xml.initScreen.InitContract
import com.example.examen.framework.xml.initScreen.InitViewModel

@Composable
fun Hospitals(onNavigate: (String) -> Unit) {
    val viewModel: InitViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            //hospitals
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(state.hospitales ?: emptyList()) { item ->
                        Button(onClick = {
                            //esto carga la lista de pacientes
                            viewModel.handleEvent(InitContract.Event.GetPacientes(item))
                        }) {
                            Text(text = item.nombre)
                        }
                    }
                }
            }
            //patients
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(state.pacientes) { item ->
                        Button(onClick = {
                            onNavigate("detail_patient/${item.nombre}")
                        }) {
                            Text(text = item.nombre)
                        }
                    }
                }

            }

        }
    }
}

@Preview
@Composable
fun PreviewHospitals() {
    Hospitals(onNavigate = {})
}