package com.example.crudjoaquinfernandez.ui.recycler

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudjoaquinfernandez.R
import com.example.crudjoaquinfernandez.data.DataHeadset
import com.example.crudjoaquinfernandez.domain.model.Headset
import com.example.crudjoaquinfernandez.domain.usecases.headset.*
import com.example.crudjoaquinfernandez.ui.mainScreen.MainViewModel
import com.example.crudjoaquinfernandez.ui.mainScreen.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

class RecyclerActivity : AppCompatActivity() {
    private fun click(nombre: String) {
        Snackbar.make(
            findViewById<RecyclerView>(R.id.rvHeadsets), nombre, Snackbar.LENGTH_SHORT
        ).show()
    }

    private val viewModel: RecyclerViewModel by viewModels {
        RecyclerViewModelFactory(
            RemoveHeadsetUsecase(),
//            GetAllHeadsetUsecase(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        intent.extras?.let {
            val headset = it.getParcelable<Headset>("headset").toString()
            Timber.i("Headset: $headset")
            Timber.tag("Headset").i("Headset: $headset")
        }

        val headsetList = DataHeadset.getAllHeadsets()


        val rvHeadsets = this.findViewById<RecyclerView>(R.id.rvHeadsets)

        val adapter = HeadsetAdapter(headsetList, this::click,
            object : HeadsetAdapter.Actions {
                override fun onClickDelete(id: Int) {
                    viewModel.deleteHeadset(id)

                }

//                override fun onClickAdd(headset: Headset) {
//                    viewModel.addHeadset(headset)
//                }

            },

        )

        adapter.cambiarLista(headsetList)

        headsetList.let {
            rvHeadsets.adapter = adapter
            rvHeadsets.layoutManager = LinearLayoutManager(this@RecyclerActivity)
        }


    }


}