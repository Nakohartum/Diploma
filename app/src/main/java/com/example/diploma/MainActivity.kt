package com.example.diploma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.diploma.ui.theme.DiplomaTheme
import com.example.diploma.view.MainView
import com.example.diploma.viewmodels.SubjectViewModel
import com.example.diploma.viewmodels.TargetViewModel
import com.example.diploma.viewmodels.TeacherViewModel
import com.example.diploma.viewmodels.UserViewModel
import com.example.diploma.viewmodels.Utils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Utils.applySavedLocale(this)
            val navController = rememberNavController()
            val viewModel: UserViewModel = viewModel()
            val teacherViewModel: TeacherViewModel = viewModel()
            val subjectViewModel: SubjectViewModel = viewModel()
            val targetViewModel: TargetViewModel = viewModel()
            DiplomaTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    MainView(viewModel, navController,teacherViewModel, targetViewModel, subjectViewModel)

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DiplomaTheme {
        Greeting("Android")
    }
}