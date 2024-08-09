package com.itssagnikmukherjee.studysimulationapp.screens

import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import com.itssagnikmukherjee.studysimulationapp.ui.theme.MyGreen
import com.itssagnikmukherjee.studysimulationapp.ui.theme.outfit

@Composable
fun HomeScreen() {
    Column{
        TopSec()
        StudyGrid(getSubs())
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp).offset(y=40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val context = LocalContext.current
                AndroidView(
                    factory = {
                        WebView(context).apply {
                            settings.apply {
                                javaScriptEnabled = true
                                domStorageEnabled = true
                                setSupportMultipleWindows(true)
                                javaScriptCanOpenWindowsAutomatically = true
                                cacheMode = WebSettings.LOAD_DEFAULT
                            }
                            clearCache(true)
                            loadUrl("https://phet.colorado.edu/sims/html/circuit-construction-kit-ac/latest/circuit-construction-kit-ac_en.html")
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
        }
    }
}

@Composable
fun TopSec() {
    var inTxt by remember { mutableStateOf("") }
    Box(modifier = Modifier
        .fillMaxHeight(0.3f)
        .fillMaxWidth()
        .clip(RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp))){
        Column(
            modifier = Modifier
                .background(MyGreen)
                .fillMaxSize()
                .padding(20.dp, 40.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
        Text(text = "How is the josh !", Modifier.offset(y=30.dp), color = Color.White, fontFamily = outfit, fontWeight = FontWeight.Medium)
        Text(text = "Sagnik Mukherjee", fontSize = 32.sp, color = Color.White, fontFamily = outfit, fontWeight = FontWeight.Medium)
        OutlinedTextField(value = inTxt, onValueChange = {inTxt = it}, placeholder = { Text(text = "What are you looking for", fontFamily = outfit, fontWeight = FontWeight.Medium, color = Color.Gray)},
            shape = RoundedCornerShape(30.dp),
            leadingIcon = {Icon(imageVector = Icons.Default.Search, contentDescription = "Search")},
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedLeadingIconColor = Color.Gray,
                unfocusedLeadingIconColor = Color.Gray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp)
            )
        }
    }
}

@Composable
fun StudyGrid(subs: List<Sub>) {
    LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.padding(20.dp)) {
        items(subs.size) { sub ->
            StudyCard(subs[sub])
        }
    }
}

@Composable
fun StudyCard(sub: Sub) {
    Card(onClick = { /*TODO*/ }, modifier = Modifier.padding(5.dp)) {
        Column(
            modifier = Modifier
                .height(110.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Icon(imageVector = sub.icon, contentDescription = "")
            Text(text = sub.title)
        }
    }
}

data class Sub(
    val icon: ImageVector,
    val title: String
)

fun getSubs(): List<Sub>{
    return listOf(
        Sub(icon = Icons.Default.Search, title = "Phy"),
        Sub(icon = Icons.Default.Search, title = "Chem"),
        Sub(icon = Icons.Default.Search, title = "Maths"),
        Sub(icon = Icons.Default.Search, title = "Bio"),
        Sub(icon = Icons.Default.Search, title = "CS"),
        Sub(icon = Icons.Default.Search, title = "Others")
    )
}