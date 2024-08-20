package com.example.diploma.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.diploma.models.SubjectData
import com.example.diploma.models.TargetData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TargetViewModel(
    private val appRepository: AppRepository = Graph.appRepository
): ViewModel() {
    private val _targets = MutableLiveData<List<TargetData>>()
    var targetName by mutableStateOf("")
    var targetDescription by mutableStateOf("")
    var targetDeadline by mutableLongStateOf(0L)
    var isDone by mutableStateOf(false)
    var targets: LiveData<List<TargetData>> = _targets

    fun clearStates(){
        targetName = ""
        targetDescription = ""
        targetDeadline = 0L
    }

    fun onTargetNameChanged(newName: String){
        targetName = newName
    }

    fun onTargetDescriptionChanged(newDescription: String){
        targetDescription = newDescription
    }

    fun onTargetDeadlineChanged(newDeadline: Long){
        targetDeadline = newDeadline
    }

    fun onIsDoneChanged(isDone: Boolean){
        this.isDone = isDone
    }

    fun addTarget(targetData: TargetData){
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.addTarget(targetData)
        }
    }



    fun getTargets(subjectId: Long) : LiveData<List<TargetData>> {
        val result = MutableLiveData<List<TargetData>>()
        viewModelScope.launch(Dispatchers.IO){
            //result.postValue(appRepository.getTargets(subjectId))
            appRepository.getTargets(subjectId).collect{
                _targets.postValue(it)
            }
        }
        return result
    }

    fun updateTarget(targetData: TargetData) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.updateTarget(targetData)
        }
    }

    fun deleteTarget(targetData: TargetData) {
        viewModelScope.launch(Dispatchers.IO){
            appRepository.deleteTarget(targetData)
        }
    }
}