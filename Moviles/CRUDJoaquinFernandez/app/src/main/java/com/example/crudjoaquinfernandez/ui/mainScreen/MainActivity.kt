package com.example.crudjoaquinfernandez.ui.mainScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudjoaquinfernandez.R
import com.example.crudjoaquinfernandez.databinding.ActivityMainBinding
import com.example.crudjoaquinfernandez.domain.model.Headset
import com.example.crudjoaquinfernandez.domain.model.Model
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about -> {
                MaterialAlertDialogBuilder(this@MainActivity)
                    .setMessage(Const.msgMenu)
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    inner class Imple : Actions {
        override fun onClickDelete(id: Int){
            viewModel.handleEvent(
                MainEvent.DeleteModel(id)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        binding = ActivityMainBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)

            viewModel.handleEvent(
                MainEvent.GetHeadset(
                    intent.getIntExtra(Const.id, 0)
                )
            )

            viewModel.handleEvent(
                MainEvent.GetAllModels(
                    intent.getIntExtra(Const.id, 0)
                )
            )

            val modelList = viewModel.uiState.value?.headset?.models

            val adapter = ModelAdapter(Imple())

            viewModel.uiState.observe(this@MainActivity) { state ->

                state.stringError?.let {
                    Timber.e(it)
                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                }

                state.headset?.let {
                    nameText.editText?.text =
                        Editable.Factory.getInstance().newEditable(it.name)
                    idText.editText?.text =
                        Editable.Factory.getInstance().newEditable(it.id.toString())

                    adapter.submitList(it.models)

                }

                if (state.stringError == null) {
                    Toast.makeText(this@MainActivity, R.string.correct, Toast.LENGTH_SHORT).show()
                    Timber.i(R.string.correct.toString())
                }


            }

            modelList.let {
                rvModels?.adapter = adapter
                rvModels?.layoutManager = LinearLayoutManager(this@MainActivity)
            }


            Picasso.get()
                .load(Const.picURL)
                .into(imageView)

            add?.setOnClickListener {
                if (nameText.editText?.text.toString().isNotEmpty() &&
                    idText.editText?.text.toString().isNotEmpty()
                ) {
                    viewModel.handleEvent(
                        MainEvent.AddHeadset(
                            Headset(
                                name = nameText.editText?.text.toString(),
                                id = Integer.parseInt(idText.editText?.text.toString()),
                                mic = if (micCheck.isActivated) 1 else 0,
                                bluetooth = if (bluetooth.isActivated) 1 else 0,
                                models = emptyList(),
                            ),
                        )
                    )
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        R.string.fillAllTheFields,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            update.setOnClickListener {
                if (idText.editText?.text.toString().isNotEmpty()) {
                    val headset = Headset(
                        idText.editText?.text.toString().toInt(),
                        nameText.toString(),
                        mic = if (micCheck.isActivated) 1 else 0,
                        bluetooth = if (bluetooth.isActivated) 1 else 0,
                        models = emptyList(),
                    )
                    viewModel.handleEvent(
                        MainEvent.UpdateHeadset(
                            headset
                        )
                    )
                } else {
                    Toast.makeText(this@MainActivity, R.string.setID, Toast.LENGTH_SHORT).show()
                }
            }

            addModelBtn?.setOnClickListener {
                var idNuevo = idEditText?.text.toString().toInt()
                if (intent.getIntExtra(Const.id, 0) != 0){
                    idNuevo = intent.getIntExtra(Const.id, 0)
                }
                if (tvAddModel?.text.toString().isNotEmpty()) {
                    viewModel.handleEvent(
                        MainEvent.AddModel(
                            Model(
                                modelName = tvAddModel?.text.toString(),
                                idHeadset = idNuevo,
                            )
                        )
                    )
                } else {
                    Toast.makeText(this@MainActivity, R.string.setID, Toast.LENGTH_SHORT).show()
                }
            }


        }
    }
}