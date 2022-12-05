package com.example.examenjoaquinfernandez.ui.listaEquipos

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examenjoaquinfernandez.R
import com.example.examenjoaquinfernandez.databinding.ListaEquiposBinding
import com.example.examenjoaquinfernandez.domain.model.Equipo
import com.example.examenjoaquinfernandez.ui.addEquipo.AddActivity
import com.example.examenjoaquinfernandez.ui.detalleEquipo.DetailActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EquiposActivity : AppCompatActivity() {
    private lateinit var binding: ListaEquiposBinding

    private val viewModel: EquiposViewmodel by viewModels()

    override fun onResume() {
        super.onResume()
        viewModel.handleEvent(EquiposEvent.GetAll)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.goToAddTeam -> {
                val intent = Intent(this@EquiposActivity, AddActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    inner class Imple : Actions {
        override fun onClickDelete(nombre: String) {
            viewModel.handleEvent(
                EquiposEvent.Delete(nombre)
            )
        }

        override fun onClickDetail(nombre: String) = onDetail(nombre)
    }

    private fun onDetail(nombre: String) {
        val intent = Intent(
            this@EquiposActivity, DetailActivity::class.java
        )
        intent.putExtra("nombre", nombre)
        startActivity(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListaEquiposBinding.inflate(layoutInflater)



        with(binding) {
            setContentView(root)
            val adapter = EquiposAdapter(Imple())

            viewModel.handleEvent(EquiposEvent.GetAll)
            rvEquipos.adapter = adapter

//            val equiposList = viewModel.state.value?.list ?: emptyList()


            viewModel.state.observe(this@EquiposActivity) { state ->
                state.error?.let {
                    Toast.makeText(this@EquiposActivity, it, Toast.LENGTH_SHORT).show()
                }

                state.list.let { adapter.submitList(it) }
            }



        }

    }


}