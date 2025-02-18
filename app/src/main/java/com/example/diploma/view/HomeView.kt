package com.example.diploma.view


import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.diploma.R
import com.example.diploma.models.DaysOfTheWeek
import com.example.diploma.models.Screens
import com.example.diploma.models.SubjectData
import com.example.diploma.models.TeacherData
import com.example.diploma.models.UserData
import com.example.diploma.ui.theme.DarkBlue
import com.example.diploma.ui.theme.DarkOrange
import com.example.diploma.ui.theme.Orange
import com.example.diploma.viewmodels.SubjectViewModel
import com.example.diploma.viewmodels.TeacherViewModel
import com.example.diploma.viewmodels.UserViewModel
import com.example.diploma.viewmodels.Utils


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDialog(
    userViewModel: UserViewModel,
    subjectViewModel: SubjectViewModel,
    teacherViewModel: TeacherViewModel,
    onDismissClicked: () -> Unit
) {
    var addTeacher by remember {
        mutableStateOf(false)
    }

    var addSubject by remember {
        mutableStateOf(false)
    }

    val teachers = userViewModel.getAllTeachers().observeAsState(listOf())

    AlertDialog(
        modifier = Modifier.background(Color.LightGray, RoundedCornerShape(16.dp)),
        onDismissRequest = { onDismissClicked() }
    ) {
        Column {
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable {
                        addTeacher = true
                    },
                text = stringResource(id = R.string.add_teacher),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
            )
            Divider(color = Color.Red)
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable {
                        addSubject = true
                    },
                text = stringResource(id = R.string.add_subject),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
            )
        }
    }

    if (addTeacher){
        TeacherAdd(userViewModel,teacherViewModel) {
            addTeacher = false
        }
    }
    if (addSubject){
        SubjectAdd(teachers.value, subjectViewModel, userViewModel) {
            addSubject = false
        }
    }
}

@Composable
fun TeacherAdd(
    userViewModel: UserViewModel,
    viewModel: TeacherViewModel,
    onDismissClicked: () -> Unit
){
    AlertDialog(
        onDismissRequest = {
                           onDismissClicked()
        },
        confirmButton = {
            Button(onClick = {
                val teacher = TeacherData(
                    teacherName = viewModel.teacherName,
                    teachersPhone = viewModel.teacherPhone,
                    teacherEmail = viewModel.teacherEmail,
                    userId = userViewModel.userData!!.userId
                )
                viewModel.addTeacher(teacher)
                viewModel.clearStates()
                onDismissClicked()
            }) {
                Text(text = stringResource(id = R.string.add))
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismissClicked()
            }) {
                Text(text = stringResource(id = R.string.cancel))
            }
        },
        text = {
            Column {
                OutlinedTextField(
                    label = { Text(text = stringResource(id = R.string.teachers_name))},
                    value = viewModel.teacherName,
                    onValueChange = {viewModel.onTeacherNameChanged(it)}
                )
                OutlinedTextField(
                    label = { Text(text = stringResource(id = R.string.teachers_phone))},
                    value = viewModel.teacherPhone,
                    onValueChange = {viewModel.onTeacherPhoneChanged(it)}
                )
                OutlinedTextField(
                    label = { Text(text = stringResource(id = R.string.teachers_email))},
                    value = viewModel.teacherEmail,
                    onValueChange = {viewModel.onTeacherEmailChanged(it)}
                )
            }
        }
    )
}

@Composable
fun SubjectAdd(
    teacherList: List<TeacherData>,
    subjectViewModel: SubjectViewModel,
    userViewModel: UserViewModel,
    onDismissClicked: () -> Unit
){
    val context = LocalContext.current
    var teacherChoose by remember {
        mutableStateOf(false)
    }

    var dayChoose by remember {
        mutableStateOf(false)
    }

    var chosenDay by remember {
        mutableStateOf<DaysOfTheWeek?>(null)
    }

    var chosenTeacher by remember {
        mutableStateOf<TeacherData?>(null)
    }

    var daysStrings by remember {
        mutableStateOf(listOf(
            context.getString(R.string.monday),
            context.getString(R.string.tuesday),
            context.getString(R.string.wednesday),
            context.getString(R.string.thursday),
            context.getString(R.string.friday)
        ))
    }

    var chosenDayString by remember {
        mutableStateOf("")
    }

    AlertDialog(
        onDismissRequest = {
            onDismissClicked()
        },
        confirmButton = {
            Button(onClick = {
                subjectViewModel.addSubject(
                    SubjectData(
                        subjectName = subjectViewModel.subjectName,
                        subjectDescription = subjectViewModel.subjectDescription,
                        teacherID = subjectViewModel.teacherId,
                        subjectIcon = subjectViewModel.subjectIcon,
                        dayOfWeek = subjectViewModel.dayOfWeek,
                        userId = userViewModel.userData!!.userId,
                        lastOpenedTime = System.currentTimeMillis()
                    )
                )
                subjectViewModel.clearStates()
                userViewModel.getLastSubject()
                onDismissClicked()
            }) {
                Text(text = stringResource(id = R.string.add))
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismissClicked()
            }) {
                Text(text = stringResource(id = R.string.cancel))
            }
        },
        text = {
            Column {
                OutlinedTextField(
                    label = { Text(text = stringResource(id = R.string.subject_name))},
                    value = subjectViewModel.subjectName,
                    onValueChange = {
                        if(it.length < 15){
                            subjectViewModel.onSubjectNameChanged(it)
                        }
                    }
                )
                OutlinedTextField(
                    label = { Text(text = stringResource(id = R.string.subject_description))},
                    value = subjectViewModel.subjectDescription,
                    onValueChange = {subjectViewModel.onSubjectDescriptionChanged(it)}
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(onClick = {
                        teacherChoose = true
                    }) {
                        Text(text = if (chosenTeacher != null) chosenTeacher!!.teacherName else stringResource(
                            id = R.string.choose_teacher
                        ))
                        DropdownMenu(expanded = teacherChoose, onDismissRequest = {
                            teacherChoose = false
                        }) {
                            teacherList.forEach{
                                DropdownMenuItem(
                                    text = {
                                        Text(text = it.teacherName)
                                    },
                                    onClick = {
                                        chosenTeacher = it
                                        subjectViewModel.onTeacherIdChanged(it.teacherId)
                                        teacherChoose = false
                                    })
                            }
                        }
                    }


                    Button(onClick = { dayChoose = true }) {
                        Text(text = if(chosenDay != null) chosenDayString  else stringResource(id = R.string.select_day))
                        DropdownMenu(expanded = dayChoose, onDismissRequest = {
                            dayChoose = false
                        }) {

                            daysStrings.forEach{
                                DropdownMenuItem(
                                    text = {
                                        Text(text = "${it}")
                                    },
                                    onClick = {
                                        chosenDay = DaysOfTheWeek.entries.toTypedArray()[daysStrings.indexOf(it)]
                                        chosenDayString = it
                                        subjectViewModel.onDayOfWeekChanged(chosenDay!!.id)
                                        dayChoose = false
                                    })
                            }
                        }
                    }
                }

                ImagePicker(onImagePicked = {
                    //val imageData = Utils.uriToByteArray(context, it)
                    val imagePath = Utils.copyUriToFile(context, it, "image_${System.currentTimeMillis()}")
                    subjectViewModel.onSubjectIconChanged(imagePath!!)
                })
            }
        }
    )
}



@Composable
fun ImagePicker(onImagePicked: (Uri) -> Unit){
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){
        it?.let{
            onImagePicked(it)
        }
    }

    Column {
        IconButton(onClick = { launcher.launch("image/*") }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}

@Composable
fun HomeView(
    controller: NavController,
    viewModel: UserViewModel,
    subjectViewModel: SubjectViewModel,
    paddingValues: PaddingValues
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
            .padding(paddingValues)
    ) {
        ContentView(
            controller,
            viewModel,
            subjectViewModel
        )
    }
}

@Composable
fun BottomBar(
    onHomeClicked: () -> Unit,
    onPlusClicked: (Boolean) -> Unit,
    onAccountClicked: () -> Unit
) {
    BottomNavigation(
        backgroundColor = DarkOrange,
    ) {
        BottomNavigationItem(selected = true,
            onClick = {
                onHomeClicked()
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_home_24),
                    contentDescription = null,
                    tint = DarkBlue
                )
            }
        )
        BottomNavigationItem(selected = true,
            onClick = {
                      onPlusClicked(true)
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_circle_outline_24),
                    contentDescription = null,
                    tint = DarkBlue
                )
            }
        )
        BottomNavigationItem(selected = true,
            onClick = {
                      onAccountClicked()
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_account_box_24),
                    contentDescription = null,
                    tint = DarkBlue
                )
            }
        )

    }
}

@Composable
fun TopBar(userData: UserData?){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = "${stringResource(id = R.string.hello)} ${userData?.userName}!",
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

@Composable
fun ContentView(
    controller: NavController,
    userViewModel: UserViewModel,
    subjectViewModel: SubjectViewModel
) {
    userViewModel.getLastSubject()
    val lastOpenedLesson by userViewModel.lastSubject.observeAsState(initial = null)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (lastOpenedLesson != null){
            LastOpenedLesson(controller = controller, subjectData = lastOpenedLesson)
        }
        Spacer(modifier = Modifier.height(16.dp))
        AllLessonsView(controller, userViewModel, subjectViewModel)
    }
}

@Composable
fun LastOpenedLesson(controller: NavController, subjectData: SubjectData?) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 18.dp, vertical = 18.dp)
            .background(Orange, RoundedCornerShape(8.dp))
            .clickable {
                controller.navigate("${Screens.subjectScreen.route}/${subjectData?.subjectId}")
            }
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
                    text = "${stringResource(id = R.string.continue_to)}\n${subjectData?.subjectName}",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
            if (subjectData!!.subjectIcon != null){
                DisplayImage(
                    modifier = Modifier.width(180.dp),
                    image = subjectData.subjectIcon
                )
            }
        }
    }
}


@Composable
fun AllLessonsView(controller: NavController, userViewModel: UserViewModel, subjectViewModel: SubjectViewModel){
    userViewModel.getAllSubjects()
    val listOfSubject = userViewModel.subjects.observeAsState(listOf())
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 18.dp),
            text = stringResource(id = R.string.all_lessons),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
    }
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = Modifier.height(400.dp),
        verticalArrangement = Arrangement.Top
    ){
        items(listOfSubject.value){
            LessonView(controller = controller, subjectData = it, subjectViewModel = subjectViewModel, userViewModel)
        }
    }
}

@Composable
fun LessonView(controller: NavController, subjectData: SubjectData, subjectViewModel: SubjectViewModel, userViewModel: UserViewModel) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .background(Orange, RoundedCornerShape(8.dp))
            .clickable {
                controller.navigate("${Screens.subjectScreen.route}/${subjectData.subjectId}")
            }
    ){
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                if (subjectData.subjectIcon != null){
                    DisplayImage(
                        modifier = Modifier.width(120.dp),
                        image = subjectData.subjectIcon
                    )
                }
                IconButton(modifier = Modifier.padding(start = 4.dp), onClick = {
                    subjectViewModel.deleteSubject(subjectData)
                    userViewModel.getLastSubject()
                    controller.navigate(Screens.homeScreen.route)
                }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = Color.White)
                }
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                textAlign = TextAlign.Center,
                text = subjectData.subjectName,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}


@Composable
fun DisplayImage(modifier: Modifier, image: String?) {
    val imageBitmap = BitmapFactory.decodeFile(image)
    Image(
        modifier = modifier,
        bitmap = imageBitmap.asImageBitmap(),
        contentDescription = "Lesson Image"
    )
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview(){

}