package com.example.diploma.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diploma.R
import com.example.diploma.models.StubObjects
import com.example.diploma.models.UserData
import com.example.diploma.ui.theme.DarkBlue
import com.example.diploma.ui.theme.LightBlue
import com.example.diploma.ui.theme.Orange

@Composable
fun HomeView(userData: UserData){
    Scaffold(
        topBar = {
            TopBar(userData)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBlue)
                .padding(it)
        ){
            ContentView()
        }
    }
}

@Composable
fun TopBar(userData: UserData){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp)
    ) {
        Text(
            text = "Hello ${userData.userName}!",
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

@Composable
fun ContentView() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LastOpenedLesson()
        Spacer(modifier = Modifier.height(16.dp))
        AllLessonsView()
    }
}

@Composable
fun LastOpenedLesson() {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 18.dp, vertical = 18.dp)
            .background(Orange, RoundedCornerShape(8.dp))
    ){
        Row(
            modifier = Modifier.padding(vertical = 36.dp, horizontal = 8.dp)
        ){
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Continue to\nTest Course",
                    fontSize = 16.sp,
                    color = Color.White
                )

                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    progress = 0.5f,
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 5.dp, bottom = 25.dp),
                    text = "50% Complete",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
            Image(
                modifier = Modifier
                    .padding(16.dp)
                    .width(150.dp),
                painter = painterResource(id = R.drawable.stub_image),
                contentDescription = null
            )
        }
    }
}

@Composable
fun AllLessonsView(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.wrapContentSize().padding(horizontal = 18.dp),
            text = "All lessons",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        Text(
            modifier = Modifier.wrapContentSize().padding(horizontal = 18.dp),
            text = "See All",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = LightBlue,
            textAlign = TextAlign.Center,
        )
    }
    LazyHorizontalGrid(GridCells.Fixed(1)){
        items(4){
            LessonView()
        }
    }
}

@Composable
fun LessonView() {

}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview(){
    HomeView(StubObjects.userData)
}