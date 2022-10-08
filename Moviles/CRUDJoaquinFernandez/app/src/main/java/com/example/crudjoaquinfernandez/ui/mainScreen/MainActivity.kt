package com.example.crudjoaquinfernandez.ui.mainScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.activity.viewModels
import com.example.crudjoaquinfernandez.databinding.ActivityMainBinding
import com.example.crudjoaquinfernandez.domain.model.Headset
import com.example.crudjoaquinfernandez.domain.usecases.headset.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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



        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)


            viewModel.uiState.observe(this@MainActivity) { state ->
                state.stringError?.let { error ->
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                    viewModel.showError()
                }
            }

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


        }


    }
}