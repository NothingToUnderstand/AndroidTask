package com.example.androidtask.screens

import android.content.Intent
import com.example.androidtask.R
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.androidtask.MainViewModel

@Composable
fun DetailsScreen(viewModel: MainViewModel, navController: NavController) {
    val item = viewModel.article.observeAsState().value
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item?.url ?: "http://www.google.com"))
    val context = LocalContext.current


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
                        item?.title ?: "Sorry. no title",
                        fontWeight = Bold,
                        fontSize = 20.sp,
                    )
                    item?.description?.let { Text(it) }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Button(
                        onClick = {
                        startActivity(context, intent, null)
                    },
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                        border = BorderStroke(2.dp, Color.Black),


                    ) {
                        Image(
                            painterResource(id=R.drawable.img),
                            contentDescription ="Cart button icon",
                            modifier = Modifier.size(20.dp))
                        Text(text = "See it on site",
                            color = Color.White
                        )
                    }
                    Text(
                        item?.author ?: "Sorry. no title",
                        fontSize = 10.sp,
                        textAlign = TextAlign.Right,
                        fontWeight = Bold,

                        )

                }
            }
        }
    }
}



