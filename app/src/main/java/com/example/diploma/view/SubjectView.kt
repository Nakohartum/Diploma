package com.example.diploma.view

import android.Manifest
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.diploma.R
import com.example.diploma.models.SubjectData
import com.example.diploma.models.TargetData
import com.example.diploma.ui.theme.DarkBlue
import com.example.diploma.ui.theme.DarkOrange
import com.example.diploma.viewmodels.SubjectViewModel
import com.example.diploma.viewmodels.TargetViewModel
import com.example.diploma.viewmodels.TeacherViewModel
import com.example.diploma.viewmodels.UserViewModel
import com.example.diploma.viewmodels.Utils
import java.util.Calendar


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SubjectView(
    subjectViewModel: SubjectViewModel,
    userViewModel: UserViewModel,
    teacherViewModel: TeacherViewModel,
    targetViewModel: TargetViewModel,
    controller: NavController,
    subjectId: Long
){


    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {

        }
    )


    val subjectData = subjectViewModel.getSubject(subjectId).observeAsState()
    var showAddTarget by remember {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
            .padding(bottom = 50.dp)
    ) {
        Box(modifier = Modifier
            .padding(4.dp)
            .weight(0.9f)
            .background(colorResource(id = R.color.light_red), RoundedCornerShape(4.dp))){
           if (subjectData.value != null){
               val teacherData = teacherViewModel.getTeacher(subjectData.value!!.teacherID).observeAsState()
               targetViewModel.getTargets(subjectData.value!!.subjectId)
               userViewModel.setLastSubject(subjectData.value!!.subjectId)

               if (teacherData.value != null ){
                   var scrollableState = rememberScrollState()
                   Column(
                       modifier = Modifier
                           .fillMaxSize()
                           .verticalScroll(
                               scrollableState
                           )
                   ) {
                       Row(
                           modifier = Modifier.padding(8.dp),
                           verticalAlignment = Alignment.CenterVertically
                       ) {
                           if (subjectData.value!!.subjectIcon != null){
                               DisplayImage(modifier = Modifier
                                   .width(100.dp)
                                   .height(100.dp)
                                   , image = subjectData.value!!.subjectIcon)
                           }
                           Text(
                               modifier = Modifier.fillMaxWidth(),
                               text = subjectData.value!!.subjectName,
                               color = Color.White,
                               fontWeight = FontWeight.Bold,
                               fontSize = 20.sp,
                               textAlign = TextAlign.Center
                           )
                       }
                       Text(
                           modifier = Modifier.fillMaxWidth(),
                           text = subjectData.value!!.subjectDescription,
                           color = Color.White,
                           fontWeight = FontWeight.Bold,
                           fontSize = 18.sp,
                           textAlign = TextAlign.Center
                       )
                       Divider()
                       Text(
                           modifier = Modifier.fillMaxWidth(),
                           text = "${stringResource(id = R.string.teachers_name)}: ${teacherData.value!!.teacherName}",
                           color = Color.White,
                           fontWeight = FontWeight.Bold,
                           fontSize = 15.sp,
                           textAlign = TextAlign.Center
                       )
                       Text(
                           modifier = Modifier.fillMaxWidth(),
                           text = "${stringResource(id = R.string.teachers_phone)}: ${teacherData.value!!.teachersPhone}",
                           color = Color.White,
                           fontWeight = FontWeight.Bold,
                           fontSize = 15.sp,
                           textAlign = TextAlign.Center
                       )
                       Text(
                           modifier = Modifier.fillMaxWidth(),
                           text = "${stringResource(id = R.string.teachers_email)}: ${teacherData.value!!.teacherEmail}",
                           color = Color.White,
                           fontWeight = FontWeight.Bold,
                           fontSize = 15.sp,
                           textAlign = TextAlign.Center
                       )
                   }
               }

           }
        }

        Column(
            modifier = Modifier
                .weight(2f)
                .padding(8.dp)
                .border(2.dp, Color.Black, RoundedCornerShape(4.dp)),
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            if (subjectData.value != null){
                val targetsList by targetViewModel.targets.observeAsState(listOf())
                LazyColumn(
                    modifier = Modifier
                        .weight(1f),
                ){

                    items(targetsList, key = {target -> target.targetId}){target->
                        var dismissState = rememberDismissState(
                            confirmStateChange = {
                                if (it == DismissValue.DismissedToEnd){
                                    targetViewModel.deleteTarget(target)
                                }
                                true
                            }
                        )

                        SwipeToDismiss(
                            state = dismissState,
                            background = {
                                         val color by animateColorAsState(
                                             if (dismissState.dismissDirection == DismissDirection.StartToEnd) Color.Red else Color.Transparent,
                                             label = ""
                                         )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(color)
                                        .padding(horizontal = 20.dp),
                                    contentAlignment = Alignment.CenterStart
                                ){
                                    Icon(Icons.Default.Delete, null, tint = Color.White)
                                }


                            },
                            directions = setOf(DismissDirection.StartToEnd)
                        ) {
                            TargetView(targetData = target, targetViewModel){
                                val targetData = target.copy(
                                    isDone = it
                                )
                                targetViewModel.updateTarget(
                                    targetData
                                )
                                if (it){
                                    Utils.cancelDeadlineReminder(context, targetData.targetId.toInt())
                                }
                            }
                        }

                    }

                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        showAddTarget = true
                        Utils.checkAndRequestExactAlarmPermission(context)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                                permissionLauncher.launch(arrayOf(Manifest.permission.POST_NOTIFICATIONS))
                            }
                        }
                    }
                ) {
                    Text(text = stringResource(id = R.string.add_target))
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        controller.popBackStack()
                    }
                ) {
                    Text(text = stringResource(id = R.string.back))
                }

                if (showAddTarget){
                    AddTargetDialog(targetViewModel = targetViewModel, subjectData = subjectData.value!!) {
                        showAddTarget = false
                    }
                }
            }
        }
    }
}


@Composable
fun AddTargetDialog(
    targetViewModel: TargetViewModel,
    subjectData: SubjectData,
    onDismissClicked: () -> Unit
) {
    val context = LocalContext.current
    var showDialog by remember {
        mutableStateOf(false)
    }
    var isShown by remember {
        mutableStateOf(false)
    }
    if (showDialog){
        DatePicker(isShown,
            onDatePickerShown = {
                                isShown = it
            },
            onDateSelected = {
                targetViewModel.onTargetDeadlineChanged(it)
                showDialog = false
                isShown = false
            }
            )
    }
    AlertDialog(
        onDismissRequest = {
            onDismissClicked()
        },
        confirmButton = {
            Button(onClick = {
                val target = TargetData(
                    targetName = targetViewModel.targetName,
                    targetDescription = targetViewModel.targetDescription,
                    isDone = false,
                    subjectID = subjectData.subjectId,
                    targetDeadline = targetViewModel.targetDeadline
                )
                targetViewModel.addTarget(
                    target
                )
                targetViewModel.clearStates()
                Utils.scheduleNotification(context = context, target)
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
                    label = { Text(text = stringResource(id = R.string.target_name))},
                    value = targetViewModel.targetName,
                    onValueChange = {targetViewModel.onTargetNameChanged(it)}
                )
                OutlinedTextField(
                    label = { Text(text = stringResource(id = R.string.target_description))},
                    value = targetViewModel.targetDescription,
                    onValueChange = {targetViewModel.onTargetDescriptionChanged(it)}
                )
                IconButton(onClick = { showDialog = true }) {
                    Icon(Icons.Default.DateRange, contentDescription = null)
                }
            }
        }
    )
}


@Composable
fun DatePicker(isShown: Boolean, onDateSelected: (Long) -> Unit, onDatePickerShown: (Boolean) -> Unit){
        val context = LocalContext.current
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(context, {
                _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            onDateSelected(calendar.timeInMillis)
        },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        if (isShown){
            return
        }else{
            datePickerDialog.show()
            onDatePickerShown(true)
        }

}

@Composable
fun TargetView(targetData: TargetData, targetViewModel: TargetViewModel, onCheckedChange: (Boolean) -> Unit){
    var isChecked by remember {
        mutableStateOf(targetData.isDone)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(
                if (isChecked) Color.Gray else Utils.getBackgroundColorForDeadline(
                    targetData.targetDeadline,
                    System.currentTimeMillis()
                ),
                RoundedCornerShape(4.dp)
            )
            .padding(4.dp)
    ) {

            Checkbox(checked = isChecked, onCheckedChange = {
                isChecked = it
                onCheckedChange(it)
            })
            Column {
                Row(
                    modifier = Modifier.wrapContentSize(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        modifier = Modifier.padding(end = 6.dp),
                        text = targetData.targetName,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )

                    Text(
                        text = targetData.targetDescription,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }

                Divider(color = Color.Cyan)
                val targetDeadline by remember {
                    mutableStateOf(Utils.formatDate(targetData.targetDeadline))
                }
                Text(
                    text = "${targetDeadline}",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SubjectViewPreview(){

}