package com.example.joaquinfernandezcomposefinal.ui.framework.mainScreen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.joaquinfernandezcomposefinal.ui.framework.game.GameViewModel

@Composable
fun InitScreen(onNavigate: (String) -> Unit) {
    val viewModel: InitViewmodel = hiltViewModel()
    val list = viewModel.state.collectAsState()
    val context = androidx.compose.ui.platform.LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        Toast.makeText(context, "Falta implementar", Toast.LENGTH_SHORT).show()
                    },
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
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //llamar a lista de partidos desde el viewmodel
                items(list.value) { item ->
                    Text(text = item.toString(), modifier = Modifier.padding(8.dp))
                }
            }
        }
        FloatingActionButton(
            onClick = { viewModel.handleEvent(InitContract.Event.Init) },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Icon(Icons.Filled.Refresh, contentDescription = "Refresh")
        }
    }
}