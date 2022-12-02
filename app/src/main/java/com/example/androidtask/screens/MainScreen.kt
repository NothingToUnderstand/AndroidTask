package com.example.androidtask.screens

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.androidtask.MainViewModel
import com.example.androidtask.model.Article
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavHostController) {
    val news = viewModel.news.observeAsState(listOf()).value
    val swipeRefreshState = rememberSwipeRefreshState(news != news)
    if (news.isEmpty()) {
        Surface {
            Text(text = "Please wait")
        }
        return
    }
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.DarkGray,
                title = {
                    Text(
                        text = news[0].date,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )

                }
            )
        }
    ) { padding ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.getNews("ua", 1) },
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = 32.dp,
                    horizontal = 16.dp
                )
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(vertical = 25.dp)
                            .background(color = Color.Gray),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "\uD83D\uDCF0  News Today",
                            style = MaterialTheme.typography.h3,
                            color = Color.White
                        )
                    }
                }
                items(news[0].articles) {
                    NewsItem(it, viewModel, navController)
                }
            }
        }
    }
}

@Composable
fun NewsItem(it: Article, viewModel: MainViewModel, navController: NavHostController) {
    Card(
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 40.dp,
            bottomEnd = 0.dp,
            bottomStart = 40.dp,
        ),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                viewModel.article.postValue(it)
                navController.navigate("details_screen")
            },
        elevation = 5.dp,
        backgroundColor = Color.LightGray
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = rememberAsyncImagePainter(it.urlToImage),
                contentDescription = null,
                modifier = Modifier
                    .size(130.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
            )
            Column(
                Modifier
                    .padding(8.dp)
            ) {
                it.title?.let { it1 ->
                    Text(
                        text = it1,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSurface,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

        }
    }
}

