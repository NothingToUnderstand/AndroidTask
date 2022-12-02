package com.example.androidtask.screens

import android.view.animation.BounceInterpolator
import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androidtask.MainViewModel
import com.example.androidtask.R
import com.example.androidtask.ui.theme.AndroidTaskTheme
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController, viewModel: MainViewModel) {
    var startAnimate by remember {
        mutableStateOf(false)
    }
    val alphaAnimation = animateFloatAsState(
        targetValue = if (startAnimate) 1f else 0f,
        animationSpec = tween(durationMillis = 2000)
    )
    LaunchedEffect(key1 = true) {
        startAnimate = true
        viewModel.getNews("ua", 1)
        viewModel.setChangeListener()
    }
    Splash(navController, alphaAnimation.value)
}


@Composable
fun Splash(navController: NavController, alpha: Float) {
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
            Icon(
                painterResource(R.drawable.openbook),
                modifier = Modifier
                    .size(350.dp)
                    .alpha(alpha),
                contentDescription = "",
                tint = Color.Black
            )
        }
        AnimatedVisibility(
            visible = !visible,
            enter = fadeIn(animationSpec = tween(1000)) +
                    expandVertically(
                        animationSpec = tween(1500)
                    )
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxSize()
                    .size(200.dp)
                    .alpha(alpha)
                    .clickable {
                        visible = !visible
//                navController.navigate("main_screen")
                    },
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "",
                tint = Color.Black
            )
        }
    }
}

