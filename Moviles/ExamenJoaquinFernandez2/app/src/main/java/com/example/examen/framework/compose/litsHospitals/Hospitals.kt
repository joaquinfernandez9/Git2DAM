package com.example.examen.framework.compose.litsHospitals

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.examen.framework.compose.BottomBarScreen
import com.example.examen.framework.xml.initScreen.InitContract
import com.example.examen.framework.xml.initScreen.InitViewModel

@Composable
fun Hospitals(onNavigate: (String) -> Unit) {
    val viewModel: InitViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomBar(navController) },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    //hospitals
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items(state.hospitales ?: emptyList()) { item ->
                                Button(onClick = {
                                    viewModel.handleEvent(InitContract.Event.GetPacientes(item))
                                }) {
                                    Text(
                                        text = item.nombre,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp)
                                    )
                                }
                            }
                        }
                    }
                    //patients
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items(state.pacientes) { item ->
                                Button(onClick = {
                                    onNavigate(item.nombre)
                                }) {
                                    Text(text = item.nombre)
                                }
                            }
                        }

                    }

                }
            }

        })
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}
