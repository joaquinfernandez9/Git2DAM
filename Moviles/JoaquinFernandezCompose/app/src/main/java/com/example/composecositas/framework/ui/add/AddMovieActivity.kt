package com.example.composecositas.framework.ui.add

import android.content.Intent
import androidx.compose.material3.Text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composecositas.domain.model.Movie
import com.example.composecositas.framework.theme.AppTheme
import com.example.composecositas.framework.ui.list.ListActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddMovieActivity : ComponentActivity() {

    override fun onCreate(instance: Bundle?) {
        super.onCreate(instance)
        setContent {
            AppTheme {
                Scaffold(content = { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        Screen()
                        MyFloatingActionButton(
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.BottomEnd)
                        )
                    }
                })
            }
        }
    }



    @Composable
    fun Screen(viewModel: AddViewModel = hiltViewModel()) {
        var text by remember {
            mutableStateOf("")
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            ) {

                OutlinedTextField(value = text, onValueChange = { text = it },
                shape = RoundedCornerShape(16.dp), placeholder = { Text(text = "Title") }, singleLine = true,
                )

//                var text by rememberSaveable { mutableStateOf("") }
//                TextField(value = text, onValueChange = { text = it }, label = { Text("Label") })


                FloatingActionButton(
                    onClick = {
                        viewModel.handleEvent(AddContract.Event.Add(movie = Movie(text)))
                    },
                    modifier = Modifier.align(Alignment.End),
                    backgroundColor = Color.Cyan,
                    contentColor = Color.Black,
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
                }
            }

        }
    }


    @Composable
    fun MyFloatingActionButton(
        modifier: Modifier = Modifier,
    ) {
        val context = LocalContext.current
        FloatingActionButton(modifier = modifier, onClick = {
            context.startActivity(Intent(context, ListActivity::class.java))
        }, elevation = FloatingActionButtonDefaults.elevation(8.dp, 8.dp, 0.dp)) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "Add",
            )
        }

    }


}