package com.example.diploma.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.diploma.models.DaysOfTheWeek
import com.example.diploma.models.Screens
import com.example.diploma.models.SubjectData
import com.example.diploma.models.TeacherData
import com.example.diploma.viewmodels.SubjectViewModel
import com.example.diploma.viewmodels.TeacherViewModel

@Composable
fun ScheduleView(
    navController: NavController,
    subjectViewModel: SubjectViewModel,
    teacherViewModel: TeacherViewModel
){
    val daysOfTheWeek by remember {
        mutableStateOf(DaysOfTheWeek.entries.toTypedArray())
    }

    var index by remember {
        mutableStateOf(0)
    }

    var currentDay by remember {
        mutableStateOf(daysOfTheWeek[index])
    }
    subjectViewModel.getSubjectsByDay(currentDay.id)
    val targetsList by subjectViewModel.schedule.observeAsState(listOf())


        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    if (index <= 0){
                        index = daysOfTheWeek.size - 1
                    }
                    else{
                        index--
                    }
                    currentDay = daysOfTheWeek[index]
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                Text(
                    fontSize = 25.sp,
                    text = currentDay.dayName,
                    color = Color.White
                )

                IconButton(onClick = {
                    if (index < daysOfTheWeek.size - 1){
                        index++
                    }
                    else{
                        index = 0
                    }
                    currentDay = daysOfTheWeek[index]
                 }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
            Divider()
            LazyColumn(
                modifier = Modifier.weight(1f)
            ){
                items(targetsList){
                    val teacherData = teacherViewModel.getTeacher(it.teacherID).observeAsState()
                    if (teacherData.value != null){
                        ItemView(it, teacherData.value!!, navController)
                    }
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, bottom = 70.dp),
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text(text = "Back")
            }
        }

}

@Composable
fun ItemView(subjectData: SubjectData, teacherData: TeacherData, navController: NavController) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .height(80.dp)
            .padding(8.dp)
            .clickable {
                navController.navigate("${Screens.subjectScreen.route}/${subjectData.subjectId}")
            },
        border = BorderStroke(2.dp, Color.Gray)
    ) {

        Row (
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = subjectData.subjectName, modifier = Modifier.padding(start = 16.dp))
            Text(text = teacherData.teacherName, modifier = Modifier.padding(end = 16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleViewPreview(){
    ScheduleView(navController = rememberNavController(), viewModel(), viewModel())
}
