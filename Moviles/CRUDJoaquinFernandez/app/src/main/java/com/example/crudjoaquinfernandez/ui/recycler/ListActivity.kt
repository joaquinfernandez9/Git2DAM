package com.example.crudjoaquinfernandez.ui.recycler

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudjoaquinfernandez.R
import com.example.crudjoaquinfernandez.data.HeadsetRepository
import com.example.crudjoaquinfernandez.data.HeadsetRoomDataBase
import com.example.crudjoaquinfernandez.databinding.ActivityRecyclerBinding
import com.example.crudjoaquinfernandez.domain.usecases.headset.*
import com.example.crudjoaquinfernandez.ui.mainScreen.MainActivity
import timber.log.Timber

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding

    private val viewModel: RecyclerViewModel by viewModels {
        RecyclerViewModelFactory(
            RemoveHeadsetUsecase(
                HeadsetRepository(
                    HeadsetRoomDataBase.getDatabase(this).headsetDao()
                )
            ),
            GetAllUseCase(HeadsetRepository(HeadsetRoomDataBase.getDatabase(this).headsetDao())),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)

            viewModel.handleEvent(
                RecyclerEvent.GetAll
            )

            floatingActionButton.setOnClickListener {
                val intent = Intent(this@ListActivity, MainActivity::class.java)
                startActivity(intent)
            }

            val headsetList = viewModel.state.value?.list ?: emptyList()

            val adapter = HeadsetAdapter(
                headsetList,
                object : HeadsetAdapter.Actions {
                    override fun onClickDelete(id: Int) {
                        viewModel.handleEvent(
                            RecyclerEvent.DeleteHeadset(id)
                        )
                    }

                    override fun onClickDetail(id: Int) {
                        val intent = Intent(
                            this@ListActivity,
                            MainActivity::class.java
                        )
                        intent.putExtra("id", id)
                        startActivity(intent)
                    }
                },
            )

            viewModel.state.observe(this@ListActivity) { state ->
                state.error?.let {
                    Timber.e(it)
                    Toast.makeText(
                        this@ListActivity, it,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                state.list.let {
                    adapter.cambiarLista(it)
                }

//                adapter.cambiarLista(headsetList)
            }

            headsetList.let {
                rvHeadsets.adapter = adapter
                rvHeadsets.layoutManager =
                    LinearLayoutManager(this@ListActivity)
            }
        }


//        onBackPressed()

    }

}