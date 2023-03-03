package com.example.examen.framework.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.examen.framework.compose.detail.DetailPatient
import com.example.examen.framework.compose.listPatients.Patients
import com.example.examen.framework.compose.litsHospitals.Hospitals
import com.example.joaquinfernandezcomposefinal.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppTheme {
                Navigation(navController = navController)
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "init") {
        composable("init") {
            Hospitals(onNavigate = { nombre -> navController.navigate("detail_patient/$nombre") })
        }
        composable("patients") {
            Patients(onNavigate = { nombre -> navController.navigate(nombre) })
        }
        composable(
            "detail_patient/{nombre}",
            arguments = listOf(navArgument("nombre") { type = NavType.StringType })
        ) {
            val nombre = it.arguments?.getString("nombre")
            requireNotNull(nombre)
            DetailPatient(nombre = nombre)
        }
    }
}

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "init"){
        composable("init") {
            Hospitals(onNavigate = { nombre -> navController.navigate("detail_patient/$nombre") })
        }
        composable("patients") {
            Patients(onNavigate = { nombre -> navController.navigate(nombre) })
        }
    }
}

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Profile : BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )


}