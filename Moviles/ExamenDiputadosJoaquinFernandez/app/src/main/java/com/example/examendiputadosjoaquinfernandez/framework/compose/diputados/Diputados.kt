package com.example.examendiputadosjoaquinfernandez.framework.compose.diputados

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.examendiputadosjoaquinfernandez.domain.model.Diputado

@Composable
fun Diputados(onNavigate: (String) -> Unit) {
    val viewModel: ViewModelDiputados = hiltViewModel()
    val state by viewModel.state.collectAsState()
    var text by remember {
        mutableStateOf(state.error ?: "")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
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
                    items(state.partidos ?: emptyList()) { item ->
                        Button(onClick = {
                            //1790101a-0328-40a4-b5bd-3cfd22c8fa70
                            viewModel.handleEvent(DiputadosContract.Event.GetDiputados(item.id.toString()))
                            if(state.error != null){
                                text = state.error.toString()
                                viewModel.handleEvent(DiputadosContract.Event.LimpiarError)
                            } else{
                                text = "lista diputados de ${item.id}"
                            }

                        }) {
                            Text(
                                text = item.nombre,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            )
                        }
                    }
                }
            }
            //no entiendo por que no carga la lista, en el viewmodel deberia estar todo correcto y llamo a la funcion get diputados
            //para buscar los diputados de cada partido,
            //el id del partido lo recibe bien, he puesto el 'text' justo para comprobar eso, no se que puede estar pasando
            //la pantalla detail esta hecha pero no puedo acceder a ella asi que no se si se muestran bien las cosas
            //la navegacion esta hecha con el nombre del diputado que ha sido clickeado
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(state.diputados ?: emptyList()) { item ->
                        if (item.corrupto) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .background(color = Color.Red),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                //backgroundColor = Color.Red

                            ) {
                                Button(onClick = {
                                    onNavigate(item.nombre)
                                }) {
                                    Text(
                                        text = item.nombre, modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp)
                                    )
                                }
                                Text(text = item.fechaEntradaCongreso)
                            }
                        } else {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                                    .weight(1f)
                            ) {
                                Button(onClick = {
                                    onNavigate(item.nombre)
                                }) {
                                    Text(
                                        text = item.nombre, modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp)
                                    )
                                }
                                Text(text = item.fechaEntradaCongreso)
                            }

                        }
                    }


                }

            }
        }
    }
}