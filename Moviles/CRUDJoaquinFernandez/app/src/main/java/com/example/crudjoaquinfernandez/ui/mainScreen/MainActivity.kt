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
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var temp: Int = 0

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            AddHeadsetUsecase(HeadsetRepository(HeadsetRoomDataBase.getDatabase(this).headsetDao())),
            GetHeadsetUsecase(HeadsetRepository(HeadsetRoomDataBase.getDatabase(this).headsetDao())),
            UpdateHeadsetUseCase(HeadsetRepository(HeadsetRoomDataBase.getDatabase(this).headsetDao())),
            RemoveHeadsetUsecase(HeadsetRepository(HeadsetRoomDataBase.getDatabase(this).headsetDao())),
            GetAllUseCase(HeadsetRepository(HeadsetRoomDataBase.getDatabase(this).headsetDao())),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        temp = 0



        viewModel.handleEvent(
            MainEvent.GetHeadset(
                intent.getIntExtra("id", 0)
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
                    nameText.editText?.text = Editable.Factory.getInstance().newEditable(it.name)
                    idText.editText?.text =
                        Editable.Factory.getInstance().newEditable(it.id.toString())
                    micCheck.isChecked = it.mic
                    bluetooth.isChecked = it.bluetooth
                }
            }



            Picasso.get()
                .load("https://images.ecestaticos.com/FPDLmoNTTTzZeuuEMqvGze2X41A=/0x0:1183x665/1200x675/filters:fill(white):format(jpg)/f.elconfidencial.com%2Foriginal%2F5e5%2F157%2F764%2F5e5157764ad17f8e7ae6ead0618c5fdb.jpg")
                .into(imageView)

            viewModel.uiState.observe(this@MainActivity) { state ->
                state.stringError?.let { error ->
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                    viewModel.showError()
                }
            }

            viewModel.uiState.observe(this@MainActivity) { state ->
                state.stringError?.let { error ->
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                    viewModel.showError()
                }
                if (state.stringError == null) {
                    Toast.makeText(this@MainActivity, R.string.correct, Toast.LENGTH_SHORT).show()
                    Timber.i(R.string.correct.toString())
                }
            }

            add?.setOnClickListener {
                if (nameText.editText?.text.toString().isNotEmpty() &&
                    idText.editText?.text.toString().isNotEmpty()
                ) {
                    viewModel.handleEvent(
                        MainEvent.AddHeadset(
                            Headset(
                                name = nameText.editText?.text.toString(),
                                id = Integer.parseInt(idText.editText?.text.toString()),
                                mic = micCheck.isActivated,
                                bluetooth = bluetooth.isActivated,
                            )
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
            remove?.setOnClickListener {
                if (idSearching.editText?.text.toString().isNotEmpty()) {
                    MaterialAlertDialogBuilder(this@MainActivity)
                        .setTitle(R.string.deleteActivity)
                        .setMessage(R.string.deleteQuestion)
                        .setPositiveButton(R.string.deleteActivity) { _, _ ->
                            viewModel.handleEvent(
                                MainEvent.RemoveHeadset(
                                    idSearching.editText?.text.toString().toInt()
                                )
                            )

                        }
                        .show()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        R.string.fillAllTheFields,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            search.setOnClickListener {
                if (idSearching.editText?.text.toString().isNotEmpty()) {
                    val headset = viewModel.uiState.value?.headset

                    if (headset != null) {
                        nameText.editText?.setText(headset.name)
                        idText.editText?.setText(headset.id.toString())
                        micCheck.isActivated = headset.mic
                        bluetooth.isActivated = headset.bluetooth
                    } else {
                        Toast.makeText(this@MainActivity, R.string.notFound, Toast.LENGTH_SHORT)
                            .show()
                        Timber.i(R.string.notFound.toString())
                    }
                } else {
                    Toast.makeText(this@MainActivity, R.string.setID, Toast.LENGTH_SHORT).show()
                    Timber.i(R.string.idNotSet.toString())
                }
            }

            update.setOnClickListener {
                if (idText.editText?.text.toString().isNotEmpty()) {
                    val headset = Headset(
                        idText.editText?.text.toString().toInt(),
                        nameText.toString(),
                        micCheck.isActivated,
                        bluetooth.isActivated,
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


            changeScreen?.setOnClickListener {
                val intent = Intent(this@MainActivity, ListActivity::class.java)
                startActivity(intent)
            }


        }
    }
}