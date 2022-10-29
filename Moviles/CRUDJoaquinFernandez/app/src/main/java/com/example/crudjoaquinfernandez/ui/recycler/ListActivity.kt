package com.example.crudjoaquinfernandez.ui.recycler

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudjoaquinfernandez.R
import com.example.crudjoaquinfernandez.domain.model.Headset
import com.example.crudjoaquinfernandez.domain.usecases.headset.*
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

class ListActivity : AppCompatActivity() {
    private fun click(nombre: String) {
        Snackbar.make(
            findViewById<RecyclerView>(R.id.rvHeadsets), nombre, Snackbar.LENGTH_SHORT
        ).show()
    }

    private val viewModel: RecyclerViewModel by viewModels {
        RecyclerViewModelFactory(
            RemoveHeadsetUsecase(),
            GetAllUseCase(),
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        intent.extras?.let {
            val headset = it.getParcelable<Headset>("headset").toString()
            Timber.i("Headset: $headset")
            Timber.tag("Headset").i("Headset: $headset")
        }

        val headsetList = viewModel.state.value?.list ?: emptyList()


        val rvHeadsets = this.findViewById<RecyclerView>(R.id.rvHeadsets)

        val adapter = HeadsetAdapter(
            headsetList, this::click,
            object : HeadsetAdapter.Actions {
                override fun onClickDelete(id: Int) {
                    viewModel.deleteHeadset(id)
                }
            },
        )


        viewModel.state.observe(this) { state ->
            state.error?.let {
                Timber.e(it)
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
            adapter.cambiarLista(headsetList)
        }



        headsetList.let {
            rvHeadsets.adapter = adapter
            rvHeadsets.layoutManager = LinearLayoutManager(this@ListActivity)
        }



//        BottomNavigationView.OnNavigationItemSelectedListener { item ->
//            when(item.itemId) {
//                R.id.home -> {
//                    val intent = Intent(this@RecyclerActivity, MainActivity::class.java)
//                    intent.putExtra(getString(R.string.headset), viewModel.().hashCode())
//                    startActivity(intent)
//
//                    true
//                }
//                R.id.list -> {
//
//                    true
//                }
//                else -> false
//            }
//        }


    }


}