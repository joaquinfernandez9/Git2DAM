package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var etName: TextView
    lateinit var btn: Button

    val paquete =  "eres un paquete loco"
    val hola = "hola"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = this.findViewById(R.id.cajaTexto)
        btn = this.findViewById(R.id.button)

        btn.setOnClickListener {
            if (etName.text.toString() == paquete) {
                etName.text = hola
            } else {
                etName.text = paquete
            }
        }


        etName.setText("Hola Mundo")

    }
}