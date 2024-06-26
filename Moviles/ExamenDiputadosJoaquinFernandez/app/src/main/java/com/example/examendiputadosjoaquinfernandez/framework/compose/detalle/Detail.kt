package com.example.examendiputadosjoaquinfernandez.framework.compose.detalle

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.examendiputadosjoaquinfernandez.framework.compose.diputados.DiputadosContract
import com.example.examendiputadosjoaquinfernandez.framework.compose.diputados.ViewModelDiputados

@Composable
fun DetailDiputado(nombre: String) {
    val viewModel: ViewModelDiputados = hiltViewModel()
    viewModel.handleEvent(DiputadosContract.Event.GetDiputado(nombre))
    val state by viewModel.state.collectAsState()

    val diputado = state.dipu

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column {
            Text(text = "ID: ${diputado?.id}")
            Text(text = "Nombre diputado: ${diputado?.nombre}")
            if (diputado?.corrupto == true) {
                Text(text = "Corrupto: Si")
            } else {
                Text(text = "Corrupto: No")
            }
            Text(text = "En el congreso desde: ${diputado?.fechaEntradaCongreso}")


            Row {
                Text(text = "Causas confirmadas: ")
                LazyRow {
                    items(diputado?.causasConfirmadas ?: emptyList()) { causasConf ->
                        Text(text = "$causasConf ,")
                    }
                }
            }
            Row {
                Text(text = "Causas supuestas: ")
                LazyRow {
                    items(diputado?.causasSupuestas ?: emptyList()) { causasSup ->
                        Text(text = "$causasSup ,")
                    }
                }
            }


        }
    }
}