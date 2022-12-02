package com.example.androidtask.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androidtask.MainViewModel
import com.example.androidtask.model.Article

@Composable
fun DetailsScreen(viewModel: MainViewModel, id: String) {
    val news = viewModel
        .news
        .observeAsState(listOf())
        .value
    var item: Article? = null
    if (news.isNotEmpty()) {
        item = news[0].articles
            .firstOrNull() {
                it.id.toString() == id
            }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = 24.dp,
                horizontal = 8.dp
            )
    ) {
        item?.author?.let { Text(it) }
        item?.description?.let { Text(it) }
        if (item == null) {
            Text(text = id)
        }
    }
}
