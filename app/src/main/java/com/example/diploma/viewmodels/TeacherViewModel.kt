package com.example.diploma.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diploma.models.SubjectData
import com.example.diploma.models.TeacherData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeacherViewModel(
    private val appRepository: AppRepository = Graph.appRepository
): ViewModel() {
    var teacherName by mutableStateOf("")
    var teacherPhone by mutableStateOf("")
    var teacherEmail by mutableStateOf("")

    fun clearStates(){
        teacherName = ""
        teacherPhone = ""
        teacherEmail = ""
    }

    fun onTeacherNameChanged(newName: String){
        teacherName = newName
    }

    fun onTeacherPhoneChanged(newPhone: String){
        teacherPhone = newPhone
    }

    fun onTeacherEmailChanged(newEmail: String){
        teacherEmail = newEmail
    }

    fun addTeacher(teacher: TeacherData) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.addTeacher(teacher)
        }
    }

    fun getTeacher(teacherID: Long): LiveData<TeacherData> {
        val result = MutableLiveData<TeacherData>()
        viewModelScope.launch(Dispatchers.IO){
            result.postValue(appRepository.getTeacher(teacherID))
        }
        return result
    }
}