package com.example.joaquinfernandezcomposefinal.ui.framework.result

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController


@Composable
fun ResultScreen(equipo1: String, equipo2: String) {
    val viewmodel: ResultViewmodel = hiltViewModel()
    val partido = viewmodel.state.collectAsState()
    var localScoreApuesta by remember { mutableStateOf(0) }
    var visitanteScoreApuesta by remember { mutableStateOf(0) }
    val context = androidx.compose.ui.platform.LocalContext.current
    var enabledActive by remember { mutableStateOf(true) }

    val localScore = partido.value.golesLocal
    val visitanteScore = partido.value.golesVisitante
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "$equipo1 $localScore", fontSize = 32.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "$equipo2 $visitanteScore", fontSize = 32.sp)
            }

        }
        Row {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { localScoreApuesta-- }, enabled = enabledActive) {
                    Text(text = "-")
                }
                Text(text = equipo1)
                Text(text = localScoreApuesta.toString(), fontSize = 32.sp)
                Button(onClick = { localScoreApuesta++ }, enabled = enabledActive) {
                    Text(text = "+")
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { visitanteScoreApuesta-- }, enabled = enabledActive) {
                    Text(text = "-")
                }
                Text(text = equipo2)
                Text(text = visitanteScoreApuesta.toString(), fontSize = 32.sp)
                Button(onClick = { visitanteScoreApuesta++ }, enabled = enabledActive) {
                    Text(text = "+")
                }
            }

        }
        Row {
            val contador = remember { mutableStateOf(0) }
            //Show result with button
            Button(onClick = {
                do {
                    if (localScoreApuesta == localScore && visitanteScoreApuesta == visitanteScore) {
                        Toast.makeText(context, "Has acertado!", Toast.LENGTH_SHORT).show()
                        viewmodel.handleEvent(ResultContract.Event.GetLast)
                        enabledActive = false
                        break
                    } else {
                        Toast.makeText(context, "Has fallado :(", Toast.LENGTH_SHORT).show()
                        contador.value++
                    }
                } while (contador.value < 3)
                if (contador.value == 3) {
                    Toast.makeText(
                        context,
                        "Has fallado 3 veces, no puedes seguir jugando",
                        Toast.LENGTH_SHORT
                    ).show()
                    viewmodel.handleEvent(ResultContract.Event.GetLast)
                    enabledActive = false
                }
            }, enabled = enabledActive) {
                Text(text = "Get Last Match")
            }
        }
    }
}


