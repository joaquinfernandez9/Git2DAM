package com.example.crudjoaquinfernandez.ui.storeList.storeDetail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.crudjoaquinfernandez.databinding.StoreDetailBinding
import com.example.crudjoaquinfernandez.domain.model.Store
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity(){
    private lateinit var binding: StoreDetailBinding

    private val viewModel: DetailViewModel by viewModels()

    inner class Imple : Actions {
        override fun onClickDelete(id: Int) {
            viewModel.handleEvent(
                DetailEvent.DeleteHeadset(id)
            )
        }

        override fun onClickDetail(id: Int) {
            TODO("Not yet implemented")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = StoreDetailBinding.inflate(layoutInflater)

        with(binding){
            setContentView(root)

            addStoreBtn.setOnClickListener {
                viewModel.handleEvent(
                    DetailEvent.AddStore(
                        Store(
                            name = nameStore.editText?.text.toString(),
                            headsets = emptyList()
                        )
                    )
                )
            }

//            val headsetList =

            val adapter = HeadsetAdapter(Imple())

            viewModel.uiState.observe(this@DetailActivity) { state ->
                state.error.let {
                    Toast.makeText(this@DetailActivity, it, Toast.LENGTH_SHORT).show()
                }

                state.store.let {
                    nameStore.editText?.setText(it.name)
                    adapter.submitList(it.headsets)
                }
            }




        }

    }




}