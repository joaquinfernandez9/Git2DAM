package com.example.joaquinfernandezcomposefinal.ui.framework


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.unit.dp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.joaquinfernandezcomposefinal.ui.framework.mainScreen.InitScreen
import com.example.joaquinfernandezcomposefinal.ui.theme.AppTheme
import androidx.navigation.compose.rememberNavController
import com.example.joaquinfernandezcomposefinal.ui.framework.game.GameActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

//hacer navigation
@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
    startDestination = "initScreen"){
        composable("initScreen"){
            InitScreen(onNavigate = {
                id -> navController.navigate(id)
            })
        }
        composable("gameActivity"){
            GameActivity(onNavigate = {
                id -> navController.navigate(id)
            })
        }
        composable("resultadoPartido"){

        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    AppTheme {
//        InitScreen()
    }
}