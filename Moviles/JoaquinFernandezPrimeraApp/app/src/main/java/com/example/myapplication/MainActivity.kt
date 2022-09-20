package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var etName: TextView
    lateinit var btn: Button

    lateinit var btnLogin: Button
    lateinit var cajaEmail: EditText
    lateinit var cajaPassword: EditText

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


        btnLogin = this.findViewById(R.id.btnLogin)
        cajaEmail = this.findViewById(R.id.email)
        cajaPassword = this.findViewById(R.id.pass)

        btnLogin.setOnClickListener {
            if (cajaEmail.text.toString().contains("@gmail.com")){
                if (cajaPassword.text.toString().length in 8..13){
                    Toast.makeText(this, "Login Correcto", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(this, "Login Incorrecto", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(this, "Login Incorrecto", Toast.LENGTH_SHORT).show()
        }

        etName.text = hola

    }
}