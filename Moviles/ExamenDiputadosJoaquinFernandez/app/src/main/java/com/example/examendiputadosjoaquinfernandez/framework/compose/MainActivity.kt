package com.example.examendiputadosjoaquinfernandez.framework.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.examendiputadosjoaquinfernandez.framework.compose.detalle.DetailDiputado
import com.example.examendiputadosjoaquinfernandez.framework.compose.diputados.Diputados
import com.example.examendiputadosjoaquinfernandez.ui.theme.ExamenDiputadosJoaquinFernandezTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ExamenDiputadosJoaquinFernandezTheme {
                Navigation(navHostController = navController)
            }
        }
    }
}

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "diputados") {
        composable("diputados") {
            Diputados(onNavigate = { nombre -> navHostController.navigate("detail_patient/$nombre") })
        }
        composable("detail/{nombre}",
            arguments = listOf(navArgument("nombre") { type = NavType.StringType })
        ) {
            val nombre = it.arguments?.getString("nombre")
            requireNotNull(nombre)
            DetailDiputado(nombre = nombre)
        }
    }
}

