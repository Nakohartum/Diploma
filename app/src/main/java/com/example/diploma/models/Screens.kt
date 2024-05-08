package com.example.diploma.models

import androidx.compose.runtime.Composable
import com.example.diploma.R
import com.example.diploma.view.AccountView
import com.example.diploma.view.HomeView
import com.example.diploma.view.RegistrationView

data class Screen(
    val route: String,
    val icon: Int,
    val showBars: Boolean
)

object Screens{
    val chooseScreen: Screen = Screen("choose_screen", R.drawable.stub_image, false)
    val homeScreen: Screen = Screen("home_screen", R.drawable.stub_image, true)
    val registrationScreen: Screen = Screen("reg_screen", R.drawable.stub_image, false)
    val accountScreen: Screen = Screen("acc_screen", R.drawable.stub_image, true)
    val subjectScreen: Screen = Screen("subject_screen", R.drawable.stub_image, true)
    val scheduleScreen: Screen = Screen("schedule_screen", R.drawable.stub_image, true)
    val startingScreen: Screen = if(true) chooseScreen else homeScreen
}