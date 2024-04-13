package com.aiglepub.marvelcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aiglepub.marvelcompose.ui.MarvelScreen
import com.aiglepub.marvelcompose.ui.navigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelScreen {
                Navigation()
            }
        }
    }
}



