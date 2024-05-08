package com.example.diploma.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.diploma.models.Screens
import com.example.diploma.models.UserData
import com.example.diploma.ui.theme.Orange
import com.example.diploma.ui.theme.Pink
import com.example.diploma.ui.theme.Purple
import com.example.diploma.viewmodels.TeacherViewModel
import com.example.diploma.viewmodels.UserViewModel
import com.example.diploma.viewmodels.Utils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationView(
    viewModel: UserViewModel,
    teacherViewModel: TeacherViewModel,
    navController: NavController
){
    val context = LocalContext.current

    var datePickerState = rememberDatePickerState()

    var pickupOpened by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Orange),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome\nRegistration",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            modifier = Modifier.padding(vertical = 4.dp),
            value = viewModel.userName,
            onValueChange = {viewModel.onUserNameChanged(it)},
            label = {Text(text = "Name")},
        )

        OutlinedTextField(
            modifier = Modifier.padding(vertical = 4.dp),
            value = viewModel.userSurname,
            onValueChange = {viewModel.onUserSurnameChanged(it)},
            label = {Text(text = "Surname")},
        )

        OutlinedTextField(
            modifier = Modifier.padding(vertical = 4.dp),
            value = viewModel.userProfession,
            onValueChange = {viewModel.onUserProfessionChanged(it)},
            label = {Text(text = "Profession")},
        )

        OutlinedTextField(
            modifier = Modifier.padding(vertical = 4.dp),
            value = viewModel.userCourse.toString(),
            onValueChange = {viewModel.onUserCourseChanged(it.toIntOrNull()?:0)},
            label = {Text(text = "Course")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            ImagePicker(onImagePicked = {
                val imageData = Utils.uriToByteArray(context, it)
                viewModel.onUserPictureChanged(imageData!!)
            })
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(
                    Brush.horizontalGradient(
                        0.0f to Purple,
                        0.9f to Pink
                    ), shape = RoundedCornerShape(20.dp)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            onClick = {
                val userData = UserData(
                    userName = viewModel.userName,
                    userProfession = viewModel.userProfession,
                    userSurname = viewModel.userSurname,
                    userCourse = viewModel.userCourse,
                    userPicture = viewModel.userPicture
                )
                viewModel.addUser(userData)
                navController.navigate(Screens.chooseScreen.route)
            },
        ) {
            Text(
                text = "Register",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@Composable
fun EnterView(
    controller: NavController,
    userViewModel: UserViewModel
){
    val users = userViewModel.getAllUsers().observeAsState(initial = listOf())
    Column(modifier = Modifier.fillMaxSize()) {
        Row {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Accounts",
                color = Color.White
            )

            Button(onClick = { controller.navigate(Screens.registrationScreen.route) }) {
                Text(text = "Add")
            }
        }
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp)){
            items(users.value){
                AccountCard(it, userViewModel, controller)
            }
        }
    }
}

@Composable
fun AccountCard(
    userData: UserData,
    userViewModel: UserViewModel,
    controller: NavController
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .background(Color.Cyan, RoundedCornerShape(8.dp))
        .clip(
            RoundedCornerShape(8.dp)
        )
        .clickable {
            userViewModel.provideUser(userName = userData.userName!!)
            controller.navigate(Screens.homeScreen.route)
        }
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Text(text = userData.userName!!)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationViewPreview(){
    EnterView(controller = rememberNavController(), userViewModel = UserViewModel())
}