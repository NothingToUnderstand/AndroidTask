package com.example.androidtask.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.androidtask.MainViewModel

@Composable
fun DetailsScreen(viewModel: MainViewModel, navController: NavController) {
    val item = viewModel.article.observeAsState().value
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 8.dp
            )
    ) {
        LazyColumn {
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = rememberAsyncImagePainter(item?.urlToImage),
                        contentDescription = "Image",
                        modifier = Modifier
                            .size(400.dp)
                    )
                    Text(
                        item?.author ?: "Sorry. no title",
                        fontSize = 15.sp,
                        textAlign = TextAlign.End
                    )
                    Text(
                        item?.title ?: "Sorry. no title",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                    item?.description?.let { Text(it) }

                }
            }
        }
    }
}



