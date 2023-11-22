package com.example.kotlinkingdomking.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.kotlinkingdomking.R
import com.example.kotlinkingdomking.tp1.PictureData
import com.example.kotlinkingdomking.tp1.pictureList
import com.example.kotlinkingdomking.ui.theme.KotlinKingdomKingTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(12.dp)) {
        SearchBar()
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), content = {
            items(pictureList.size) { index ->
                PictureRowItem(index +1, pictureList[index])
            }
        })
    }
    ActionBtns(modifier = Modifier.fillMaxWidth())
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PictureRowItem(index: Int, data: PictureData) {

    var opened by remember { mutableStateOf(false) }

    Row(modifier = Modifier
        .defaultMinSize(minHeight = 100.dp)
        .background(Color.White)
        .animateContentSize()
        .clickable { opened = !opened }) {
        GlideImage(
            model = data.url,
            contentDescription = data.text,
            contentScale = ContentScale.FillWidth,
            failure = placeholder(R.drawable.ic_launcher_background),
            modifier = Modifier.heightIn(max = 100.dp).widthIn(max = 100.dp)
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = index.toString() + " - " + data.text, fontWeight = FontWeight.Bold)
            Text(text = data.longText, maxLines = if (opened) Int.MAX_VALUE else 2, fontSize = 14.sp, lineHeight = 16.sp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = {newValue ->  text = newValue},
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
        label = { Text(text = "Rechercher") },
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun ActionBtns(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Clear Filters")
        }
        Spacer(modifier = Modifier.widthIn(12.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Fetch Data")
        }
    }
}