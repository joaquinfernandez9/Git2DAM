package com.example.mundialjoaquinfernandez.ui.pantallas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mundialjoaquinfernandez.R
import com.example.mundialjoaquinfernandez.data.const.Constantes
import com.example.mundialjoaquinfernandez.databinding.ActivityMainBinding
import com.example.mundialjoaquinfernandez.ui.pantallas.listaEquipos.ListaEquiposDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            //top bar
            setSupportActionBar(topAppBar)

            // para que salga el nombre del fragment en la barra de arriba
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            navController = navHostFragment.findNavController()

            //barra navegacion
            appBarConfiguration = AppBarConfiguration(setOf(
                R.id.lista_equipos, R.id.seleccionar_equipos
            ), drawerLayout)

            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)


            topAppBar.setNavigationOnClickListener {
                Log.i(Constantes.TAG,navController.currentDestination?.id.toString())
                drawerLayout.open()
            }

            topAppBar.navigationIcon = getDrawable(R.drawable.ic_baseline_menu_24)

            navController.addOnDestinationChangedListener { _, destination, arguments ->
                topAppBar.isVisible = arguments?.getBoolean(Constantes.SHOW_APP_BAR, false) == true
                topAppBar.navigationIcon = getDrawable(R.drawable.ic_baseline_menu_24)

                when (destination.id){
                    R.id.seleccionar_equipos -> {
                        topAppBar.visibility = View.VISIBLE
                    }
                    R.id.lista_equipos -> {
                        topAppBar.visibility = View.VISIBLE
                    }
                    R.id.lista_jugadores -> {
                        topAppBar.visibility = View.INVISIBLE

                    }
                }
            }




        }





    }
}