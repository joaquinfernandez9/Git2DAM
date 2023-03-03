package com.example.examendiputadosjoaquinfernandez.framework.xml

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.examendiputadosjoaquinfernandez.R
import com.example.examendiputadosjoaquinfernandez.databinding.XmlActivityBinding
import com.example.examendiputadosjoaquinfernandez.framework.compose.MainActivity
import com.example.examendiputadosjoaquinfernandez.framework.xml.crud.CrudPartidos
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityXML : AppCompatActivity() {
    private lateinit var binding: XmlActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // se habia creado mal el theme del manifest
        binding = XmlActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(CrudPartidos())
        with(binding) {
            bottomNavigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.crudDiputados -> {
                        replaceFragment(CrudPartidos())
                        true
                    }
                    R.id.go_compose -> {
                        val intent = Intent(this@ActivityXML, MainActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    else -> false
                }
            }
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }
    }
}