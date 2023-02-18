package com.example.joaquinfernandezcomposefinal.ui.framework.game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.joaquinfernandezcomposefinal.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameActivity : ComponentActivity(){
    override fun onCreate(instance: Bundle?){
        super.onCreate(instance)
        setContent {
            AppTheme {

            }
        }

    }




}