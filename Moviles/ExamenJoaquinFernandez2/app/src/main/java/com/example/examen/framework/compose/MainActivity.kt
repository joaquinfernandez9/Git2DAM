package com.example.examen.framework.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Navigation(navController = navController)
                }

            }
        }
    }
}
@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "init") {
        composable("init") {
            Hospitals(onNavigate = { id -> navController.navigate(id) })
        }
        composable("patients") {
            Patients(onNavigate = { id -> navController.navigate(id) })
        }
        composable("detail_patient/{nombre}") {
            val nombre = it.arguments?.getString("nombre")
//            DetailPatient()
        }
    }



}