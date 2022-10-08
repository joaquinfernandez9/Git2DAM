package com.example.colocacioncosas.ui.pantallaMain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.colocacioncosas.R
import com.example.colocacioncosas.databinding.ActivityMainBinding
import com.example.colocacioncosas.domain.usecases.personas.AddPersona
import com.example.colocacioncosas.domain.usecases.personas.GetPersonas

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //by = delegate functions
    private val viewModel: MainViewModel by viewModels(){
        MainViewModelFactory(
//            StringProvider.instance(this),
            AddPersona(),
            GetPersonas(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

    }


    //switch = when

}