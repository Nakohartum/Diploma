package com.example.diploma.view


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.diploma.MainActivity
import com.example.diploma.models.Screens
import com.example.diploma.ui.theme.DarkBlue
import com.example.diploma.viewmodels.SubjectViewModel
import com.example.diploma.viewmodels.TargetViewModel
import com.example.diploma.viewmodels.TeacherViewModel
import com.example.diploma.viewmodels.UserViewModel

@Composable
fun MainView(
    viewModel: UserViewModel,
    controller: NavController,
    teacherViewModel: TeacherViewModel,
    targetViewModel: TargetViewModel,
    subjectViewModel: SubjectViewModel
){
    var showAddDialog by remember {
        mutableStateOf(false)
    }

    val showBars = remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = DarkBlue,
        topBar = { if(showBars.value) TopBar(userData = viewModel.userData) },
        bottomBar = {if(showBars.value) BottomBar(
            onHomeClicked = {
                controller.navigate(Screens.homeScreen.route)
            },
            onPlusClicked = {toAdd ->
                showAddDialog = toAdd
            },
            onAccountClicked = {
                controller.navigate(Screens.accountScreen.route)
            }
        )}
    ) {
        Navigation(navController = controller,viewModel, teacherViewModel, subjectViewModel, targetViewModel, it, showBars)
        if (showAddDialog){
            AddDialog(viewModel, subjectViewModel, teacherViewModel) {
                showAddDialog = false
            }
        }
    }
}


@Composable
fun Navigation(
    navController: NavController,
    viewModel: UserViewModel,
    teacherViewModel: TeacherViewModel,
    subjectViewModel: SubjectViewModel,
    targetViewModel: TargetViewModel,
    paddingValues: PaddingValues,
    showBars: MutableState<Boolean>
){

    NavHost(navController = navController as NavHostController, startDestination = Screens.startingScreen.route){
        composable(route = Screens.registrationScreen.route){
            RegistrationView(viewModel, teacherViewModel, navController)
            showBars.value = Screens.registrationScreen.showBars
        }

        composable(route = Screens.chooseScreen.route){
            showBars.value = Screens.chooseScreen.showBars
            EnterView(controller = navController, userViewModel = viewModel)
        }

        composable(route = Screens.accountScreen.route){
            showBars.value = Screens.accountScreen.showBars
            AccountView(userData = viewModel.userData!!, navController)
        }

        composable(route = Screens.homeScreen.route){
            showBars.value = Screens.homeScreen.showBars
            HomeView(
                navController,
                viewModel = viewModel,
                subjectViewModel,
                paddingValues = paddingValues
            )
        }
        
//        composable(route = Screens.scheduleScreen.route){
//            ScheduleView(navController = navController,daysOfWeek = stubObject.daysOfTheWeek)
//        }

        composable(route = "${Screens.subjectScreen.route}/{subjectId}"){ backStackEntry ->
            val subjectId = backStackEntry.arguments?.getString("subjectId")
            showBars.value = Screens.subjectScreen.showBars

            SubjectView(
                subjectViewModel,
                viewModel,
                teacherViewModel,
                targetViewModel,
                navController,
                subjectId!!.toLong()
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview(){

}