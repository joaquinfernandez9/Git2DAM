package com.example.crudjoaquinfernandez.ui.mainScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.activity.viewModels
import com.example.crudjoaquinfernandez.R
import com.example.crudjoaquinfernandez.data.HeadsetRepository
import com.example.crudjoaquinfernandez.data.HeadsetRoomDataBase
import com.example.crudjoaquinfernandez.databinding.ActivityMainBinding
import com.example.crudjoaquinfernandez.domain.model.Headset
import com.example.crudjoaquinfernandez.domain.usecases.headset.*
import com.example.crudjoaquinfernandez.ui.recycler.ListActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        viewModel.handleEvent(
            MainEvent.GetHeadset(
                intent.getIntExtra(Const.id, 0)
            )
        )

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

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
                    micCheck.isChecked = it.mic == 1
                    bluetooth.isChecked = it.bluetooth == 1
                }

                if (state.stringError == null) {
                    Toast.makeText(this@MainActivity, R.string.correct, Toast.LENGTH_SHORT).show()
                    Timber.i(R.string.correct.toString())
                }

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
                                        models = null,
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
                        models = null
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


        }
    }
}