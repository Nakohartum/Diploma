package com.example.diploma.viewmodels

import android.accounts.AuthenticatorDescription
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SubjectViewModel(
    private val appRepository: AppRepository = Graph.appRepository
): ViewModel() {
    private val _schedule = MutableLiveData<List<SubjectData>>()
    var subjectName by mutableStateOf("")
    var subjectDescription by mutableStateOf("")
    var teacherId by mutableLongStateOf(0)
    var subjectIcon by mutableStateOf<ByteArray?>(null)
    var dayOfWeek by mutableIntStateOf(0)
    var schedule: LiveData<List<SubjectData>> = _schedule

    fun onSubjectNameChanged(newName: String){
        subjectName = newName
    }

    fun onSubjectDescriptionChanged(newDescription: String){
        subjectDescription = newDescription
    }

    fun onTeacherIdChanged(newId: Long){
        teacherId = newId
    }

    fun onSubjectIconChanged(newIcon: ByteArray){
        subjectIcon = newIcon
    }

    fun onDayOfWeekChanged(newDayOfWeek: Int){
        dayOfWeek = newDayOfWeek
    }
    

    fun addSubject(subjectData: SubjectData){
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.addSubject(subjectData)
        }
    }



    fun getSubject(subjectId: Long) : LiveData<SubjectData>{
        val result = MutableLiveData<SubjectData>()
        viewModelScope.launch(Dispatchers.IO){
            result.postValue(appRepository.getSubject(subjectId))
        }
        return result
    }

    fun getSubjectsByDay(id: Int, userId: Int): LiveData<List<SubjectData>> {
        val result = MutableLiveData<List<SubjectData>>()
        viewModelScope.launch(Dispatchers.IO){
            //result.postValue(appRepository.getTargets(subjectId))
            appRepository.getSubjectByDay(id, userId).collect{
                _schedule.postValue(it)
            }
        }
        return result
    }


}