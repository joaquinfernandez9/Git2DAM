package com.example.examen.framework.xml

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.Fragment
import com.example.examen.R
import com.example.examen.databinding.ActivityMainBinding
import com.example.examen.framework.xml.detail.DetailFragment
import com.example.examen.framework.xml.getAllPatients.AllPatients
import com.example.examen.framework.xml.initScreen.InitFrag
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        replaceFragment(InitFrag())
        val intent =
            Intent(this@MainActivity, com.example.examen.framework.compose.MainActivity::class.java)
        with(binding) {
            bottomNavigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.firstScreen -> {
                        replaceFragment(InitFrag())
                        true
                    }
                    R.id.secondScreen -> {
                        replaceFragment(AllPatients())
                        true
                    }
                    R.id.thirdScreen -> {
                        replaceFragment(DetailFragment())
                        true
                    }
                    R.id.compose -> {
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
            setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            commit()
        }
    }
}