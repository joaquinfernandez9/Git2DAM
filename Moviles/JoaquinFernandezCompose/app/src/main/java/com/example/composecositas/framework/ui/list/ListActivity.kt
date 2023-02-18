package com.example.composecositas.framework.ui.list

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.example.composecositas.framework.ui.add.AddMovieActivity
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composecositas.domain.model.Movie
import com.example.composecositas.framework.theme.AppTheme
import com.example.composecositas.utils.Constants

@AndroidEntryPoint
class ListActivity : ComponentActivity() {

    override fun onCreate(instance: Bundle?) {
        super.onCreate(instance)
        setContent {
            AppTheme() {
                Scaffold(content = { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        Screen()
                        AddMovieBtn(
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
    fun Screen(viewModel: ListViewModel = hiltViewModel()) {
        val list = viewModel.movies.collectAsState()
        ListView(list =list.value)

    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun ListView(list: List<Movie>, viewModel: ListViewModel? = null) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(list.size) { index ->
                val movie = list[index]
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), elevation = 8.dp, onClick = {
                    viewModel?.handleEvent(ListContract.Event.Delete(movie))
                    viewModel?.handleEvent(ListContract.Event.GetAll)
                }) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = movie.titulo)
                    }


                }
                if (index < list.size - 1) {
                    Divider(color = Color.Black, modifier = Modifier.padding(vertical = 1.dp))
                }
            }
        }
    }

    @Composable
    fun AddMovieBtn(modifier: Modifier = Modifier) {
        val context = LocalContext.current
        FloatingActionButton(modifier = modifier, onClick = {
            context.startActivity(Intent(context, AddMovieActivity::class.java))
        }, elevation = FloatingActionButtonDefaults.elevation(8.dp, 8.dp, 0.dp)) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = Constants.ADD,
            )
        }
    }
}