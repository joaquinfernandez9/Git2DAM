package com.example.examen.framework.xml

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.examen.R
import com.example.examen.databinding.ActivityMainBinding
import com.example.examen.framework.xml.detail.DetailFragment
import com.example.examen.framework.xml.getAllPatients.AllPatients
import com.example.examen.framework.xml.initScreen.InitFrag
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfig: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavigation

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        appBarConfig = AppBarConfiguration(
            setOf(R.id.initFrag,R.id.allPatients)
        )

        setupActionBarWithNavController(navController,appBarConfig)
        navView.setupWithNavController(navController)
    }

}