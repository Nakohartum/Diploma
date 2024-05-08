package com.example.diploma.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.diploma.models.DayOfWeek
import com.example.diploma.models.SubjectData

//@Composable
//fun ScheduleView(navController: NavController,dayTitle: String, list: List<SubjectData>){
//    Column(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(onClick = { /*TODO*/ }) {
//                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
//            }
//
//            Text(
//                fontSize = 25.sp,
//                text = dayTitle
//            )
//
//            IconButton(onClick = { /*TODO*/ }) {
//                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
//            }
//        }
//        Divider()
//        LazyColumn(
//            modifier = Modifier.weight(1f)
//        ){
//            items(daysOfWeek[0].subjects){
//                ItemView(it, 1)
//            }
//        }
//        Button(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 8.dp, end = 8.dp, bottom = 70.dp),
//            onClick = {
//                navController.popBackStack()
//            }
//        ) {
//            Text(text = "Back")
//        }
//    }
//}
//
//@Composable
//fun ItemView(subjectData: SubjectData, number: Int) {
//    Card(
//        modifier = Modifier
//            .wrapContentSize()
//            .padding(8.dp),
//        border = BorderStroke(2.dp, Color.Gray)
//    ) {
//        Row (
//            modifier = Modifier
//                .padding(horizontal = 4.dp)
//                .fillMaxSize()
//                ,
//            horizontalArrangement = Arrangement.SpaceEvenly,
//            verticalAlignment = Alignment.CenterVertically
//        ){
//            Text(text = number.toString())
//            Divider(modifier = Modifier
//                .height(50.dp)
//                .width(4.dp)
//                .border(2.dp, Color.Gray))
//            Text(text = subjectData.subjectName)
//            Text(text = subjectData.teacherData.teacherName)
//        }
//    }
//}

@Preview(showBackground = true)
@Composable
fun ScheduleViewPreview(){

}
