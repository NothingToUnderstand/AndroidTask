package com.example.androidtask

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.androidtask.navigation.SetupNavHost
import com.example.androidtask.screens.MainScreen
import com.example.androidtask.ui.theme.AndroidTaskTheme
import dagger.hilt.android.AndroidEntryPoint
import io.realm.Realm

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Realm.init(this)
        setContent {
            AndroidTaskTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController= rememberNavController()
                    val viewModel= hiltViewModel<MainViewModel>()

                    SetupNavHost(navController = navController,viewModel= viewModel)
                }
            }
        }
    }
}