package com.example.diploma.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diploma.R
import com.example.diploma.models.StubObjects
import com.example.diploma.models.UserData
import com.example.diploma.ui.theme.DarkOrange
import com.example.diploma.ui.theme.Orange
import com.example.diploma.ui.theme.Pink
import com.example.diploma.ui.theme.Purple


@Composable
fun AccountView(userData: UserData){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AccountTopView(userData)
            Divider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.Yellow,
                thickness = 2.dp
            )
            AccountDataView(userData)
        }
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 220.dp)
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
                Icon(
                    painter = painterResource(id = R.drawable.icons8_back_arrow_96___),
                    contentDescription = null,
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 80.dp),
                    text = "Back to Home",
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun AccountTopView(userData: UserData){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    0.0f to DarkOrange,
                    0.8f to Orange
                )
            )
            .padding(48.dp),
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = Modifier
                .width(150.dp)
                .clip(CircleShape),
            painter = painterResource(id = userData.userPicture),
            contentDescription = userData.userName
        )
    }
}

@Composable
fun AccountDataView(userData: UserData){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 32.dp),
    ) {
        Text(
            text = "Account Info",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )
        DataView(
            title = "Name",
            icon = R.drawable.icons8_person_96,
            data = userData.userName
        )
        DataView(
            title = "Surname",
            icon = R.drawable.icons8_person_96__1_,
            data = userData.userSurname
        )
        DataView(
            title = "D.O.B",
            icon = R.drawable.icons8_pay_date_96,
            data =
            "${userData.userBirthDate.date}/" +
                    "${userData.userBirthDate.month}/" +
                    "${userData.userBirthDate.year}"
        )
        DataView(
            title = "Profession",
            icon = R.drawable.icons8_new_job_96,
            data = userData.userProfession
        )
        DataView(
            title = "Course",
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
                fontWeight = FontWeight.Bold
            )
            Text(
                text = data,
                fontSize = 10.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview(){
    AccountView(StubObjects.userData)
}