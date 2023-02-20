package com.example.joaquinfernandezcomposefinal.ui.framework.mainScreen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InitScreen(onNavigate: (String)-> Unit) {
    var context = androidx.compose.ui.platform.LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { Toast.makeText(context, "Falta implementar", Toast.LENGTH_SHORT).show() },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Ver Equipos")
            }
            Button(
                onClick = { onNavigate("gameActivity") },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Jugar")
            }
        }
        LazyColumn(
            modifier = Modifier.weight(1f).padding(16.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //llamar a lista de partidos desde el viewmodel
            items(listOf("Partido 1", "Partido 2", "Partido 3")) { item ->
                Text(text = item, modifier = Modifier.padding(8.dp))
            }
        }
    }
}