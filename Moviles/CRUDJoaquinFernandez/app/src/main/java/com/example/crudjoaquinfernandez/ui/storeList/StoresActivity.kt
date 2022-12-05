package com.example.crudjoaquinfernandez.ui.storeList

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.crudjoaquinfernandez.R
import com.example.crudjoaquinfernandez.databinding.StoreListBinding
import com.example.crudjoaquinfernandez.domain.model.Store
import com.example.crudjoaquinfernandez.ui.mainScreen.MainActivity
import com.example.crudjoaquinfernandez.ui.storeList.storeDetail.DetailActivity
import com.example.crudjoaquinfernandez.ui.storeList.storeDetail.DetailEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoresActivity : AppCompatActivity() {
    private lateinit var binding: StoreListBinding
    private val viewModel: StoresViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        viewModel.handleEvent(StoresEvent.GetAll)
    }

    inner class Imple : Actions {
        override fun onClickDetail(storeName: String) = onDetail(storeName)
        override fun onClickDelete(storeName: String) {
            TODO("Not yet implemented")
        }
    }

    private fun onDetail(name: String) {
        val intent = Intent(
            this@StoresActivity, MainActivity::class.java
        )
        intent.putExtra("name", name)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = StoreListBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)

            viewModel.handleEvent(StoresEvent.GetAll)

            floatingActionButton.setOnClickListener {
                val intent = Intent(this@StoresActivity, DetailActivity::class.java)
                startActivity(intent)
            }



//            val storeList = viewModel.state.value?.list ?: emptyList()

            val adapter = StoresAdapter(Imple())

            rvStores.adapter = adapter

            viewModel.state.observe(this@StoresActivity) { state ->


                state.list.let {
                    adapter.submitList(it)
                }
            }





        }
    }


}