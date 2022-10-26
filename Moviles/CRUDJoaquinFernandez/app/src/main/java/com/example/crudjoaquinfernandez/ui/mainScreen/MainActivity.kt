package com.example.crudjoaquinfernandez.ui.mainScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.activity.viewModels
import com.example.crudjoaquinfernandez.R
import com.example.crudjoaquinfernandez.databinding.ActivityMainBinding
import com.example.crudjoaquinfernandez.domain.model.Headset
import com.example.crudjoaquinfernandez.domain.usecases.headset.*
import com.example.crudjoaquinfernandez.ui.recycler.RecyclerActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var temp: Int = 0

    //llamar viewmodel
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            AddHeadsetUsecase(),
            GetHeadsetUsecase(),
            UpdateHeadsetUseCase(),
            RemoveHeadsetUsecase(),
            GetAllUseCase(),
        )
    }

    //aaaaaaaaa
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        temp = 0


        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)


            viewModel.uiState.observe(this@MainActivity) { state ->
                state.stringError?.let { error ->
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                    viewModel.showError()
                }
            }

            //mover al viewmodel
            var index = 0
            goRight.setOnClickListener {
                if (index < viewModel.getAll().size - 1) {
                    index++
                    val headset = viewModel.getAll()[index]
                    nameText.editText?.text =
                        Editable.Factory.getInstance().newEditable(headset.name)
                    idText.editText?.text =
                        Editable.Factory.getInstance().newEditable(headset.id.toString())
                    micCheck.isChecked = headset.mic
                    bluetooth.isChecked = headset.bluetooth
                } else {
                    Toast.makeText(this@MainActivity, "No hay más elementos", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            goLeft.setOnClickListener {
                if (index > 0) {
                    index--
                    val headset = viewModel.getAll()[index]
                    nameText.editText?.text =
                        Editable.Factory.getInstance().newEditable(headset.name)
                    idText.editText?.text =
                        Editable.Factory.getInstance().newEditable(headset.id.toString())
                    micCheck.isChecked = headset.mic
                    bluetooth.isChecked = headset.bluetooth
                } else {
                    Toast.makeText(this@MainActivity, "No hay más elementos", Toast.LENGTH_SHORT)
                        .show()
                }
            }



            add?.setOnClickListener {
                if (nameText.editText?.text.toString().isNotEmpty() &&
                    idText.editText?.text.toString().isNotEmpty()
                ) {
                    viewModel.addHeadset(
                        Headset(
                            name = nameText.editText?.text.toString(),
                            id = Integer.parseInt(idText.editText?.text.toString()),
                            mic = micCheck.isActivated,
                            bluetooth = bluetooth.isActivated,
                        )
                    )
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Rellena todos los campos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            remove?.setOnClickListener {
                if (idSearching.editText?.text.toString().isNotEmpty()) {
                    MaterialAlertDialogBuilder(this@MainActivity)
                        .setTitle("Eliminar")
                        .setMessage("Seguro que quieres borrarlo?")
                        .setNegativeButton("Cancelar") { _, _ ->
                            // Respond to negative button press
                        }
                        .setPositiveButton("Borrar") { _, _ ->
                            viewModel.removeHeadset(idSearching.editText?.text.toString().toInt())
                        }
                        .show()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Rellena todos los campos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            search.setOnClickListener {
                if (idSearching.editText?.text.toString().isNotEmpty()) {
                    val hola = viewModel.get(idSearching.editText?.text.toString().toInt())

                    nameText.editText?.setText(hola.name)
                    idText.editText?.setText(hola.id.toString())
                    micCheck.isActivated = hola.mic
                    bluetooth.isActivated = hola.bluetooth
                } else {
                    Toast.makeText(this@MainActivity, "Introduce un id", Toast.LENGTH_SHORT).show()
                }
            }




            update.setOnClickListener {
                if (idText.editText?.text.toString().isNotEmpty()) {
                    viewModel.updateHeadset(
                        idText.editText?.text.toString().toInt(),
                        nameText.toString(),
                        micCheck.isActivated,
                        bluetooth.isActivated,
                    )
                } else {
                    Toast.makeText(this@MainActivity, "Introduce un id", Toast.LENGTH_SHORT).show()
                }
            }


            changeScreen?.setOnClickListener {
                val intent = Intent(this@MainActivity, RecyclerActivity::class.java)
                intent.putExtra(getString(R.string.headset), viewModel.getAll().hashCode())
                startActivity(intent)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) { // Here You have to save count value
        super.onSaveInstanceState(outState)
        Timber.i("onSaveInstanceState")

        outState.putInt("COUNT_KEY", temp)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) { // Here You have to restore count value
        super.onRestoreInstanceState(savedInstanceState)
        Timber.tag("::MyTag").i("onRestoreInstanceState")
        // Log.i("::MyTag", "onRestoreInstanceState")

        temp = savedInstanceState.getInt("COUNT_KEY")
    }

}