package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var etName: TextView
    private lateinit var btn: Button

    private lateinit var btnLogin: Button
    private lateinit var cajaEmail: EditText
    private lateinit var cajaPassword: EditText

    private val paquete = resources.getString(R.string.paquete)
    private val hola = resources.getString(R.string.hola)

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
            if (cajaEmail.text.toString().contains(resources.getString(R.string.gmail))){
                if (cajaPassword.text.toString().length in 8..13){
                    Toast.makeText(this,resources.getString(R.string.LogOK) , Toast.LENGTH_SHORT).show()
                } else Toast.makeText(this, resources.getString(R.string.LogERROR), Toast.LENGTH_SHORT).show()
            } else Toast.makeText(this, resources.getString(R.string.LogERROR), Toast.LENGTH_SHORT).show()
        }

        etName.text = hola

    }
}