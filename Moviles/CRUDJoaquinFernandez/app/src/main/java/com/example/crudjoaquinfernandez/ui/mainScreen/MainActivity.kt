package com.example.crudjoaquinfernandez.ui.mainScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.crudjoaquinfernandez.R
import com.example.crudjoaquinfernandez.databinding.ActivityMainBinding
import com.example.crudjoaquinfernandez.domain.model.Headset
import com.example.crudjoaquinfernandez.domain.usecases.headset.AddHeadsetUsecase
import com.example.crudjoaquinfernandez.domain.usecases.headset.GetHeadsetUsecase
import com.example.crudjoaquinfernandez.domain.usecases.headset.RemoveHeadsetUsecase
import com.example.crudjoaquinfernandez.utils.StringProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //llamar viewmodel
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            StringProvider.instance(this),
            AddHeadsetUsecase(),
            GetHeadsetUsecase(),
            RemoveHeadsetUsecase(),
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)


            addBtn.setOnClickListener {
                if (nameHeadset.tag.toString().isBlank()) {
                    Toast.makeText(this@MainActivity, "Rellena los campos", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.addHeadset(
                        Headset(
                            name = nameHeadset.tag.toString(),
                            id = (idHeadset as String).toInt(),
                            bluetooth = bluetooth.isChecked,
                            mic = mic.isChecked,
                        ))
                }

            }
        }


    }
}