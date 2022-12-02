package com.example.androidtask.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.androidtask.MainViewModel
import com.example.androidtask.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SplashScreen(navController: NavController, viewModel: MainViewModel) {
    viewModel.getNews("ua", 1)
    viewModel.setChangeListener()
    Splash(navController)
}


@Composable
fun Splash(navController: NavController) {
    var visible by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(animationSpec = tween(2000))
                    + fadeIn(animationSpec = tween(2000)),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome",
                    style = TextStyle(
                        fontSize = 50.sp,
                        color = Color.Black,
                        fontStyle = FontStyle.Italic,
                        shadow = Shadow(
                            color = Color.Gray,
                            offset = Offset(20.0f, 20.0f),
                            blurRadius = 10f
                        )
                    )
                )
                Icon(
                    painterResource(R.drawable.openbook),
                    modifier = Modifier
                        .size(350.dp)
                        .alpha(1f),
                    contentDescription = "",
                    tint = Color.Black
                )
            }
        }
        AnimatedVisibility(
            visible = !visible,
            enter = fadeIn(animationSpec = tween(1000)) +
                    expandVertically(
                        animationSpec = tween(1500)
                    )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(50.dp))
                Text(
                    text = "News App",
                    style = TextStyle(
                        fontSize = 50.sp,
                        color = Color.Black,
                        fontStyle = FontStyle.Italic,
                        shadow = Shadow(
                            color = Color.Gray,
                            offset = Offset(20.0f, 20.0f),
                            blurRadius = 10f
                        )
                    )
                )
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .size(200.dp)
                        .alpha(1f)
                        .clickable {
                            visible = !visible
                            CoroutineScope(Dispatchers.Main).launch {
                                delay(2000)
                                navController.navigate("main_screen")
                            }
                        },
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "",
                    tint = Color.Black
                )
            }
        }
    }
}

