package com.example.diploma.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.diploma.R
import com.example.diploma.models.DaysOfTheWeek
import com.example.diploma.models.Screens
import com.example.diploma.models.UserData
import com.example.diploma.ui.theme.DarkOrange
import com.example.diploma.ui.theme.Orange
import com.example.diploma.ui.theme.Pink
import com.example.diploma.ui.theme.Purple
import com.example.diploma.viewmodels.Utils


@Composable
fun AccountView(
    userData: UserData,
    controller: NavController
){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AccountTopView(userData, controller)
            Divider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.Yellow,
                thickness = 2.dp
            )
            AccountDataView(userData)
            Button(onClick = {
                controller.navigate(Screens.scheduleScreen.route)
            }) {
                Text(text = stringResource(id = R.string.schedule))
            }
        }
        Button(
            onClick = {
                      controller.navigate(Screens.chooseScreen.route)
            },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 190.dp)
                .background(
                    Brush.horizontalGradient(
                        0.0f to Purple,
                        0.9f to Pink
                    ), shape = RoundedCornerShape(20.dp)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(start = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 80.dp),
                    text = stringResource(id = R.string.back_to_accounts),
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun AccountTopView(userData: UserData, navController: NavController) {
    val context = LocalContext.current
    Column {
        var expanded by remember {
            mutableStateOf(false)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        0.0f to DarkOrange,
                        0.8f to Orange
                    )
                )
                .height(220.dp),

        ){
            Button(onClick = { expanded = true }, modifier = Modifier.align(Alignment.TopEnd)) {
                Text(text = Utils.loadLanguage(context))
                DropdownMenu(expanded = expanded, onDismissRequest = {
                    expanded = false
                }) {

                    DropdownMenuItem(
                        text = { Text(text = "Русский") },
                        onClick = {
                            Utils.setLocale(context, "ru")
                            expanded = false
                            navController.navigate(Screens.chooseScreen.route)
                        })
                    DropdownMenuItem(
                        text = { Text(text = "English") },
                        onClick = {
                            Utils.setLocale(context, "en")
                            expanded = false
                            navController.navigate(Screens.chooseScreen.route)
                        })
                }

            }
            if (userData.userPicture != null){
                DisplayImage(modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .align(Alignment.Center), image = userData.userPicture)
            }
        }
    }
}
@Composable
fun AccountDataView(userData: UserData){
    Column(
        modifier = Modifier

            .padding(start = 16.dp, top = 16.dp),
    ) {
        Text(
            text = stringResource(id = R.string.account_info),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            color = Color.White
        )
        DataView(
            title = stringResource(id = R.string.account_name),
            icon = R.drawable.icons8_person_96,
            data = userData.userName!!
        )
        DataView(
            title = stringResource(id = R.string.account_surname),
            icon = R.drawable.icons8_person_96__1_,
            data = userData.userSurname!!
        )
        DataView(
            title = stringResource(id = R.string.account_profession),
            icon = R.drawable.icons8_new_job_96,
            data = userData.userProfession
        )
        DataView(
            title = stringResource(id = R.string.account_course),
            icon = R.drawable.icons8_university_96,
            data = userData.userCourse.toString()
        )
    }
}

@Composable
fun DataView(
    title: String, icon: Int, data: String
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(id = icon),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .padding(4.dp)
                .wrapContentSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = data,
                fontSize = 10.sp,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview(){
    AccountView(userData = UserData(1, "1","1","1",1,"sds"), controller = rememberNavController())
}