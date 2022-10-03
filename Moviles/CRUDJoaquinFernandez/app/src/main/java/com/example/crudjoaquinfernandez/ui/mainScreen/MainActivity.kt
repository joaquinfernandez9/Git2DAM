package com.example.crudjoaquinfernandez.ui.mainScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crudjoaquinfernandez.R
import com.example.crudjoaquinfernandez.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
    }
}