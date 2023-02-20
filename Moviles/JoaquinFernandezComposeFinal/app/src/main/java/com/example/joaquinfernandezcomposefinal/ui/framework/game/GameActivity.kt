package com.example.joaquinfernandezcomposefinal.ui.framework.game

import androidx.activity.ComponentActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun GameActivity(onNavigate: (String) -> Unit) {
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

    val equipos = listOf(
        Equipo(1, "Real Madrid"),
        Equipo(2, "Barcelona"),
        Equipo(3, "Atlético de Madrid"),
        Equipo(4, "Valencia"),
        Equipo(5, "Sevilla")
    )

    val context = LocalContext.current


    Column(Modifier.padding(20.dp)) {
        Row(Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = equipo1?.nombre ?: "",
                onValueChange = { equipo1 = Equipo(1, it) },
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
                equipos.forEach { label ->
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
                onValueChange = { equipo2 = Equipo(2, it) },
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
                equipos.forEach { label ->
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
                if (equipo1 != equipo2) {
                    onNavigate("resultadoPartido")
                } else {
                    Toast.makeText(
                        context,
                        "Los equipos seleccionados deben ser diferentes",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        ) {
            Text(text = "Jugar")
        }
    }


}


//mover y poner bien, esto solo prueba
data class Equipo(val id: Int, val nombre: String)

// Lista de equipos


@Preview
@Composable
fun GameActivityPreview() {
    GameActivity(onNavigate = {})
}
