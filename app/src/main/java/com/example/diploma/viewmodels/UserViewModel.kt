package com.example.diploma.viewmodels

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diploma.models.SubjectData
import com.example.diploma.models.TargetData
import com.example.diploma.models.TeacherData
import com.example.diploma.models.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(
    private val appRepository: AppRepository = Graph.appRepository,

): ViewModel() {
    private var _subjects = MutableLiveData<List<SubjectData>>()
    private var _lastSubject = MutableLiveData<SubjectData?>()
    var userData by mutableStateOf<UserData?>(null)
    var userName by mutableStateOf("")
    var userSurname by mutableStateOf("")
    var userProfession by mutableStateOf("")
    var userCourse by mutableIntStateOf(0)
    var userPicture by mutableStateOf<String?>(null)
    var subjects = _subjects
    var lastSubject = _lastSubject

    fun clearStates(){
        userName = ""
        userSurname = ""
        userProfession = ""
        userCourse = 0
        userPicture = null
    }

    fun onUserNameChanged(newName: String){
        userName = newName
    }
    fun onUserSurnameChanged(newSurname: String){
        userSurname = newSurname
    }
    fun onUserProfessionChanged(newProfession: String){
        userProfession = newProfession
    }
    fun onUserCourseChanged(newCourse: Int){
        userCourse = newCourse
    }
    fun onUserPictureChanged(newPicture: String){
        userPicture = newPicture
    }

//    fun getSubjectWithTargets(subjectId: Int): Flow<List<SubjectWithTargets>>{
//        return appRepository.getSubject(subjectId)
//    }

    fun addUser(userData: UserData) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.addUser(userData)
        }
    }

    fun provideUser(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userData = appRepository.getUser(userName)
        }
    }

    fun setLastSubject(subjectId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.updateLastOpened(subjectId, userData!!.userId, System.currentTimeMillis())
        }
    }

    fun getAllSubjects(): LiveData<List<SubjectData>> {
//        val result = MutableLiveData<List<SubjectData>>()
//        viewModelScope.launch(Dispatchers.IO){
//            result.postValue(appRepository.getSubjectsFromUser(userData!!.userId))
//        }
//        return result

        val result = MutableLiveData<List<SubjectData>>()
        viewModelScope.launch(Dispatchers.IO){
            //result.postValue(appRepository.getTargets(subjectId))
            appRepository.getSubjectsFromUser(userData!!.userId).collect{
                _subjects.postValue(it)
            }
        }
        return result
    }

    fun getAllTeachers(): LiveData<List<TeacherData>> {
        val result = MutableLiveData<List<TeacherData>>()
        viewModelScope.launch(Dispatchers.IO){
            result.postValue(appRepository.getTeachers(userData!!.userId))
        }
        return result
    }

    fun getLastSubject(): LiveData<SubjectData> {
//        val result = MutableLiveData<SubjectData>()
//        viewModelScope.launch(Dispatchers.IO){
//            result.postValue(appRepository.getLastOpened(userData!!.userId))
//        }
//        return result
        val result = MutableLiveData<SubjectData>()
        viewModelScope.launch(Dispatchers.IO){
            //result.postValue(appRepository.getTargets(subjectId))
            _lastSubject.postValue(appRepository.getLastOpened(userData!!.userId))
        }
        return result
    }

    fun getAllUsers(): LiveData<List<UserData>> {
        val result = MutableLiveData<List<UserData>>()
        viewModelScope.launch(Dispatchers.IO){
            result.postValue(appRepository.getAllUsers())
        }
        return result
    }

}