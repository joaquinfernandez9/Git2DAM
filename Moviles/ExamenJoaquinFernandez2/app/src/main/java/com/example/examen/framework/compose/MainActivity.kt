package com.example.examen.framework.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examen.framework.compose.detail.DetailPatient
import com.example.examen.framework.compose.listPatients.Patients
import com.example.examen.framework.compose.litsHospitals.Hospitals
import com.example.joaquinfernandezcomposefinal.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Navigation()

                }

            }
        }
    }
}

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "init"){
        composable("init"){
            Hospitals()
        }
        composable("patients"){
            Patients(onNavigate = {id -> navController.navigate(id)})
        }
        composable("detail/{nombre}"){
            val nombre = it.arguments?.getString("nombre")
//            DetailPatient()
        }
    }
}