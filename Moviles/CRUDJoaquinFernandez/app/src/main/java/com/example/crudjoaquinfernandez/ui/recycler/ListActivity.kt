package com.example.crudjoaquinfernandez.ui.recycler

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudjoaquinfernandez.R
import com.example.crudjoaquinfernandez.databinding.ActivityRecyclerBinding
import com.example.crudjoaquinfernandez.ui.mainScreen.Const
import com.example.crudjoaquinfernandez.ui.mainScreen.MainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding

    private val viewModel: RecyclerViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        viewModel.handleEvent(RecyclerEvent.GetAll)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about -> {
                MaterialAlertDialogBuilder(this@ListActivity)
                    .setMessage("app_info.txt")
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    inner class Imple : Actions {
        override fun onClickDelete(id: Int) {
            viewModel.handleEvent(
                RecyclerEvent.DeleteHeadset(id)
            )
        }

        override fun onClickDetail(id: Int) {
            val intent = Intent(
                this@ListActivity, MainActivity::class.java
            )
            intent.putExtra(Const.id, id)
            startActivity(intent)
        }
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

            val adapter = HeadsetAdapter(Imple())

            viewModel.state.observe(this@ListActivity) { state ->
                state.error?.let {
                    Timber.e(it)
                    Toast.makeText(
                        this@ListActivity, it, Toast.LENGTH_SHORT
                    ).show()
                }
                state.list.let {
                    adapter.submitList(it)
                }
            }

            headsetList.let {
                rvHeadsets.adapter = adapter
                rvHeadsets.layoutManager = LinearLayoutManager(this@ListActivity)
            }
        }


    }

}