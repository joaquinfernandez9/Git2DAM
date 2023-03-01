package com.example.joaquinfernandezcomposefinal.ui.framework.game

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.joaquinfernandezcomposefinal.domain.model.Equipo


@Composable
fun GameScreen(navController: NavController) {
    val viewModel: GameViewModel = hiltViewModel()
    var equipo1 by remember { mutableStateOf<Equipo?>(null) }
    var equipo2 by remember { mutableStateOf<Equipo?>(null) }

    var expanded by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }
    var textfieldSize2 by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    val equipos = viewModel.state.collectAsState()

    val context = LocalContext.current

    Column(Modifier.padding(20.dp)) {
        Row(Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = equipo1?.nombre ?: "",
                onValueChange = { equipo1 = Equipo(it, emptyList()) },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textfieldSize = coordinates.size.toSize()
                    },
                label = { Text("Equipo 1") },
                trailingIcon = {
                    Icon(icon, "contentDescription",
                        Modifier.clickable { expanded = !expanded })
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
            ) {
                equipos.value.forEach { label ->
                    DropdownMenuItem(onClick = {
                        equipo1 = label
                        expanded = false
                    }) {
                        Text(label.nombre)
                    }
                }

            }
        }
        Row(Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = equipo2?.nombre ?: "",
                onValueChange = { equipo2 = Equipo(it, emptyList()) },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textfieldSize2 = coordinates.size.toSize()
                    },
                label = { Text("Equipo 2") },
                trailingIcon = {
                    Icon(icon, "contentDescription",
                        Modifier.clickable { expanded2 = !expanded2 })
                }
            )
            DropdownMenu(
                expanded = expanded2,
                onDismissRequest = { expanded2 = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
            ) {
                equipos.value.forEach { label ->
                    DropdownMenuItem(onClick = {
                        equipo2 = label
                        expanded2 = false
                    }) {
                        Text(label.nombre)
                    }
                }
            }
        }

        Button(
            onClick = {
                if (equipo1 == null || equipo2 == null || equipo1 != equipo2) {
                    if (equipos.value.contains(equipo1) && equipos.value.contains(equipo2)) {
                        viewModel.handleEvent(
                            GameContract.Event.Play(
                                equipo1!!.nombre,
                                equipo2!!.nombre
                            )
                        )
                        navController.navigate("resultadoPartido/${equipo1!!.nombre}/${equipo2!!.nombre}")
                    } else {
                        Toast.makeText(
                            context,
                            "Selecciona equipos de la lista",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Los equipos seleccionados deben ser diferentes",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }, modifier = Modifier.padding(20.dp).align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Jugar")
        }
    }


}





