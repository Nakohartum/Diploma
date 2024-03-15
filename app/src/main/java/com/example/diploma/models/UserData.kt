package com.example.diploma.models

import com.example.diploma.R
import java.util.Date

data class UserData(
    val userId: String,
    val userName: String,
    val userSurname: String,
    val userBirthDate: Date,
    val userProfession: String,
    val userCourse: Int,
    val userPicture: Int,
)

object StubObjects{
    val userData = UserData(
        "1", "TestName", "TestSurname", Date(2001,11,24),"TestProfession", 1, R.drawable.stub_image
    )
}
